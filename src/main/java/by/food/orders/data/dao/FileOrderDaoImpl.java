package by.food.orders.data.dao;

import by.food.orders.data.dao.api.OrderDao;
import by.food.orders.data.reader.DataReader;
import by.food.orders.data.reader.OrderDataReaderImpl;
import by.food.orders.data.writer.DataWriter;
import by.food.orders.data.writer.OrderDataWriterImpl;
import by.food.orders.entity.Order;
import by.food.orders.exception.DaoException;
import by.food.orders.exception.DataException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileOrderDaoImpl implements OrderDao {
    private String outputFileName = "src/data/source/orders.json";

    //wrong
    @Override
    public void save(Order order) throws DaoException {
        try {
            DataWriter<Order> dataWriter = new OrderDataWriterImpl();
            List<Order> orders = getAll();
            orders.add(order);
            dataWriter.writeJSONData(outputFileName, orders);
            System.out.println("Your order was saved successfully");
        } catch (DataException e) {
            throw new DaoException(e.getMessage(), e);
        }

    }

    @Override
    public List<Order> getAll() throws DaoException {
        try {
            DataReader<Order> dataReader = new OrderDataReaderImpl();
            List<Order> orders = dataReader.readJSONData(outputFileName);
            return orders != null ? orders : new ArrayList<>();
        } catch (DataException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    ;

    @Override
    public List<Order> getUserOrders(Long userId) throws DaoException {
        List<Order> userOrders = getAll()
                .stream()
                .filter(order -> order.getOwnerId() == userId)
                .collect(Collectors.toList());
        return userOrders;
    }
}
