package by.food.orders.logics;

import by.food.orders.data.dao.api.UserDao;
import by.food.orders.entity.User;
import by.food.orders.exception.NoSuchUserException;

import java.util.Optional;

public class AuthenticationManager {
    private final UserDao userDao;

    public AuthenticationManager(UserDao userDao) {
        this.userDao = userDao;
    }

    public User authenticateByCredentials(String login, String pass) throws NoSuchUserException {
        Optional<User> optionalUser = userDao.findByCredentials(login, pass);
        User user = optionalUser.orElseThrow(() -> new NoSuchUserException("user is not found!"));
        return user;
    }
}
