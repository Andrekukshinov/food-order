package by.food.orders.data.cart;

import by.food.orders.entity.CartItem;
import by.food.orders.application.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CartManagerImpl implements CartManager {
    private Cart cart;

    public CartManagerImpl(Cart cart) {
        this.cart = cart;
    }

    @Override
    public void addToCart(Product product) {
        Map<Long, CartItem> products = cart.getProducts();
        Long productId = product.getId();
        if (products.containsKey(productId)) {
            CartItem cartItem = products.get(productId);
            cartItem.increaseAmount();
            products.put(productId, cartItem);
        }
        else {
            products.put(productId, new CartItem(product, 1));
        }
    }

    @Override
    public List<CartItem> getCartItems() {
        Map<Long, CartItem> products = cart.getProducts();
        Set<Long> keySet = products.keySet();
        List<CartItem> items = new ArrayList<>();
        keySet.forEach(key -> {
            CartItem cartItem = products.get(key);
            items.add(cartItem);
        });
        return new ArrayList<>(items);
    }

    @Override
    public void render() {
        Map<Long, CartItem> products = cart.getProducts();
        System.out.println("=== КОРЗИНА ===");
        if(products.isEmpty()) {
            System.out.println("Корзина пустая. Нажмите 1 для перехода в каталог");
        } else {
            for (Long i : products.keySet()) {
                System.out.println(products.get(i).toString());
            }
        }
        System.out.println("===============");
    }

    @Override
    public void clearCart() {
        Map<Long, CartItem> products = cart.getProducts();
        products.clear();
    }
}
