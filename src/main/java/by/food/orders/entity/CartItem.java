package by.food.orders.entity;

public class CartItem {
    private Integer count;
    private final Product product;

    public CartItem(Product product, Integer count) {
        this.product = product;
        this.count = count;
    }

    public void increaseAmount() {
        count++;
    }

    public void decreaseAmount() {
        if (count > 0)
            count--;
    }

    public Product getProduct() {
        return product;
    }

    public Integer getCount() {
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CartItem cartItem = (CartItem) o;

        if (count != null ? !count.equals(cartItem.count) : cartItem.count != null) {
            return false;
        }
        if (product != null ? !product.equals(cartItem.product) : cartItem.product != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = count != null ? count.hashCode() : 0;
        result = 31 * result + (product != null ? product.hashCode() : 0);
        return result;
    }

    public String toString() {
        return String.format("Item name: %s, amount - %d", product.toString(), count);
    }
}
