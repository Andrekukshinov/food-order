package by.food.orders.data.storage;

import by.food.orders.entity.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderStorage {
    private static OrderStorage instance = null;

    private List<Order> orders = new ArrayList<>();

    private OrderStorage() {
    }

    public static OrderStorage getInstance() {
        if (instance == null) {
            OrderStorage local = new OrderStorage();
            instance = local;
        }
        return instance;
    }



    public void addOrder(Order order){
        orders.add(order);
    }


}
