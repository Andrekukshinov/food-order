package by.food.orders.entity;
import java.lang.*;
import java.math.BigDecimal;

public class Product {
    protected String name;
    protected BigDecimal price;
    private Long id;

    public Product(Long id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Product product = (Product) o;

        if (name != null ? !name.equals(product.name) : product.name != null) {
            return false;
        }
        if (price != null ? !price.equals(product.price) : product.price != null) {
            return false;
        }
        return getId() != null ? getId().equals(product.getId()) : product.getId() == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (getId() != null ? getId().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return this.name.toUpperCase() + " --- Price: " + this.price + "BYN";
    }


}
