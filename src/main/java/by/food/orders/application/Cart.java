package by.food.orders.application;
import java.util.HashMap;

public class Cart {
    private HashMap<Integer, CartItem> products = new HashMap<Integer, CartItem>();

    public void Render() {
        System.out.println("=== КОРЗИНА ===");
        if(products.isEmpty()) {
            System.out.println("Корзина пустая. Нажмите 1 для перехода в каталог");
        } else {
            for (Integer i : products.keySet()) {
                System.out.println(products.get(i).ToString());
            }
        }
        System.out.println("===============");
    }

    public void AddToCart(Product product) {
        Integer productId = product.GetId();
        if (products.containsKey(productId)) {
            CartItem cartItem = products.get(productId);
            cartItem.Add();
            products.put(productId, cartItem);
        }
        else {
            products.put(productId, new CartItem(product, 1));
        }
    }

    public void Clear() {
        products.clear();
    }
}
