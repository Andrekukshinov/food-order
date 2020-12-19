package by.food.orders.application;
import java.util.Scanner;


enum UserChoice {
    UNDEFINED,
    SHOW_CATALOG,
    ADD_PRODUCT_TO_CART,
    SHOW_CART_CONTENTS,
    PLACE_ORDER,
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
                return PLACE_ORDER;
            case 5:
                return SHOW_ORDERS;
            case 6:
                return EXIT;
        }
        return UNDEFINED;
    }
}

public class App {
    public static void main(String[] args) {
        //OrderManager orderManager = new OrderManager();
        Scanner scanner = new Scanner(System.in);
        Catalog catalog = new Catalog();
        Cart cart = new Cart();
        UserChoice userChoice = UserChoice.UNDEFINED;
        Menu menu = new Menu();

        while (!userChoice.equals(UserChoice.EXIT)) {
            menu.showMenu();
            try {
                userChoice = UserChoice.fromInt(Integer.parseInt(scanner.nextLine().toLowerCase()));

                if (userChoice == UserChoice.SHOW_CATALOG) {
                    catalog.Render();
                }

                else if (userChoice == UserChoice.SHOW_CART_CONTENTS) {
                    cart.Render();
                }

                else if (userChoice == UserChoice.ADD_PRODUCT_TO_CART) {
                    System.out.println("Введите код продукта");
                    String productNum = scanner.nextLine().toLowerCase();
                    if (!productNum.equals("")) {
                        Integer productId = Integer.parseInt(productNum);
                        Product product = catalog.GetProduct(productId);
                        cart.AddToCart(product);
                    } else {
                        System.out.println("Код продукта не введен. Попробуйте еще раз");
                        userChoice = UserChoice.ADD_PRODUCT_TO_CART;
                    }
                }

                else if (userChoice == UserChoice.PLACE_ORDER) {
                    break;
                }

                else if (userChoice == UserChoice.SHOW_ORDERS) {
                    break;
                }

                else if (userChoice == UserChoice.EXIT) {
                    break;
                }
            }
            catch ( Exception e) {
                //System.out.println(e.getMessage());
                System.out.println("Выберите существующий пункт меню");
            }
        }
    }
}
