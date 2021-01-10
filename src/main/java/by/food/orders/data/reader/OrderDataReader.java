package by.food.orders.data.reader;

import by.food.orders.exception.DataException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderDataReader implements DataReader {

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
}
