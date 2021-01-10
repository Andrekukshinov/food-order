package by.food.orders.application;
import java.lang.*;

public class ProductFood extends Product {
    private final String description;

    public ProductFood(Long id, String name, Double price, String description){
        super(id, name, price);
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        ProductFood that = (ProductFood) o;

        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return this.name.toUpperCase() + " --- Цена: " + this.price + "BYN --- " + this.description;
    }
}
