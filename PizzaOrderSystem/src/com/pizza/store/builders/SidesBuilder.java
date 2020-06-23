package com.pizza.store.builders;

import java.util.Set;

import com.pizza.store.model.Sides;


/**
 * @author Reeta Wani
 * This interface holds all the available methods allowed for sides
 */
public interface SidesBuilder {
	
	public int addSides(Sides side) throws Exception;
	
	public Set<Sides> getSides();
	
	public Sides getSidesById(String Id);

}
