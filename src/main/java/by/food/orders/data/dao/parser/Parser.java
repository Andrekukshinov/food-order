package by.food.orders.data.dao.parser;

import by.food.orders.exception.DataException;

public interface Parser<T> {
    T parse(String stringForParsing) throws DataException;
}
