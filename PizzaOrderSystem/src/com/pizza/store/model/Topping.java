package com.pizza.store.model;

import com.pizza.store.util.Type;

/**
 * @author Reeta Wani
 * This class is used to hold the information of toppings used on the pizza.
 */
public class Topping {

	private String name;
	private Type type;
	private float price;
	private String id;

	public Topping(String id, String name, Type type, float price) {
		this.name = name;
		this.price = price;
		this.type = type;
		this.id = id;

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Topping other = (Topping) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "\n Topping [name=" + name + ", type=" + type + ", price=" + price + ", id=" + id + "]";
	}

}
