package by.food.orders.application;

public class CartItem {
    private Integer count;
    private Product product;

    CartItem(Product product, Integer count) {
        this.product = product;
        this.count = count;
    }

    public void Add() {
        count++;
    }

    public void Remove() {
        if (count > 0)
            count--;
    }

    public String ToString() {
        return String.format("Продукт: %s, штук %d", product.ToString(), count);
    }
}
