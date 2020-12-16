package by.food.orders.application;
import java.lang.*;

public class Product {
    protected String name;
    protected Double price;

    public Product(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public String printData() {
        return this.name.toUpperCase() + " --- Цена: " + this.price + "BYN";
    }
}