package by.food.orders.application;

import by.food.orders.data.cart.CartManager;
import by.food.orders.entity.CartItem;
import by.food.orders.entity.Order;
import by.food.orders.entity.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OrderManager {

    public Order getCartOrder(CartManager cartManager, LocalDate deliveryDate, Long buyerId) {
        List<CartItem> cartItems = cartManager.getCartItems();
        LocalDate orderDate = LocalDate.now();
        List<Long> productIds = new ArrayList<>();
        cartItems.forEach(item -> {
            Integer count = item.getCount();
            IntStream.range(0, count).forEach(itemCounter->{
                Product product = item.getProduct();
                productIds.add(product.getId());
            });
        });
        //fixme change id value
        int id = -1;
        return new Order(id, orderDate, deliveryDate, buyerId, productIds);
    }
}

