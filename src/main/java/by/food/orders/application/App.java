package by.food.orders.application;

import by.food.orders.data.Menu;
import by.food.orders.data.cart.Cart;
import by.food.orders.data.cart.CartManager;
import by.food.orders.data.cart.CartManagerImpl;
import by.food.orders.data.dao.FileOrderDaoImpl;
import by.food.orders.data.dao.UserStorageDao;
import by.food.orders.data.dao.api.OrderDao;
import by.food.orders.data.dao.api.UserDao;
import by.food.orders.data.storage.Catalog;
import by.food.orders.entity.CartItem;
import by.food.orders.entity.Order;
import by.food.orders.entity.Product;
import by.food.orders.entity.User;
import by.food.orders.exception.DaoException;
import by.food.orders.exception.DataException;
import by.food.orders.exception.NoSuchUserException;
import by.food.orders.logics.AuthenticationManager;
import by.food.orders.logics.OrderManager;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;


enum UserChoice {
    UNDEFINED,
    SHOW_CATALOG,
    ADD_PRODUCT_TO_CART,
    SHOW_CART_CONTENTS,
    CONFIRM_ORDER,
    SHOW_ORDERS,
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
            case 6:
                return EXIT;
        }
        return UNDEFINED;
    }
}

public class App {
    //todo For LENA display food as I wrote you please)
    //todo For TANYA put users to file instead of storage
    //todo For ME put catalog to file instead of storage
    //todo For ME split user and server part
    //todo For ME add re-login

    private static final String NOT_FOUND = "Code is incorrect. Please, try again";

    public static void main(String[] args) throws DataException {
        //OrderManager orderManager = new OrderManager();
        Scanner scanner = new Scanner(System.in);
        Catalog catalog = new Catalog();
        Cart cart = new Cart();
        UserChoice userChoice = null;
        Menu menu = new Menu();
        User sessionUser = null;
        CartManager cartManager = new CartManagerImpl(cart);
        UserDao userDao = new UserStorageDao();
        OrderDao orderDao = new FileOrderDaoImpl();
        OrderManager orderManager = new OrderManager();
        AuthenticationManager authenticationManager = new AuthenticationManager(userDao);
        boolean isNotAuthenticated = true;

        do {
            System.out.println("Before making orders you should log in");
            System.out.println("Your login:");
            String login = scanner.nextLine();
            System.out.println("Your pass:");
            String pass = scanner.nextLine();
            try {
                sessionUser = authenticationManager.authenticateByCredentials(login, pass);
            } catch (NoSuchUserException | DaoException e) {
                System.out.println(e.getMessage());
                System.out.println("Try again, please");
                continue;
            }
            isNotAuthenticated = false;
        } while (isNotAuthenticated);

        while (!UserChoice.EXIT.equals(userChoice)) {
            menu.showMenu();
            try {
                int userChoiceInt = Integer.parseInt(scanner.nextLine());
                userChoice = UserChoice.fromInt(userChoiceInt);
                switch (userChoice) {
                    case SHOW_CATALOG:
                        catalog.render();
                        break;
                    case SHOW_CART_CONTENTS:
                        cartManager.render();
                        break;
                    case ADD_PRODUCT_TO_CART:
                        AtomicBoolean isFound = new AtomicBoolean(false);
                        do {
                            isFound.set(false);
                            System.out.println("Введите код продукта");
                            Long productNum = scanner.nextLong();
                            Optional<Product> productOptional = catalog.getProduct(productNum);
                            productOptional.ifPresentOrElse(
                                    product -> {
                                        cartManager.addToCart(product);
                                        isFound.set(true);
                                    },
                                    () -> {
                                        System.out.println("wrong code try, again please");
                                    });
                        } while (!isFound.get());
                        break;
                    case CONFIRM_ORDER:
                        List<CartItem> cartItems = cartManager.getCartItems();
                        if (cartItems.size() > 0) {
                            Long userId = sessionUser.getId();
                            System.out.println("=== CHECKOUT ORDER ===");
                            System.out.println("Set delivery date in format yyyy-mm-dd");

                            String deliveryDateString = scanner.nextLine();
                            LocalDate deliveryDate = LocalDate.parse(deliveryDateString);

                            Order cartOrder = orderManager.getCartOrder(cartManager, deliveryDate, userId);
                            orderDao.save(cartOrder);
                            cartManager.clearCart();
                        }
                        break;
                    case SHOW_ORDERS:
                        Long userId = sessionUser.getId();
                        List <Order> userOrders = orderDao.getUserOrders(userId);
                        if (userOrders.isEmpty()) {
                            System.out.println("You don't have orders");
                            break;
                        }
                        System.out.println("Your orders: ");
                        for (Order order : userOrders) {
                            System.out.println(order.toString());
                            System.out.println("----------------------------------------");
                        };
                        break;
                    case EXIT:
                        break;
                    default:
                        System.out.println("Choose the correct menu item");
                        break;
                }
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
