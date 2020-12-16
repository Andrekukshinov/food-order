package by.food.orders.application;
import java.util.*;

public class Catalog {
    private HashMap<Integer, Product> catalog = new HashMap<Integer, Product>();

    Catalog() {
        catalog.put(2001, new Product("Капучино", 3.5));
        catalog.put(2002, new Product("Латте", 4.2));
        catalog.put(2003, new Product("Американо", 2.5));
        catalog.put(1001, new ProductFood("Маргарита", 10.2, "Томатный соус Domino's, Сыр моцарелла"));
        catalog.put(1002, new ProductFood("Пепперони", 12.8, "Томатный соус Domino's, Пепперони, Сыр моцарелла"));
        catalog.put(1003, new ProductFood("Фирменная", 15.2, "Пармезан, Курица, Буженина, Лук, Томатный соус Domino's, Бекон, Сладкий перец, Томаты, Сыр моцарелла, Телятина, Шампиньоны"));

    }

    public void printCatalog() {
        for (Integer i : catalog.keySet()) {
            System.out.println(i + " " + catalog.get(i).printData());
        }
    }
}