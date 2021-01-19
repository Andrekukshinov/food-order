package by.food.orders.data.dao;

import by.food.orders.data.dao.api.OrderDao;
import by.food.orders.data.storage.OrderStorage;
import by.food.orders.entity.Order;
import by.food.orders.exception.DaoException;
import by.food.orders.data.reader.OrderDataReaderWriterImpl;
import by.food.orders.data.reader.DataReaderWriter;
import by.food.orders.exception.DataException;
import java.util.List;
import java.util.stream.Collectors;

public class OrderStorageDaoImpl implements OrderDao {
    private String outputFileName = "./src/data.source/orders.json";
    private final OrderStorage orderStorage;

    public OrderStorageDaoImpl() {
        orderStorage = OrderStorage.getInstance();
    }

    @Override
    public void save(Order order) throws DaoException {
        orderStorage.addOrder(order);
    }

    @Override
    public void saveToFile() throws DaoException {
        try {
            DataReaderWriter dataWriter = new OrderDataReaderWriterImpl();
            List<Order> orders = orderStorage.getOrders();
            dataWriter.writeJSONData(outputFileName, orders);
            System.out.println("Your order was saved successfully");
        } catch (DataException e) {
            e.printStackTrace();
        };
    };

    @Override
    public List<Order> loadFromFile() throws DaoException {
        try{
            DataReaderWriter dataReader = new OrderDataReaderWriterImpl();
            List<Order> resultList = dataReader.readJSONData(outputFileName);
            orderStorage.setOrders(resultList);
        } catch (DataException e) {
            e.printStackTrace();
        }
        return null;
    };

    @Override
    public List<Order> getUserOrders(Long userId) {
        List<Order> userOrders = orderStorage.getOrders()
                .stream()
                .filter(order -> order.getOwnerId() == userId)
                .collect(Collectors.toList());
        return userOrders;
    };
}
