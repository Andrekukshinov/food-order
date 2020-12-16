package by.food.orders.application;
import java.util.Scanner;

public class OrderManager {
    Scanner scanner = new Scanner(System.in);
    Catalog catalogMenu = new Catalog();

    public void create() {
        String orderNum = "";

        while (!orderNum.trim().equals("y")) {
            System.out.println("МЕНЮ");
            System.out.println("Cделайте заказ, по окончании введите y");
            System.out.println("===================================");
            catalogMenu.printCatalog();
            orderNum = scanner.nextLine().toLowerCase();
        }
    }
}
