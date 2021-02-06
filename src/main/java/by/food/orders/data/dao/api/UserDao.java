package by.food.orders.data.dao.api;

import by.food.orders.entity.User;
import by.food.orders.exception.DaoException;
import by.food.orders.exception.DataException;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    Optional<User> findByCredentials(String login, String pass) throws DaoException; /**/

}
