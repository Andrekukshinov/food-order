package by.food.orders.data.writer;

import by.food.orders.exception.DataException;
import java.util.List;

public interface DataWriter<T> {

    void writeJSONData(List<T> data) throws DataException;
}
