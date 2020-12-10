package by.food.orders.data.reader;

import by.food.orders.data.DataException;

import java.util.List;

public interface DataReader {
    //это эквивалент массива, только в него можно добавлять и удалять элементы
    List<String> readData(String filePath) throws DataException;
}
