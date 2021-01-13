package by.food.orders.data.cart;
import by.food.orders.entity.CartItem;

import java.util.*;

public class Cart {
    private final Map<Long, CartItem> products;

    public Cart() {
        products = new HashMap<>();
    }

    Map<Long, CartItem> getProducts() {
        return products;
    }
}
