package com.pizza.store.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Reeta Wani
 * This POJO has all the details of customer orders
 */
public class Order {
	
	public Order() {}

	private List<OrderedPizza> items = new ArrayList<>();;
	private String id;
	private Date date;
	private float totalPrice;
	private int quantity;
	private Customer customer;
	private List<Sides> sides = new ArrayList<>();
	
	public Order(List<OrderedPizza> items, String id, Date date, float totalPrice, int quantity, Customer customer,
			List<Sides> sides) {
		super();
		this.items = items;
		this.id = id;
		this.date = date;
		this.totalPrice = totalPrice;
		this.quantity = quantity;
		this.customer = customer;
		this.sides = sides;
	}


	public List<OrderedPizza> getItems() {
		return items;
	}

	public void setItems(List<OrderedPizza> items) {
		this.items = items;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Sides> getSides() {
		return sides;
	}

	public void setSides(List<Sides> sides) {
		this.sides = sides;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((items == null) ? 0 : items.hashCode());
		result = prime * result + quantity;
		result = prime * result + ((sides == null) ? 0 : sides.hashCode());
		result = prime * result + Float.floatToIntBits(totalPrice);
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		if (quantity != other.quantity)
			return false;
		if (sides == null) {
			if (other.sides != null)
				return false;
		} else if (!sides.equals(other.sides))
			return false;
		if (Float.floatToIntBits(totalPrice) != Float.floatToIntBits(other.totalPrice))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Order [\n "
				+ "id=" + id + ","
				+ "\n date=" + date
				+ "\n items=" + items + "\n "
				+ ", totalPrice=" + totalPrice 
				+ ", quantity="	+ quantity 
				+ ", customer=" + customer 
				+ ", \n sides=" + sides + "]";
	}

}
