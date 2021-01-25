package by.food.orders.data.dao.api;

import by.food.orders.entity.Order;
import by.food.orders.exception.DaoException;

import java.util.List;

public interface OrderDao {
    void save(Order order) throws DaoException;

    List<Order> getAll() throws DaoException;

    List<Order> getUserOrders(Long userId) throws DaoException;
}


