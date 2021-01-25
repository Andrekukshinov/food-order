package by.food.orders.data.writer;

import by.food.orders.exception.DataException;
import java.util.List;

public interface DataWriter<T> {

    void writeJSONData(String filePath, List<T> data) throws DataException;
}
