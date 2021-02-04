package by.food.orders.data.dao.api;

import by.food.orders.entity.User;
import by.food.orders.exception.DaoException;
import by.food.orders.exception.DataException;

import java.util.List;
import java.util.Optional;
/*Optional — объект-контейнер, используемый для хранения ненулевых объектов.
Необязательный объект используется для представления нулевого значения с отсутствующим значением.*/

public interface UserDao {
    Optional<User> findByCredentials(String login, String pass) throws DaoException; /**/

//    void saveUsersToFile() throws DaoException, DataException;

    List<User> getUserFromFile() throws DaoException;
}
