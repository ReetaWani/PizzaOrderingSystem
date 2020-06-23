package com.pizza.store.builders.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.pizza.store.builders.ToppingBuilder;
import com.pizza.store.model.Topping;
import com.pizza.store.services.InventoryService;
import com.pizza.store.util.Type;

/**
 * @author Reeta Wani
 * This class is used to create/build sides by creating default given toppings and ability to add new toppings
 */
public class ToppingsBuilderImpl implements ToppingBuilder{
	
	public static Set<Topping> toppings = new HashSet();
	
	/* Generate basic given toppings */
	static {
		
		toppings.add(new Topping("1","Black olive",Type.VEG,20f));
		toppings.add(new Topping("2","Capsicum",Type.VEG,25f));
		toppings.add(new Topping("3","Paneer",Type.VEG,35f));
		toppings.add(new Topping("4","Mushroom",Type.VEG,30f));
		toppings.add(new Topping("5","Fresh tomato",Type.VEG,10f));
		
		toppings.add(new Topping("6","Chicken tikka",Type.NON_VEG,35f));
		toppings.add(new Topping("7","Barbeque chicken",Type.NON_VEG,45f));
		toppings.add(new Topping("8","Grilled chicken",Type.NON_VEG,40f));
		
	}
	
	/**
	 * This method is used to create new toppings 
	 */
	public synchronized int addTopping(Topping topping)throws Exception {
		
		if (topping.getName() == null) {
			throw new Exception("Topping name cannot be null");
		}
		if (topping.getType() == null) {
			throw new Exception("Topping type cannot be null");
		}
		if (topping.getPrice() == 0) {
			throw new Exception("Topping price cannot be null");
		}
		
		int id = toppings.size()+1;
		String toppingId = String.valueOf(id);
		topping.setId(toppingId);
		toppings.add(topping);
		
		/* Update existing toppingsToCount with newly created topping */
		if(InventoryService.toppingsToCount.containsKey(toppingId)) {
			InventoryService.toppingsToCount.put(toppingId,InventoryService.toppingsToCount.get(toppingId)+1);
		}else {
			InventoryService.toppingsToCount.put(toppingId,1);
		}
		
		return id;
	}
	
	/**
	 * THis method is used to get all toppings
	 */
	public List<Topping> getToppings(){
		return (List<Topping>) toppings;
	}
	
	/** 
	 * This method is used to get toppings by its id
	 */
	public Topping getToppingById(String id) {
		return toppings.stream().filter(e-> e.getId().equals(id)).findFirst().get();
	}

}
