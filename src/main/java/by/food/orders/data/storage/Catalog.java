package by.food.orders.data.storage;

import by.food.orders.entity.Product;
import by.food.orders.entity.ProductFood;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Catalog {
    private Map<Long, Product> catalog = new HashMap<>();

    Product prod1 = new Product(2001L, "Cappuccino", new BigDecimal("3.5"));
    Product prod2 = new Product(2002L, "Latte", new BigDecimal("4.2"));
    Product prod3 = new Product(2003L, "Americano", new BigDecimal("2.5"));
    ProductFood prodF1 = new ProductFood(1001L, "Margarita pizza", new BigDecimal("10.2"), "Томатный соус Domino's, Сыр моцарелла");
    ProductFood prodF2 = new ProductFood(1002L, "Pepperoni pizza", new BigDecimal("12.8"), "Томатный соус Domino's, Пепперони, Сыр моцарелла");
    ProductFood prodF3 = new ProductFood(1003L, "Hawaiian pizza", new BigDecimal("15.2"), "Пармезан, Курица, Буженина, Лук, Томатный соус Domino's, Бекон, Сладкий перец, Томаты, Сыр моцарелла, Телятина, Шампиньоны");

    public Catalog() {
        catalog.put(prod1.getId(), prod1);
        catalog.put(prod2.getId(), prod2);
        catalog.put(prod3.getId(), prod3);
        catalog.put(prodF1.getId(), prodF1);
        catalog.put(prodF2.getId(), prodF2);
        catalog.put(prodF3.getId(), prodF3);
    }

//    todo move methods
    public String render() {
        StringBuilder result = new StringBuilder();
        for (Long i : catalog.keySet()) {
            result.append(i).append(" ").append(catalog.get(i).toString());
            result.append("\n");
        }
        return result.toString();
    }

    public Optional<Product> getProduct(Long productId) {
        Product value = catalog.get(productId);
        return Optional.ofNullable(value);
    }
}
