package by.food.orders.entity;

import java.time.LocalDate;
import java.util.List;

public class Order {
    private long id;
    private final LocalDate orderDate;
    private final LocalDate deliveryDate;
    private long orderOwnerId;
    private List<Long> foodListId;


    public Order(
			long id, LocalDate orderDate, LocalDate deliveryLocalDate, long orderOwnerId,
			List<Long> foodListId) {
	   this.id = id;
		this.orderDate = orderDate;
		this.deliveryDate = deliveryLocalDate;
	   this.orderOwnerId = orderOwnerId;
	   this.foodListId = foodListId;
    }

    public long getId() {
	   return id;
    }

    public void setId(long id) {
	   this.id = id;
    }

    public LocalDate getOrderLocalDate() {
	   return orderDate;
    }

    public LocalDate getDeliveryLocalDate() {
	   return deliveryDate;
    }

    public long getOrderOwnerId() {
	   return orderOwnerId;
    }

    public void setOrderOwnerId(long orderOwnerId) {
	   this.orderOwnerId = orderOwnerId;
    }

    public List<Long> getFoodListId() {
	   return foodListId;
    }

    public void setFoodListId(List<Long> foodListId) {
	   this.foodListId = foodListId;
    }

    @Override
    public String toString() {
	   return "id:" + id + ", orderDate:" + orderDate + ", deliveryDate:" + deliveryDate + ", orderOwnerId:" + orderOwnerId + ", foodListId:" + foodListId;
    }

    @Override
    public boolean equals(Object o) {
	   if (this == o) {
		  return true;
	   }
	   if (o == null || getClass() != o.getClass()) {
		  return false;
	   }

	   Order order = (Order) o;

	   if (getId() != order.getId()) {
		  return false;
	   }
	   if (getOrderOwnerId() != order.getOrderOwnerId()) {
		  return false;
	   }
	   if (getOrderLocalDate() != null ? !getOrderLocalDate().equals(order.getOrderLocalDate()) : order
			 .getOrderLocalDate() != null) {
		  return false;
	   }
	   if (getDeliveryLocalDate() != null ? !getDeliveryLocalDate()
			 .equals(order.getDeliveryLocalDate()) : order.getDeliveryLocalDate() != null) {
		  return false;
	   }
	   return getFoodListId() != null ? getFoodListId()
			 .equals(order.getFoodListId()) : order.getFoodListId() == null;
    }

    @Override
    public int hashCode() {
	   int result = (int) (getId() ^ (getId() >>> 32));
	   result = 31 * result + (getOrderLocalDate() != null ? getOrderLocalDate().hashCode() : 0);
	   result = 31 * result + (getDeliveryLocalDate() != null ? getDeliveryLocalDate()
			 .hashCode() : 0);
	   result = 31 * result + (int) (getOrderOwnerId() ^ (getOrderOwnerId() >>> 32));
	   result = 31 * result + (getFoodListId() != null ? getFoodListId().hashCode() : 0);
	   return result;
    }
}
