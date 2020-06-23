package com.pizza.store.builders.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.pizza.store.builders.PizzaBuilder;
import com.pizza.store.model.Pizza;
import com.pizza.store.util.Size;
import com.pizza.store.util.Type;

/**
 * @author Reeta Wani
 * This class is used to create/build pizza by creating default given pizza and ability to add new pizza
 */
public class PizzaBuilderImpl implements PizzaBuilder {

	public static Set<Pizza> pizzas = new HashSet<>();
	/* Generate basic given pizza */
	static {

		Map<String, Float> pizzaVariants = new HashMap<>();
		pizzaVariants.put(Size.REGULAR.getSize(), 150f);
		pizzaVariants.put(Size.MEDIUM.getSize(), 200f);
		pizzaVariants.put(Size.LARGE.getSize(), 325f);
		pizzas.add(new Pizza("1", "Deluxe Veggie", Type.VEG, pizzaVariants));

		pizzaVariants = new HashMap<>();
		pizzaVariants.put(Size.REGULAR.getSize(), 175f);
		pizzaVariants.put(Size.MEDIUM.getSize(), 375f);
		pizzaVariants.put(Size.LARGE.getSize(), 475f);
		pizzas.add(new Pizza("2", "Cheese and corn", Type.VEG, pizzaVariants));

		pizzaVariants = new HashMap<>();
		pizzaVariants.put(Size.REGULAR.getSize(), 160f);
		pizzaVariants.put(Size.MEDIUM.getSize(), 290f);
		pizzaVariants.put(Size.LARGE.getSize(), 340f);
		pizzas.add(new Pizza("3", "Paneer Tikka", Type.VEG, pizzaVariants));

		pizzaVariants = new HashMap<>();
		pizzaVariants.put(Size.REGULAR.getSize(), 190f);
		pizzaVariants.put(Size.MEDIUM.getSize(), 325f);
		pizzaVariants.put(Size.LARGE.getSize(), 425f);
		pizzas.add(new Pizza("4", "Non-Veg Supreme", Type.NON_VEG, pizzaVariants));

		pizzaVariants = new HashMap<>();
		pizzaVariants.put(Size.REGULAR.getSize(), 210f);
		pizzaVariants.put(Size.MEDIUM.getSize(), 370f);
		pizzaVariants.put(Size.LARGE.getSize(), 500f);
		pizzas.add(new Pizza("5", "Chicken Tikka", Type.NON_VEG, pizzaVariants));

		pizzaVariants = new HashMap<>();
		pizzaVariants.put(Size.REGULAR.getSize(), 220f);
		pizzaVariants.put(Size.MEDIUM.getSize(), 380f);
		pizzaVariants.put(Size.LARGE.getSize(), 525f);
		pizzas.add(new Pizza("6", "Pepper Barbecue Chicken", Type.NON_VEG, pizzaVariants));

	}

	/**
	 * This method is used to create new pizza 
	 */
	public synchronized int addPizza(Pizza pizza) throws Exception {

		if (pizza.getName() == null || pizza.getName().isEmpty()) {
			throw new Exception("Pizza name cannot be null");
		}
		if (pizza.getType() == null) {
			throw new Exception("Pizza type cannot be null");
		}
		if (pizza.getSizeToPrice() == null ||pizza.getSizeToPrice().isEmpty()) {
			// Here checks can be added for keys and values if provided correct values or not
			throw new Exception("Pizza prices values are not provided");
		}

		Integer id = pizzas.size() + 1;
		pizza.setId(id.toString());
		pizzas.add(pizza);
		return id;
	}
	
	/**
	 * THis method is used to get all pizza
	 */
	public Set<Pizza> getPizzas(){
		return pizzas;
	}
	
	/** 
	 * This method is used to get pizza by its id
	 */
	public Pizza getPizzaById(String id) {
		return pizzas.stream().filter(e-> e.getId().equals(id)).findFirst().get();
	}
	
}
