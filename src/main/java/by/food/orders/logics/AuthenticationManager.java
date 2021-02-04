package by.food.orders.logics;

import by.food.orders.data.dao.api.UserDao;
import by.food.orders.entity.User;
import by.food.orders.exception.DaoException;
import by.food.orders.exception.DataException;
import by.food.orders.exception.NoSuchUserException;

import java.util.Optional;
import java.util.Scanner;

public class AuthenticationManager {
    private final UserDao userDao;

    public AuthenticationManager(UserDao userDao) {
        this.userDao = userDao;
    }


    public User authorize() {
        Scanner scanner = new Scanner(System.in);
        User user = null;
        boolean isNotAuthenticated = true;
        do {
            System.out.println("Before making orders you should log in");
            System.out.println("Your login:");
            String login = scanner.nextLine();
            System.out.println("Your pass:");
            String pass = scanner.nextLine();
            try {
                user = authenticateByCredentials(login, pass);
            } catch (NoSuchUserException | DaoException e) {
                System.out.println(e.getMessage());
                System.out.println("Try again, please");
                continue;
            }
            isNotAuthenticated = false;
        } while (isNotAuthenticated);
        return user;
    }

    private User authenticateByCredentials(String login, String pass) throws NoSuchUserException, DaoException {
        Optional<User> optionalUser = userDao.findByCredentials(login, pass);
        return optionalUser.orElseThrow(() -> new NoSuchUserException("user is not found!"));
    }
}
