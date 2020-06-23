package com.pizza.store.builders.impl;

import java.util.HashSet;
import java.util.Set;

import com.pizza.store.builders.CrustBuilder;
import com.pizza.store.model.Crust;
import com.pizza.store.services.InventoryService;

/**
 * @author Reeta Wani
 * This is a builder class to create/add new crust in Pizza store/factory and get all/crust by id
 */
public class CrustBuilderImpl implements CrustBuilder {
	
	/**
	 * This block is used to generate the crust inventory with given types of crust in problem statement
	 */
	public static Set<Crust> crusts = new HashSet<>();
	static {
		
		crusts.add(new Crust("1","New hand tossed"));
		crusts.add(new Crust("2","Wheat thin crust"));
		crusts.add(new Crust("3","Cheese Burst"));
		crusts.add(new Crust("4","Fresh pan pizza"));
	}
	
	/**
	 * This method can be used to create a new Crust in pizza store
	 */
	public int addCrust(Crust crust)throws Exception {
		
		if(crust.getName()==null || crust.getName().isEmpty()) {
			throw new Exception("Crust name cannot be null");
		}
		
		int id = crusts.size()+1;
		String crustId = String.valueOf(id);
		crust.setId(crustId);
		crusts.add(crust);
		
		return id;
	}
	
	/**
	 * This method is used to get all types of available in pizza store
	 */
	public Set<Crust> getCrusts(){
		return crusts;
	}
	
	/**
	 * This method is used to get the crust by its id from all available crusts.
	 */
	public Crust getCrustById(String id) {
		return crusts.stream().filter(e-> e.getId().equals(id)).findFirst().get();
	}

}
