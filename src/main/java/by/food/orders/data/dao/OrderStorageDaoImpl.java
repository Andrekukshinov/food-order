package by.food.orders.data.dao;

import by.food.orders.data.dao.api.OrderDao;
import by.food.orders.data.storage.OrderStorage;
import by.food.orders.entity.Order;
import by.food.orders.exception.DaoException;

public class OrderStorageDaoImpl implements OrderDao {

    private final OrderStorage orderStorage;

    public OrderStorageDaoImpl() { orderStorage = OrderStorage.getInstance();}

    @Override
    public void save(Order order) throws DaoException {
        orderStorage.addOrder(order);
    }

}
