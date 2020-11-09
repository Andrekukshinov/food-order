package by.food.orders.entity;

import java.sql.Array;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

public class Order {
    private long id;
    private Date orderDate;
    private Date deliveryDate;
    private long orderOwnerId;
    private List<Long> foodListId;


    public Order(
		  long id, Date orderDate, Date deliveryDate, long orderOwnerId,
		  List<Long> foodListId) {
	   this.id = id;
	   this.orderDate = orderDate;
	   this.deliveryDate = deliveryDate;
	   this.orderOwnerId = orderOwnerId;
	   this.foodListId = foodListId;
    }

    public Order() {
    }

    public long getId() {
	   return id;
    }

    public void setId(long id) {
	   this.id = id;
    }

    public Date getOrderDate() {
	   return orderDate;
    }

    public void setOrderDate(Date orderDate) {
	   this.orderDate = orderDate;
    }

    public Date getDeliveryDate() {
	   return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
	   this.deliveryDate = deliveryDate;
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
	   if (getOrderDate() != null ? !getOrderDate().equals(order.getOrderDate()) : order
			 .getOrderDate() != null) {
		  return false;
	   }
	   if (getDeliveryDate() != null ? !getDeliveryDate()
			 .equals(order.getDeliveryDate()) : order.getDeliveryDate() != null) {
		  return false;
	   }
	   return getFoodListId() != null ? getFoodListId()
			 .equals(order.getFoodListId()) : order.getFoodListId() == null;
    }

    @Override
    public int hashCode() {
	   int result = (int) (getId() ^ (getId() >>> 32));
	   result = 31 * result + (getOrderDate() != null ? getOrderDate().hashCode() : 0);
	   result = 31 * result + (getDeliveryDate() != null ? getDeliveryDate()
			 .hashCode() : 0);
	   result = 31 * result + (int) (getOrderOwnerId() ^ (getOrderOwnerId() >>> 32));
	   result = 31 * result + (getFoodListId() != null ? getFoodListId().hashCode() : 0);
	   return result;
    }
}
