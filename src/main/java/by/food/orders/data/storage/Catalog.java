package by.food.orders.data.storage;

import by.food.orders.application.Product;
import by.food.orders.application.ProductFood;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Catalog {
    private Map<Long, Product> catalog = new HashMap<>();

    public Catalog() {
        Product capuchino = new Product(2001L, "Капучино", 3.5);
        catalog.put(capuchino.getId(), capuchino);
        catalog.put(2002L, new Product(2002L, "Латте", 4.2));
        catalog.put(2003L, new Product(2003L, "Американо", 2.5));
        catalog.put(1001L, new ProductFood(1001L, "Маргарита", 10.2, "Томатный соус Domino's, Сыр моцарелла"));
        catalog.put(1002L, new ProductFood(1002L, "Пепперони", 12.8, "Томатный соус Domino's, Пепперони, Сыр моцарелла"));
        catalog.put(1003L, new ProductFood(1003L, "Фирменная", 15.2, "Пармезан, Курица, Буженина, Лук, Томатный соус Domino's, Бекон, Сладкий перец, Томаты, Сыр моцарелла, Телятина, Шампиньоны"));
    }

//    todo move methods
    public void render() {
        for (Long i : catalog.keySet()) {
            System.out.println(i + " " + catalog.get(i).toString());
        }
    }

    public Optional<Product> getProduct(Long productId) {
        Product value = catalog.get(productId);
        return Optional.ofNullable(value);
    }
}
