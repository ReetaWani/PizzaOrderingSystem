package com.pizza.store.builders;

import java.util.Set;

import com.pizza.store.model.Crust;

/**
 * @author Reeta Wani
 * This interface holds all the available methods allowed for Crust
 */
public interface CrustBuilder {
	
	public int addCrust(Crust crust)throws Exception;
	
	public Set<Crust> getCrusts();
	
	public Crust getCrustById(String Id);

}
