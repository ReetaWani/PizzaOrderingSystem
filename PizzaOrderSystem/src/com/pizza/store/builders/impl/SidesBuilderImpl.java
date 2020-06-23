package com.pizza.store.builders.impl;

import java.util.HashSet;
import java.util.Set;

import com.pizza.store.builders.SidesBuilder;
import com.pizza.store.model.Sides;
import com.pizza.store.services.InventoryService;

/**
 * @author Reeta Wani
 * This class is used to create/build sides by creating default given sides and ability to add new side
 */
public class SidesBuilderImpl implements SidesBuilder {

	public static Set<Sides> sides = new HashSet<>();
	
	/* Generate basic given sides */
	static {
		sides.add(new Sides("1", "Cold Drink", 55f));
		sides.add(new Sides("2", "Mousse cake", 90f));
	}

	/**
	 * This method is used to create new sides 
	 */
	public synchronized int addSides(Sides side) throws Exception {

		if (side.getName() == null || side.getName().isEmpty()) {
			throw new Exception("Sides name cannot be null");
		}

		if (side.getPrice() == 0 && side.getPrice() < 0) {
			throw new Exception("Sides price cannot be 0");
		}

		Integer id = sides.size() + 1;
		side.setId(id.toString());
		sides.add(side);
		
		/* Update existing sidesToCount with newly created sides */
		if(InventoryService.sidesToCount.containsKey(id.toString())) {
			InventoryService.sidesToCount.put(id.toString(),
					InventoryService.sidesToCount.get(id.toString())+1);
		}else {
			InventoryService.sidesToCount.put(id.toString(),1);
		}
		
		return id;
	}

	/**
	 * THis method is used to get all sides
	 */
	public Set<Sides> getSides() {
		return sides;
	}

	/** 
	 * This method is used to get sides by its id
	 */
	public Sides getSidesById(String id) {
		return sides.stream().filter(e-> e.getId().equals(id)).findFirst().get();
	}

}