package by.food.orders.data.storage;

import by.food.orders.data.writer.UserDataWriterImpl;
import by.food.orders.entity.User;
import by.food.orders.exception.DataException;

import java.util.Collections;
import java.util.List;

public class UserStorage {

    private static UserStorage instance = null;

    // TODO: 04.01.2021 encode passwords
    private final List<User> users = List.of(
            new User("admin", "pass"),
            new User("Peter", "pass"),
            new User("Van", "pass"),
            new User("Bill", "pass"),
            new User("Lena", "1111"),
            new User("Tanya", "1111")
    );

    public static UserStorage getInstance() {
        if (instance == null) {
            UserStorage local = new UserStorage();
            instance = local;
        }
        return instance;
    }

    public void saveUsers(String outputFileName) throws DataException {
        UserDataWriterImpl userDataWriter = new UserDataWriterImpl();
        userDataWriter.writeJSONData(outputFileName, users);
    }

    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }
}
