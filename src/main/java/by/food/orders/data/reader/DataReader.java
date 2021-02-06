package by.food.orders.data.reader;

import by.food.orders.entity.Order;
import by.food.orders.exception.DataException;

import java.util.List;

public interface DataReader<T> {

    List<T> readJSONData(String filePath) throws DataException;
}
