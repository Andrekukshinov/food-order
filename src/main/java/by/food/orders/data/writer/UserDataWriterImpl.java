package by.food.orders.data.writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import by.food.orders.entity.User;
import by.food.orders.exception.DataException;
import com.google.gson.Gson;

public class UserDataWriterImpl implements DataWriter<User> {

    @Override
    public void writeJSONData(String filePath, List<User> data) throws DataException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            Gson gson = new Gson();
            String ordersJSON = gson.toJson(data);
            writer.write(ordersJSON);
        } catch (IOException e) {
            throw new DataException(e.getMessage(), e);
        }
    }
}