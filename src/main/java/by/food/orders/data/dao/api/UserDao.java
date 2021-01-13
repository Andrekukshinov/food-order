package by.food.orders.data.dao.api;

import by.food.orders.entity.User;

import java.util.Optional;

public interface UserDao {
    Optional<User> findByCredentials(String login, String pass);
}
