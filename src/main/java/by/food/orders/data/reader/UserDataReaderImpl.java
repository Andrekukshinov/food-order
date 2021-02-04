package by.food.orders.data.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import by.food.orders.entity.User;
import by.food.orders.exception.DataException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class UserDataReaderImpl implements DataReader<User> {


    @Override
    public List<String> readData(String filePath) {
        return null;
    }

    @Override
    public List<User> readJSONData(String filePath) throws DataException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder result = new StringBuilder();
            String currentOrder = reader.readLine();
            while (currentOrder != null) {
                result.append(currentOrder);
                currentOrder = reader.readLine();
            }
            String resultJSONStr = result.toString();
            Type listType = new TypeToken<List<User>>() {
            }.getType();
            return new Gson().fromJson(resultJSONStr, listType);
        } catch (IOException e) {
            throw new DataException(e.getMessage(), e);
        }
    }
}
