package by.food.orders.data.storage;

import by.food.orders.entity.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderStorage {
    private static OrderStorage instance = null;

    private List<Order> orders = new ArrayList<>();
    private OrderStorage() {}  //паттерн Singleton

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

    public void setOrders(List<Order> orderList) {
        for (Order order : orderList) {
            addOrder(order);
        }
    }

    public List<Order> getOrders(){
        return this.orders;
    }

    public void clear() {
        this.orders = new ArrayList<>();
    }
}
