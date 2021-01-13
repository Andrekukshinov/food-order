package by.food.orders.data.storage;

import by.food.orders.entity.Product;
import by.food.orders.entity.ProductFood;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Catalog {
    private Map<Long, Product> catalog = new HashMap<>();

    Product prod1 = new Product(2001L, "Капучино", new BigDecimal("3.5"));
    Product prod2 = new Product(2002L, "Латте", new BigDecimal("4.2"));
    Product prod3 = new Product(2003L, "Американо", new BigDecimal("2.5"));
    ProductFood prodF1 = new ProductFood(1001L, "Маргарита", new BigDecimal("10.2"), "Томатный соус Domino's, Сыр моцарелла");
    ProductFood prodF2 = new ProductFood(1002L, "Пепперони", new BigDecimal("12.8"), "Томатный соус Domino's, Пепперони, Сыр моцарелла");
    ProductFood prodF3 = new ProductFood(1003L, "Фирменная", new BigDecimal("15.2"), "Пармезан, Курица, Буженина, Лук, Томатный соус Domino's, Бекон, Сладкий перец, Томаты, Сыр моцарелла, Телятина, Шампиньоны");

    public Catalog() {
        catalog.put(prod1.getId(), prod1);
        catalog.put(prod2.getId(), prod2);
        catalog.put(prod3.getId(), prod3);
        catalog.put(prodF1.getId(), prodF1);
        catalog.put(prodF2.getId(), prodF2);
        catalog.put(prodF3.getId(), prodF3);
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
