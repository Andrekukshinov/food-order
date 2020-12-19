package by.food.orders.application;
import java.lang.*;

public class ProductFood extends Product {
    private String description;

    ProductFood(Integer id, String name, Double price, String description){
        super(id, name, price); //вызов родительского конструктора с аргументами
        this.description = description;
    }

    @Override
    public String ToString() {
        return this.name.toUpperCase() + " --- Цена: " + this.price + "BYN --- " + this.description;
    }
}