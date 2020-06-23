package com.pizza.store.builders;

import java.util.List;

import com.pizza.store.model.Topping;


/**
 * @author Reeta Wani
 * This interface holds all the available methods allowed for toppings
 */
public interface ToppingBuilder {
	
	public int addTopping(Topping topping) throws Exception;

	public List<Topping> getToppings();

	public Topping getToppingById(String id);

}
