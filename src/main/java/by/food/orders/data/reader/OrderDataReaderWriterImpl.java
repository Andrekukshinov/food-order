package by.food.orders.data.reader;

import by.food.orders.entity.Order;
import by.food.orders.exception.DataException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class OrderDataReaderWriterImpl implements DataReaderWriter<Order> {

    @Override
    public List<String> readData(String filePath) throws DataException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            List<String> result = new ArrayList<>();
            String currentOrder = reader.readLine();
            while (currentOrder != null) {
                result.add(currentOrder);
                currentOrder = reader.readLine();
            }
            return result;
        } catch (IOException e) {
            throw new DataException(e.getMessage(), e);
        }
    }

    @Override
    public List<Order> readJSONData(String filePath) throws DataException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String resultJSONStr = reader.readLine();
            Type listType = new TypeToken<List<Order>>(){}.getType();
            List<Order> resultList = new Gson().fromJson(resultJSONStr, listType);
            return resultList;
        } catch (IOException e) {
            throw new DataException(e.getMessage(), e);
        }
    }

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
