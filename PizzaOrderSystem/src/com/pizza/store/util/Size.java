package com.pizza.store.util;

/**
 * @author Reeta Wani
 * This enum holds all the available types of pizza/crust types
 */
public enum Size {
	REGULAR("Regular"),
	MEDIUM("Medium"),
	LARGE("Large");

	String type;
	
	public String getSize() {
		return type;
	}

	Size(String size) {
		this.type = size;
	}

	
}
