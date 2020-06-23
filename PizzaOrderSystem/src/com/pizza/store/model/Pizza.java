package com.pizza.store.model;

import java.util.Map;

import com.pizza.store.util.Type;

/**
 * @author Reeta Wani
 * This class used to store pizza details with the variant price of pizza by its size.
 */
public class Pizza {
	
	public Pizza(String id,String name, Type type, Map<String, Float> sizeToPrice) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.sizeToPrice = sizeToPrice;
	}

	private String id;
	private String name;
	private Type type;
	private Map<String, Float> sizeToPrice;

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

	public Map<String, Float> getSizeToPrice() {
		return sizeToPrice;
	}

	public void setSizeToPrice(Map<String, Float> sizeToPrice) {
		this.sizeToPrice = sizeToPrice;
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
		Pizza other = (Pizza) obj;
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
		return "\n Pizza [id=" + id + ", name=" + name + ", type=" + type + ", sizeToPrice=" + sizeToPrice + "]";
	}

}
