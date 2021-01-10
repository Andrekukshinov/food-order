package by.food.orders.data.dao;

import by.food.orders.data.dao.api.UserDao;
import by.food.orders.data.storage.UserStorage;
import by.food.orders.entity.User;

import java.util.List;
import java.util.Optional;

public class UserStorageDao implements UserDao {

    private final UserStorage userStorage;

    public UserStorageDao() {
        userStorage = UserStorage.getInstance();
    }

    @Override
    public Optional<User> findByCredentials(String login, String pass) {
        List<User> users = userStorage.getUsers();
        Optional<User> foundUser = users
                .stream()
                .filter(user -> pass.equals(user.getPassword()) && login.equals(user.getLogin()))
                .findFirst();
        return foundUser;
    }


}
