package by.food.orders.data.writer;

import by.food.orders.entity.Order;
import by.food.orders.exception.DataException;
import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class OrderDataWriterImpl implements DataWriter<Order> {

    @Override
    public void writeJSONData(String filePath, List<Order> data) throws DataException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            Gson gson = new Gson();
            String ordersJSON = gson.toJson(data);
            writer.write(ordersJSON);
        } catch (IOException e) {
            throw new DataException(e.getMessage(), e);
        }
    }
}
