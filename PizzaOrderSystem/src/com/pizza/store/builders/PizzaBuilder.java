package com.pizza.store.builders;

import java.util.Set;

import com.pizza.store.model.Pizza;

/**
 * @author Reeta Wani
 * This interface holds all the available methods allowed for sides
 */
public interface PizzaBuilder {

	public int addPizza(Pizza pizza) throws Exception;

	public Set<Pizza> getPizzas();

	public Pizza getPizzaById(String Id);
}
