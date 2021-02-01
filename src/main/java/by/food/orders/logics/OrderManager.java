package by.food.orders.logics;

import by.food.orders.entity.Product;
import by.food.orders.data.cart.CartManager;
import by.food.orders.entity.CartItem;
import by.food.orders.entity.Order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;



public class OrderManager {

    public Order getCartOrder(CartManager cartManager, LocalDate deliveryDate, Long buyerId) {
        List<CartItem> cartItems = cartManager.getCartItems();
        LocalDate orderDate = LocalDate.now();
        List<Long> productIds = new ArrayList<>();
        BigDecimal totalPrice = getTotalPrice(cartItems);
        addProductsToCart(cartItems, productIds);
        int id = (int) ( Math.random() * 600 );
        return new Order(id, orderDate, deliveryDate, buyerId, productIds, totalPrice);
    }

    private void addProductsToCart(List<CartItem> cartItems, List<Long> productIds) {
        cartItems.forEach(item -> {
            Integer count = item.getCount();
            IntStream
                    .range(0, count)
                    .forEach(itemCounter -> {
                        Product product = item.getProduct();
                        productIds.add(product.getId());
                    });
        });
    }

    private BigDecimal getTotalPrice(List<CartItem> cartItems) {
        BigDecimal totalPrice = cartItems
                .stream()
                .map(item->{
                    Integer count = item.getCount();
                    BigDecimal totalItemPrice = BigDecimal.ZERO;
                    for (int runner = 0; runner < count; ++runner) {
                        BigDecimal price = item.getProduct().getPrice();
                        totalItemPrice = totalItemPrice.add(price);
                    }
                    return totalItemPrice;
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return totalPrice;
    }
}

