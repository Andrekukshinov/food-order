package by.food.orders.data.reader;

import by.food.orders.data.DataException;
import by.food.orders.entity.Order;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDataReader implements DataReader {

    @Override
    public List<String> readData(String filePath) throws DataException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
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
