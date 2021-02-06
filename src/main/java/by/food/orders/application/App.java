package by.food.orders.application;

import by.food.orders.data.Menu;
import by.food.orders.data.cart.Cart;
import by.food.orders.data.cart.CartManager;
import by.food.orders.data.cart.CartManagerImpl;
import by.food.orders.data.dao.FileOrderDaoImpl;
import by.food.orders.data.dao.FileUserDao;
import by.food.orders.data.dao.api.OrderDao;
import by.food.orders.data.dao.api.UserDao;
import by.food.orders.data.storage.Catalog;
import by.food.orders.entity.CartItem;
import by.food.orders.entity.Order;
import by.food.orders.entity.Product;
import by.food.orders.entity.User;
import by.food.orders.exception.NoSuchUserException;
import by.food.orders.logics.AuthenticationManager;
import by.food.orders.logics.OrderManager;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;


enum UserChoice {
    UNDEFINED,
    SHOW_CATALOG,
    ADD_PRODUCT_TO_CART,
    SHOW_CART_CONTENTS,
    CONFIRM_ORDER,
    SHOW_ORDERS,
    LOG_OUT,
    EXIT;

    //fromInt - конвертация int в enum
    public static UserChoice fromInt(int i) {
        switch (i) {
            case 1:
                return SHOW_CATALOG;
            case 2:
                return ADD_PRODUCT_TO_CART;
            case 3:
                return SHOW_CART_CONTENTS;
            case 4:
                return CONFIRM_ORDER;
            case 5:
                return SHOW_ORDERS;
//            case 6:
//                return LOG_OUT;
            case 6:
                return EXIT;
        }
        return UNDEFINED;
    }
}

public class App {

    private static final String FILE_PATH_ORDERS = "src/data/source/orders.json";
    private static final String FILE_PATH_USERS = "src/data/source/users.json";
    private static final int PORT = 8082;

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        UserDao userDao = new FileUserDao(FILE_PATH_USERS);
        AuthenticationManager authenticationManager = new AuthenticationManager(userDao);
        Catalog catalog = new Catalog();
        Cart cart = new Cart();
        UserChoice userChoice = null;
        Menu menu = new Menu();
        CartManager cartManager = new CartManagerImpl(cart);
        OrderDao orderDao = new FileOrderDaoImpl(FILE_PATH_ORDERS);
        OrderManager orderManager = new OrderManager();
        User sessionUser = null;
        try (ServerSocket serverSocket = new ServerSocket(PORT);
             Socket clientSocket = serverSocket.accept()) {
            try (ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                 ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())) {
                String clientMessageRecieved;
                String pass;
                boolean isNotAuthenticated = true;
                String tryAgain = null;
                do {
                    String logInf = (tryAgain == null ? "" : tryAgain) + "Before making orders you should log in \n Your login:";
                    out.writeObject(logInf);
                    clientMessageRecieved = (String) in.readObject();
                    System.out.println(clientMessageRecieved);
                    out.writeObject("Your pass:");
                    pass = (String) in.readObject();
                    try {
                        sessionUser = authenticationManager.authenticateByCredentials(clientMessageRecieved, pass);
                    } catch (NoSuchUserException e) {
                        System.out.println(e.getMessage());
                        if (tryAgain == null) {
                            tryAgain = ("Try again, please\n");
                        }
                        continue;
                    }
                    isNotAuthenticated = false;
                } while (isNotAuthenticated);

                String menuToShow = menu.showMenu();
                while (!(clientMessageRecieved).equals("6")) {
                    System.out.println(clientMessageRecieved);
                    out.writeObject(menuToShow);
                    while (!UserChoice.EXIT.equals(userChoice)) {
                        clientMessageRecieved = (String) in.readObject();
                        try {
                            int userChoiceInt = Integer.parseInt(clientMessageRecieved);
                            userChoice = UserChoice.fromInt(userChoiceInt);
                            switch (userChoice) {
                                case SHOW_CATALOG:
                                    String rendered = catalog.render() + "\n";
                                    out.writeObject(rendered + menuToShow);
                                    break;
                                case SHOW_CART_CONTENTS:
                                    String renderedCart = cartManager.render();
                                    out.writeObject(renderedCart + "\n" + menuToShow);
                                    break;
                                case ADD_PRODUCT_TO_CART:
                                    AtomicBoolean isFound = new AtomicBoolean(false);
                                    StringBuilder err = new StringBuilder("");
                                    do {
                                        isFound.set(false);
                                        out.writeObject(err.toString() + "Set product code ");
                                        String productNumStr = (String) in.readObject();
                                        long productNum = Long.parseLong(productNumStr);
                                        Optional<Product> productOptional = catalog.getProduct(productNum);
                                        productOptional.ifPresentOrElse(
                                                product -> {
                                                    cartManager.addToCart(product);
                                                    isFound.set(true);
                                                },
                                                () -> {
                                                    if (err.toString().isEmpty()) {
                                                        err.append("wrong code try, again please\n");
                                                    }
                                                });
                                    } while (!isFound.get());
                                    out.writeObject(menuToShow);
                                    break;
                                case CONFIRM_ORDER:
                                    List<CartItem> cartItems = cartManager.getCartItems();
                                    if (cartItems.size() > 0) {
                                        Long userId = sessionUser.getId();
                                        StringBuilder result = new StringBuilder("=== CHECKOUT ORDER ===");
                                        result.append("\n");
                                        result.append("Set delivery date in format yyyy-mm-dd");
                                        result.append("\n");
                                        out.writeObject(result.toString());

                                        String deliveryDateString = (String) in.readObject();
                                        LocalDate deliveryDate = LocalDate.parse(deliveryDateString);
                                        if (deliveryDate.isBefore(LocalDate.now())) {
                                            throw new RuntimeException("too late");
                                        }

                                        Order cartOrder = orderManager.getCartOrder(cartManager, deliveryDate, userId);
                                        orderDao.save(cartOrder);
                                        cartManager.clearCart();
                                        out.writeObject(menuToShow);
                                    } else {
                                        out.writeObject("Cart is empty \n" + menuToShow);
                                    }
                                    break;
                                case SHOW_ORDERS:
                                    Long userId = sessionUser.getId();
                                    List<Order> userOrders = orderDao.getUserOrders(userId);
                                    if (userOrders.isEmpty()) {
                                        out.writeObject("You don't have orders\n" + menuToShow);
                                        break;
                                    }
                                    StringBuilder result = new StringBuilder("Your orders: \n");
                                    System.out.println("Your orders: ");
                                    for (Order order : userOrders) {
                                        result.append(order.toString());
                                        result.append("\n");
                                        result.append("----------------------------------------\n");
                                    }
                                    result.append(menuToShow);
                                    out.writeObject(result.toString());
                                    break;
                                case LOG_OUT:
                                    sessionUser = authenticationManager.authorize();
                                    break;
                                case EXIT:
                                    break;
                                default:
                                    out.writeObject("Choose the correct menu item\n " + menuToShow);
                                    break;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println(e.getMessage());
                            out.writeObject(menuToShow);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            out.writeObject("wrong date\n" + menuToShow);
                        }
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

