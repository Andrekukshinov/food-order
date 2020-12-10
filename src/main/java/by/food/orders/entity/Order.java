package by.food.orders.entity;

import java.util.Date;
import java.util.List;

public class Order {
	private long orderId;
	private Date orderDate;
	private Date deliveryDate;
	private long orderOwnerId;
	//это эквивалент массива, только в него можно добавлять и удалять элементы
	private List<Long> foodListId;


	public Order(
			long orderId, Date orderDate, Date deliveryDate, long orderOwnerId,
			List<Long> foodListId) {
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.deliveryDate = deliveryDate;
		this.orderOwnerId = orderOwnerId;
		this.foodListId = foodListId;
	}

	public Order() {
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
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
		return "orderId:" + orderId + "; orderDate:" + orderDate + "; deliveryDate:" + deliveryDate + "; orderOwnerId:" + orderOwnerId + "; foodListId:" + foodListId + ";";
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

		if (getOrderId() != order.getOrderId()) {
			return false;
		}
		if (getOrderOwnerId() != order.getOrderOwnerId()) {
			return false;
		}
		if (getOrderDate() != null ? !getOrderDate().equals(order.getOrderDate()) : order.getOrderDate() != null) {
			return false;
		}
		if (getDeliveryDate() != null ? !getDeliveryDate().equals(order.getDeliveryDate()) : order.getDeliveryDate() != null) {
			return false;
		}
		return getFoodListId() != null ? getFoodListId().equals(order.getFoodListId()) : order.getFoodListId() == null;
	}

	@Override
	public int hashCode() {
		int result = (int) (getOrderId() ^ (getOrderId() >>> 32));
		result = 31 * result + (getOrderDate() != null ? getOrderDate().hashCode() : 0);
		result = 31 * result + (getDeliveryDate() != null ? getDeliveryDate().hashCode() : 0);
		result = 31 * result + (int) (getOrderOwnerId() ^ (getOrderOwnerId() >>> 32));
		result = 31 * result + (getFoodListId() != null ? getFoodListId().hashCode() : 0);
		return result;
	}
}
