package by.food.orders.application;

import by.food.orders.data.Menu;
import by.food.orders.data.cart.Cart;
import by.food.orders.data.cart.CartManager;
import by.food.orders.data.cart.CartManagerImpl;
import by.food.orders.data.dao.OrderStorageDaoImpl;
import by.food.orders.data.dao.UserStorageDao;
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

import javax.xml.catalog.CatalogException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


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

    private static final String NOT_FOUND = "Код продукта не введен. Попробуйте еще раз";

    public static void main(String[] args) {
        //OrderManager orderManager = new OrderManager();
        Scanner scanner = new Scanner(System.in);
        Catalog catalog = new Catalog();
        Cart cart = new Cart();
        UserChoice userChoice = null;
        Menu menu = new Menu();
        User sessionUser = null;
        CartManager cartManager = new CartManagerImpl(cart);
        UserDao userDao = new UserStorageDao();
        OrderDao orderDao = new OrderStorageDaoImpl();
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
            } catch (NoSuchUserException e) {
                System.out.println(e.getMessage());
                System.out.println("try again");
                continue;
            }
            isNotAuthenticated = false;
        } while (isNotAuthenticated);

        while (!UserChoice.EXIT.equals(userChoice)) {
            menu.showMenu();
            try {
                int userChoiceInt = scanner.nextInt();
                userChoice = UserChoice.fromInt(userChoiceInt);
                switch (userChoice) {
                    case SHOW_CATALOG:
                        catalog.render();
                        break;
                    case SHOW_CART_CONTENTS:
                        cartManager.render();
                        break;
                    case ADD_PRODUCT_TO_CART:
                        System.out.println("Введите код продукта");
                        Long productNum = scanner.nextLong();
                        Product product = catalog.getProduct(productNum).orElseThrow(() -> new CatalogException(NOT_FOUND));
                        cartManager.addToCart(product);
                        break;
                    case CONFIRM_ORDER:
                        // TODO: 28.12.2020 impl
                        List<CartItem> cartItems = cartManager.getCartItems();
                        if (cartItems.size() > 0) {
                            System.out.println("Set delivery date in format yyyy-mm-dd");

                            scanner.nextLine();
                            String deliveryDateString = scanner.nextLine();
                            LocalDate deliveryDate = LocalDate.parse(deliveryDateString);

                            Long id = sessionUser.getId();
                            Order cartOrder = orderManager.getCartOrder(cartManager, deliveryDate, id);
                            orderDao.save(cartOrder);
                            cartManager.clearCart();
                        }
                        break;
                    case SHOW_ORDERS:
                        // TODO: 28.12.2020 impl
                        break;
                    case EXIT:
                        break;
                    default:
                        System.out.println("Выберите существующий пункт меню");
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
// TODO: 05.01.2021 add user's order & remove from cart
// TODO: 05.01.2021 show orders
