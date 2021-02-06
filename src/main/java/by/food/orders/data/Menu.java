package by.food.orders.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Menu {
    public String showMenu() {
        StringBuilder builder = new StringBuilder("MENU\n");
        builder.append("1. Show the catalog");
        builder.append("\n");
        builder.append("2. Add product to the cart");
        builder.append("\n");
        builder.append("3. Show Cart Content");
        builder.append("\n");
        builder.append("4. Checkout your order");
        builder.append("\n");
        builder.append("5. Show your orders");
        builder.append("\n");
        builder.append("6. Exit");
        builder.append("\n");
        builder.append("===================================");
        return builder.toString();
    }
}

