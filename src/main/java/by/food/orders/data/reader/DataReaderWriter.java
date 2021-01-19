package by.food.orders.data.reader;

import by.food.orders.entity.Order;
import by.food.orders.exception.DataException;

import java.util.List;

public interface DataReaderWriter<T> {

    List<String> readData(String filePath) throws DataException;

    void writeJSONData(String filePath, List<T> data) throws DataException;
    List<Order> readJSONData(String filePath) throws DataException;
}
