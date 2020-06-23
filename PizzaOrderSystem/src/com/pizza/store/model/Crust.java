package com.pizza.store.model;


/**
 * @author Reeta Wani
 * Simple POJO class for Crust attributes
 */
public class Crust {
	public Crust(String id, String crustName) {
		super();
		this.name = crustName;
		this.id = id;
	}

	private String name;
	private String id;

	@Override
	public String toString() {
		return "Crust [name=" + name + ", id=" + id + "]";
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

	public void setName(String crustName) {
		this.name = crustName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Crust other = (Crust) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	

}
