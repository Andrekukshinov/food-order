package by.food.orders.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Order {
	private static int idCounter = 0;

    private long id;
    private final LocalDate orderDate;
    private final LocalDate deliveryDate;
    private long orderOwnerId;
    private List<Long> foodListId;
    private BigDecimal totalPrice;


    public Order(
			long id, LocalDate orderDate, LocalDate deliveryLocalDate, long orderOwnerId,
			List<Long> foodListId, BigDecimal totalPrice) {
	   this.id = id;
	   this.orderDate = orderDate;
	   this.deliveryDate = deliveryLocalDate;
	   this.orderOwnerId = orderOwnerId;
	   this.foodListId = foodListId;
	   this.totalPrice = totalPrice;
	}

	public Order(LocalDate orderDate, LocalDate deliveryDate, long orderOwnerId, List<Long> foodListId, BigDecimal totalPrice) {
		id = idCounter++;
    	this.orderDate = orderDate;
		this.deliveryDate = deliveryDate;
		this.orderOwnerId = orderOwnerId;
		this.foodListId = foodListId;
		this.totalPrice = totalPrice;
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

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
    public String toString() {
	   return "id: " + id + ";\n price: " + totalPrice + " BYN;\n orderDate:" + orderDate + ";\n deliveryDate:" + deliveryDate + ";\n orderOwnerId:" + orderOwnerId + ";\n foodListId:" + foodListId;
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
		if (orderDate != null ? !orderDate.equals(order.orderDate) : order.orderDate != null) {
			return false;
		}
		if (deliveryDate != null ? !deliveryDate.equals(order.deliveryDate) : order.deliveryDate != null) {
			return false;
		}
		if (getFoodListId() != null ? !getFoodListId().equals(order.getFoodListId()) : order.getFoodListId() != null) {
			return false;
		}
		return getTotalPrice() != null ? getTotalPrice().equals(order.getTotalPrice()) : order.getTotalPrice() == null;
	}

	@Override
	public int hashCode() {
		int result = (int) (getId() ^ (getId() >>> 32));
		result = 31 * result + (orderDate != null ? orderDate.hashCode() : 0);
		result = 31 * result + (deliveryDate != null ? deliveryDate.hashCode() : 0);
		result = 31 * result + (int) (getOrderOwnerId() ^ (getOrderOwnerId() >>> 32));
		result = 31 * result + (getFoodListId() != null ? getFoodListId().hashCode() : 0);
		result = 31 * result + (getTotalPrice() != null ? getTotalPrice().hashCode() : 0);
		return result;
	}

	public long getOwnerId() {
		return orderOwnerId;
	}
}
