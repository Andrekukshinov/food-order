package by.food.orders.data.dao;

import by.food.orders.data.dao.api.UserDao;
import by.food.orders.data.reader.DataReader;
import by.food.orders.data.reader.UserDataReaderImpl;
import by.food.orders.data.storage.UserStorage;
import by.food.orders.entity.User;
import by.food.orders.exception.DaoException;
import by.food.orders.exception.DataException;

import java.util.List;
import java.util.Optional;

public class UserStorageDao implements UserDao {

    private static final String outputFileName = "src/data/source/users.json";
    private final UserStorage userStorage;

    public UserStorageDao() throws DataException {
        userStorage = UserStorage.getInstance();
        saveUsersToFile();
    }

    @Override
    public Optional<User> findByCredentials(String login, String pass) throws DaoException {
        List<User> users = getUserFromFile();
        Optional<User> foundUser = users
                .stream()
                .filter(user -> pass.equals(user.getPassword()) && login.equals(user.getLogin()))
                .findFirst();
        return foundUser;
    }

    @Override
    public void saveUsersToFile() throws DataException {
        userStorage.saveUsers(outputFileName);
    }

    @Override
    public List<User> getUserFromFile() throws DaoException{
        try {
            UserDataReaderImpl dataReader = new UserDataReaderImpl();
            return dataReader.readJSONData(outputFileName);
        }
        catch (DataException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }
}
