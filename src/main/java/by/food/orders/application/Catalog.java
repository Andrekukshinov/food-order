package by.food.orders.application;
import java.util.*;

public class Catalog {
    private HashMap<Integer, Product> catalog = new HashMap<>();

    Catalog() {
        catalog.put(2001, new Product(2001, "Капучино", 3.5));
        catalog.put(2002, new Product(2002,"Латте", 4.2));
        catalog.put(2003, new Product(2003,"Американо", 2.5));
        catalog.put(1001, new ProductFood(1001, "Маргарита", 10.2, "Томатный соус Domino's, Сыр моцарелла"));
        catalog.put(1002, new ProductFood(1002, "Пепперони", 12.8, "Томатный соус Domino's, Пепперони, Сыр моцарелла"));
        catalog.put(1003, new ProductFood(1003, "Фирменная", 15.2, "Пармезан, Курица, Буженина, Лук, Томатный соус Domino's, Бекон, Сладкий перец, Томаты, Сыр моцарелла, Телятина, Шампиньоны"));

    }

    public void Render() {
        for (Integer i : catalog.keySet()) {
            System.out.println(i + " " + catalog.get(i).ToString());
        }
    }

    public Product GetProduct(Integer productId) {
        return catalog.get(productId);
    }
}