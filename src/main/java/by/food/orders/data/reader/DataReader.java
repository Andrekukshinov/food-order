package by.food.orders.data.reader;

import by.food.orders.data.DataException;

import java.util.List;

public interface DataReader {
    List<String> readData(String filePath) throws DataException;
}
