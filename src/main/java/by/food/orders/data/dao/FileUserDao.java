package by.food.orders.data.dao;

import by.food.orders.data.dao.api.UserDao;
import by.food.orders.data.reader.DataReader;
import by.food.orders.data.reader.UserDataReaderImpl;
import by.food.orders.entity.User;
import by.food.orders.exception.DaoException;
import by.food.orders.exception.DataException;

import java.util.List;
import java.util.Optional;

public class FileUserDao implements UserDao {
    private final String filePath;

    public FileUserDao(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public Optional<User> findByCredentials(String login, String pass) throws DaoException {
        DataReader<User> userDataReader = new UserDataReaderImpl();
        try {
            List<User> users = userDataReader.readJSONData(filePath);
            return users
                    .stream()
                    .filter(user -> user.getLogin().equals(login) && user.getPassword().equals(pass))
                    .findFirst();
        } catch (DataException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }


    @Override
    public List<User> getUserFromFile() throws DaoException {
        return null;
    }
}
