package by.food.orders.application;
import java.lang.*;

public class Product {
    protected String name;
    protected Double price;
    private Integer id;

    public Integer GetId() {
        return id;
    }

    public Product(Integer id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String ToString() {
        return this.name.toUpperCase() + " --- Цена: " + this.price + "BYN";
    }
}