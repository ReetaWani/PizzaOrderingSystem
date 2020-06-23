package com.pizza.store.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Reeta Wani
 * This class holds all the information of pizza and ingredients used on piza to customize it. 
 */
public class OrderedPizza  {


	public OrderedPizza(Pizza pizza) {
		super();
		this.pizza = pizza;
	}

	private String size;
	private List<Topping> toppings = new ArrayList<>();;
	private float price;
	private boolean isExtraCheese;
	/* Only one type of crust can be selected for any pizza. Hence Crust is not a list item.*/
	private Crust crust;
	private Pizza pizza;
	private float totalToppingPrice;


	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public List<Topping> getToppings() {
		return toppings;
	}

	public void setToppings(List<Topping> toppings) {
		this.toppings = toppings;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public boolean isExtraCheese() {
		return isExtraCheese;
	}

	public void setExtraCheese(boolean isExtraCheese) {
		this.isExtraCheese = isExtraCheese;
	}

	public float getTotalToppingPrice() {
		return totalToppingPrice;
	}

	public void setTotalToppingPrice(float totalToppingPrice) {
		this.totalToppingPrice = totalToppingPrice;
	}

	public Crust getCrust() {
		return crust;
	}

	public void setCrust(Crust crust) {
		this.crust = crust;
	}
	
	public void setToppings(Topping topping) {
		this.toppings.add(topping);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((crust == null) ? 0 : crust.hashCode());
		result = prime * result + (isExtraCheese ? 1231 : 1237);
		result = prime * result + ((pizza == null) ? 0 : pizza.hashCode());
		result = prime * result + Float.floatToIntBits(price);
		result = prime * result + ((size == null) ? 0 : size.hashCode());
		result = prime * result + ((toppings == null) ? 0 : toppings.hashCode());
		result = prime * result + Float.floatToIntBits(totalToppingPrice);
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
		OrderedPizza other = (OrderedPizza) obj;
		if (crust == null) {
			if (other.crust != null)
				return false;
		} else if (!crust.equals(other.crust))
			return false;
		if (isExtraCheese != other.isExtraCheese)
			return false;
		if (pizza == null) {
			if (other.pizza != null)
				return false;
		} else if (!pizza.equals(other.pizza))
			return false;
		if (Float.floatToIntBits(price) != Float.floatToIntBits(other.price))
			return false;
		if (size == null) {
			if (other.size != null)
				return false;
		} else if (!size.equals(other.size))
			return false;
		if (toppings == null) {
			if (other.toppings != null)
				return false;
		} else if (!toppings.equals(other.toppings))
			return false;
		if (Float.floatToIntBits(totalToppingPrice) != Float.floatToIntBits(other.totalToppingPrice))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OrderedPizza \n [size=" + size + ", \n toppings=" + toppings + ",\n price=" + price + ", isExtraCheese="
				+ isExtraCheese + ",\n crust=" + crust + ", pizza=" + pizza + ", totalToppingPrice=" + totalToppingPrice
				+ "]\n";
	}
	
}
