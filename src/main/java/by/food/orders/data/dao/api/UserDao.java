package by.food.orders.data.dao.api;

import by.food.orders.entity.User;

import java.util.Optional;
/*Optional — объект-контейнер, используемый для хранения ненулевых объектов.
Необязательный объект используется для представления нулевого значения с отсутствующим значением.*/

public interface UserDao {
    Optional<User> findByCredentials(String login, String pass); /**/
}
