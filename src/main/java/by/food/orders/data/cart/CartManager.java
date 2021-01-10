package by.food.orders.data.cart;

import by.food.orders.entity.CartItem;
import by.food.orders.entity.Product;

import java.util.List;

public interface CartManager {
    void addToCart(Product product);

    List<CartItem> getCartItems();

    void render();

    void clearCart();
}
