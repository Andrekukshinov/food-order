package by.food.orders.entity;

public class User {
    private static long idCounter = 0;

    private final long id;
    private final String login;
    private final String password;

    public User(String login, String password) {
        id = idCounter++;
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public long getId() {
        return id;
    }
}
