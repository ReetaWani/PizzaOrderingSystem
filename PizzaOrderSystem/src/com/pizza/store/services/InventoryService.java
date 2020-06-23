package com.pizza.store.services;

import java.util.HashMap;
import java.util.Map;

import com.pizza.store.builders.PizzaBuilder;
import com.pizza.store.builders.SidesBuilder;
import com.pizza.store.builders.ToppingBuilder;
import com.pizza.store.builders.impl.CrustBuilderImpl;
import com.pizza.store.builders.impl.PizzaBuilderImpl;
import com.pizza.store.builders.impl.SidesBuilderImpl;
import com.pizza.store.builders.impl.ToppingsBuilderImpl;
import com.pizza.store.model.Crust;
import com.pizza.store.model.Pizza;
import com.pizza.store.model.Sides;
import com.pizza.store.model.Topping;
import com.pizza.store.services.InventoryService;
import com.pizza.store.util.Size;

/**
 * @author Reeta Wani
 * This class is used to store all the information of available inventory for pizza store like:
 *  Available Crusts, Toppings, Sides 
 *  Methods :
 *  reducedCrustCount : To maintain crust inventory 
 *  reducedToppingCount : To maintain topping inventory
 *  reducedSidesCount : To maintain side inventory
 *  createNewPizza: THis method is used to create a new Pizza
 *  createNewTopping : THis method is used to create new Topping
 *  creaateNewSides : This method is used to create new sides
 *  
 */
public class InventoryService{
	
	public static Map<String,Map<String,Integer>> crustIdToCount = new HashMap<>();
	public static Map<String,Integer> toppingsToCount = new HashMap<>();
	public static Map<String,Integer> sidesToCount = new HashMap<>();
	
	private PizzaBuilder pizzaBuilder = new PizzaBuilderImpl();
	private ToppingBuilder toppingsBuilder = new ToppingsBuilderImpl();
	private SidesBuilder sidesBuilder = new SidesBuilderImpl(); 
	
	/* Below static blocks create a initial inventory for Pizza store */
	static {
		
		/* As in the problem statement we have given that we have 4 types of crust hence 
		 * below loop runs for 4 times and create a inventory of each crust for different sizes of crust
		 * with 3 items in inventory 
		 * Crusts: New Hand Tossed, Wheat thin crust,Cheese burst, Fresh Pan pizza
		 * */
		for(int i=1;i<=4;i++) {
			Map<String,Integer> sizeToCount = new HashMap<>();
			sizeToCount.put(Size.REGULAR.getSize(), 3);
			sizeToCount.put(Size.MEDIUM.getSize(), 3);
			sizeToCount.put(Size.LARGE.getSize(), 3);
			crustIdToCount.put(String.valueOf(i), sizeToCount);
		}
		/*
		 * Similarly the toppings inventory created for given 8 types of toppings with 2 items in inventory.
		 * */
		for(int i=1;i<=8;i++) {
			toppingsToCount.put(String.valueOf(i), 2);
		}
		
		/* 
		 * We have two types of sides hence loops runs for two times and create inventory for coke and Mousse
		 * */
		for(int i=1;i<=2;i++) {
			sidesToCount.put(String.valueOf(i), 2);
		}
		
	}
	
	/**
	 * This method is used to update the crust inventory. 
	 * This is invoked whenever a order is places using any crust. It validates if the inventory is not available then 
	 * it throws an exception
	 * @param crustId
	 * @param size
	 * @throws Exception
	 */
	public static void reducedCrustCount(String crustId, String size)throws Exception {
		
		if(crustId==null  || size ==null) {
			throw new Exception("Invalid input provided for crustId and size");
		}
		
		int count = crustIdToCount.get(crustId).get(size);
		CrustBuilderImpl crustBuilderImpl = new CrustBuilderImpl();
		Crust crust = crustBuilderImpl.getCrustById(crustId);
		if(count == 0) {
			throw new Exception(crust.getName()+" size not available."+size);
		}
		
		crustIdToCount.get(crustId).put(size,count-1);
	}
	
	/**
	 * This method is used to update the topping inventory. 
	 * This is invoked whenever a order is places using any topping. It validates if the inventory is not available then 
	 * it throws an exception
	 * @param toppingId
	 * @throws Exception
	 */
	public static void reducedToppingCount(String toppingId)throws Exception {
		
		if(toppingId==null) {
			throw new Exception("Invalid input provided for toppingId");
		}
		
		int count = toppingsToCount.get(toppingId);
		ToppingsBuilderImpl toppingsBuilderImpl = new ToppingsBuilderImpl();
		Topping topping = toppingsBuilderImpl.getToppingById(toppingId);
		if(count == 0) {
			throw new Exception(topping.getName()+" size not available.");
		}
		toppingsToCount.put(toppingId,count-1);
	}
	
	/**
	 * This method is used to update the sides inventory. 
	 * This is invoked whenever a order is places using any sides. It validates if the inventory is not available then 
	 * it throws an exception
	 * @param sidesId
	 * @throws Exception
	 */
	public static void reducedSidesCount(String sidesId)throws Exception {
		
		if(sidesId==null) {
			throw new Exception("Invalid input provided for sidesId");
		}
		
		int count = sidesToCount.get(sidesId);
		SidesBuilder sidesBuilder = new SidesBuilderImpl();
		Sides sides = sidesBuilder.getSidesById(sidesId);
		if(count == 0) {
			throw new Exception(sides.getName()+" size not available.");
		}
		sidesToCount.put(sidesId,count-1);
	}
	
	/**
	 * THis method is used to add new pizza in Pizza factory
	 * @param pizza
	 * @return
	 * @throws Exception
	 */
	public int createNewPizza(Pizza pizza) throws Exception {
		return pizzaBuilder.addPizza(pizza);
	}
	
	/**
	 * This method is used to add new topping in Pizza factory
	 * @param topping
	 * @return
	 * @throws Exception
	 */
	public int createNewTopping(Topping topping) throws Exception {
		return toppingsBuilder.addTopping(topping);
	
	}
	
	/**
	 * This method is used to add new sides in Pizza factory
	 * @param side
	 * @return
	 * @throws Exception
	 */
	public int createNewSides(Sides side) throws Exception {
		return sidesBuilder.addSides(side);
	}

}
