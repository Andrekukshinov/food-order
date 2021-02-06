package by.food.orders.data.cart;

import by.food.orders.entity.CartItem;
import by.food.orders.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CartManagerImpl implements CartManager {
    private static final String CART_OPENING = "=== Cart ===\n";
    private static final String LINE = "===============";
    private static final String EMPTY_CART = "Cart is empty\n";
    private Cart cart;

    public CartManagerImpl(Cart cart) {//ссылка на корзину
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
    public String render() {
        Map<Long, CartItem> products = cart.getProducts();
        StringBuilder result = new StringBuilder(CART_OPENING);
        if(products.isEmpty()) {
            result.append(EMPTY_CART);
        } else {
            for (Long i : products.keySet()) {
                result.append(products.get(i).toString());
                result.append("\n");
            }
        }
        result.append(LINE);
        return result.toString();
    }

    @Override
    public void clearCart() {
        Map<Long, CartItem> products = cart.getProducts();
        products.clear();
    }
}
