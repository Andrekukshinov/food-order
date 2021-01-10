package by.food.orders.data.parser;

import by.food.orders.exception.DataException;

public interface Parser<T> {
    T parse(String stringForParsing) throws DataException;
}