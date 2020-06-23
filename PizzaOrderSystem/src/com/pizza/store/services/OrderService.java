package com.pizza.store.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.pizza.store.builders.CrustBuilder;
import com.pizza.store.builders.PizzaBuilder;
import com.pizza.store.builders.SidesBuilder;
import com.pizza.store.builders.ToppingBuilder;
import com.pizza.store.builders.impl.CrustBuilderImpl;
import com.pizza.store.builders.impl.PizzaBuilderImpl;
import com.pizza.store.builders.impl.SidesBuilderImpl;
import com.pizza.store.builders.impl.ToppingsBuilderImpl;
import com.pizza.store.model.Order;
import com.pizza.store.model.OrderedPizza;
import com.pizza.store.model.Sides;
import com.pizza.store.model.Topping;
import com.pizza.store.util.Size;
import com.pizza.store.util.Type;

/**
 * @author Reeta Wani
 * This class is used for all the transaction of Pizza factory. 
 * Methods : 
 * buildPizzaOrder : This method is used to build the order by customer
 * addSides : This method is used to ad sides in the order by customer
 * placeOrder : This method is used to place order once its built
 */
public class OrderService{

	List<Order> orders = new ArrayList<>();
	public static String PANNER_TOPPING = "Paneer";
	public static float EXTRA_CHEESE_PRICE = 35;
	public static int FREE_TOPPING_COUNT = 2;

	private PizzaBuilder pizzaBuilder = new PizzaBuilderImpl();
	private ToppingBuilder toppingsBuilder = new ToppingsBuilderImpl();
	private CrustBuilder crustBuilder = new CrustBuilderImpl();
	private SidesBuilder sidesBuilder = new SidesBuilderImpl();  
	
	/**
	 * This method is used to build the pizza by customer.
	 * @param pizzaId
	 * @param toppingIds
	 * @param crustId
	 * @param crustSize
	 * @param isExtraCheese
	 * @return
	 * @throws Exception
	 */
	public Order buildPizzaOrder(String pizzaId,List<String> toppingIds,String crustId, String crustSize, boolean isExtraCheese) throws Exception {
		
		Order order = new Order();
		
		/* Reduce the crust inventory */
		if(crustId != null && crustSize != null) {
			InventoryService.reducedCrustCount(crustId,crustSize);
		}
		
		OrderedPizza orderedPizza = new OrderedPizza(pizzaBuilder.getPizzaById(pizzaId));
		if(toppingIds.size() > 0) {
			for (String toppingId : toppingIds) {
				InventoryService.reducedToppingCount(toppingId);
				orderedPizza.setToppings(toppingsBuilder.getToppingById(toppingId));
			}
		}
		orderedPizza.setCrust(crustBuilder.getCrustById(crustId));
		orderedPizza.setSize(crustSize);
		orderedPizza.setExtraCheese(isExtraCheese);
		
		order.getItems().add(orderedPizza);
		return order;
	}

	
	/**
	 * This method is used to add sides in Pizza
	 * @param sidesId
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public Order addSides(List<String> sidesId,  Order order)throws Exception {
		
		if (sidesId!= null && sidesId.size() > 0) {
			for (String id : sidesId) {
				InventoryService.reducedSidesCount(id);
				order.getSides().add(sidesBuilder.getSidesById(id));
			}
		}
		order.setQuantity(order.getItems().size()+order.getSides().size());
		return order;
	}
	
	/**
	 * This method is used to place the order by customer once the pizza and sides are finalized.
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public Order placeOrder(Order order) throws Exception {

		List<OrderedPizza> items = order.getItems();
		float totalPrice = 0;
		for (OrderedPizza orderedPizza : items) {
			float price = 0;
			validateToppings(orderedPizza);

			price = price + orderedPizza.getPizza().getSizeToPrice().get(orderedPizza.getSize());
			if (orderedPizza.isExtraCheese()) {
				price = price + EXTRA_CHEESE_PRICE; 
			}
			price = price + orderedPizza.getTotalToppingPrice();
			orderedPizza.setPrice(price);
			totalPrice = totalPrice + price;
			System.out.println("Total Price for " + orderedPizza.getPizza().getName() + " :" + price);
		}

		List<Sides> sides = order.getSides();
		for (Sides side : sides) {
			totalPrice = totalPrice + side.getPrice();
		}

		order.setTotalPrice(totalPrice);
		order.setId(String.valueOf(orders.size() + 1));
		order.setDate(new Date());

		orders.add(order);

		return order;
	}

	/**
	 * This method is used to validate the toppings for veg and non-veg Pizza
	 * as per given business rule
	 * @param orderedPizza
	 * @throws Exception
	 */
	private void validateToppings(OrderedPizza orderedPizza) throws Exception {
		List<Topping> toppings = orderedPizza.getToppings();
		float totalToppingPrice = 0;
		int toppingCount = 0;

		if (Type.VEG.equals(orderedPizza.getPizza().getType())) {
			for (Topping topping : toppings) {
				toppingCount++;
				if (Type.NON_VEG.equals(topping.getType())) {
					throw new Exception("Vegetarian pizza cannot have a non-vegetarian topping.");
				}

				/*
				 * Large size pizzas come with any 2 toppings of customers choice with no
				 * additional cost
				 */
				if (Size.LARGE.getSize().equals(orderedPizza.getSize()) 
						&& toppingCount <= FREE_TOPPING_COUNT) {
					continue;
				} else {
					totalToppingPrice = totalToppingPrice + topping.getPrice();
				}
			}
		} else if (Type.NON_VEG.equals(orderedPizza.getPizza().getType())) {
			int nonVegToppingCounter = 0;
			for (Topping topping : toppings) {
				toppingCount ++;
				if (Type.NON_VEG.equals(topping.getType())) {
					nonVegToppingCounter ++;
					if (nonVegToppingCounter == 2) {
						throw new Exception("You can add only one of the non-veg toppings in non-vegetarian pizza.");
					}
				}
				if (PANNER_TOPPING.equals(topping.getName())) {
					throw new Exception("Non-vegetarian pizza cannot have paneer topping.");
				}

				/*
				 * Large size pizzas come with any 2 toppings of customers choice with no
				 * additional cost
				 */
				if (Size.LARGE.getSize().equals(orderedPizza.getSize()) 
						&& toppingCount <= FREE_TOPPING_COUNT) {
					continue;
				} else {
					totalToppingPrice = totalToppingPrice + topping.getPrice();
				}
			}
		}
		orderedPizza.setTotalToppingPrice(totalToppingPrice);
	}
	

}
