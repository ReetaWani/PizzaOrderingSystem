import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.pizza.store.builders.PizzaBuilder;
import com.pizza.store.builders.SidesBuilder;
import com.pizza.store.builders.ToppingBuilder;
import com.pizza.store.builders.impl.PizzaBuilderImpl;
import com.pizza.store.builders.impl.SidesBuilderImpl;
import com.pizza.store.builders.impl.ToppingsBuilderImpl;
import com.pizza.store.model.Order;
import com.pizza.store.model.OrderedPizza;
import com.pizza.store.model.Pizza;
import com.pizza.store.model.Sides;
import com.pizza.store.model.Topping;
import com.pizza.store.services.InventoryService;
import com.pizza.store.services.OrderService;
import com.pizza.store.util.Size;
import com.pizza.store.util.Type;

class TestOrder {

	OrderService orderService = new OrderService();
	
	/* Pizza details :
	 * Pizza : Deluxe Veggie pizza
	 * Toppings: Chicken tikka toppings
	 * Crust : New hand tossed 
	 * ExtraCheese : Yes
	 * */
	@Test
	void testVegPizzaWithNonVegTopping() {
		try {
			Order order = orderService.buildPizzaOrder("1", Arrays.asList("6"), "1", Size.REGULAR.getSize(), true);
			orderService.placeOrder(order);
		} catch (Exception ex) {
			System.out.println("===========================================================================");
			System.out.println("Test Name : testVegPizzaWithNonVegTopping ");
			System.out.println(ex.getMessage());
			System.out.println("===========================================================================");
			
			assertEquals(ex.getMessage(), "Vegetarian pizza cannot have a non-vegetarian topping.");
		}
	}

	/* Pizza details :
	 * Pizza : Cheese and corn pizza
	 * Toppings: Capsicum,Paneer,Mushroom
	 * Crust : New hand tossed 
	 * ExtraCheese : No
	 * */
	@Test
	void testLargePizzaWithThreeTopping() throws Exception {

		ToppingBuilder toppingsBuilderImpl = new ToppingsBuilderImpl();
		
		Order order = orderService.buildPizzaOrder("2", Arrays.asList("2", "3", "4"), "1", Size.LARGE.getSize(), false);
		OrderedPizza orderedPizza = order.getItems().get(0);
		float expetedPizzaPrice = orderedPizza.getPizza().getSizeToPrice().get(Size.LARGE.getSize())
				+ toppingsBuilderImpl.getToppingById("4").getPrice();

		System.out.println("===========================================================================");
		System.out.println("Test Name : testLargePizzaWithThreeTopping \n");
		System.out.println(orderService.placeOrder(order));
		System.out.println("===========================================================================");
		assertEquals(expetedPizzaPrice, order.getTotalPrice());
	}

	/* Pizza details :
	 * Pizza : Non-Veg Supreme pizza
	 * Toppings: Capsicum,Mushroom
	 * Crust : New hand tossed 
	 * ExtraCheese : No
	 * */
	@Test
	void testMediumPizzaWithSides() throws Exception {

		ToppingsBuilderImpl toppingsBuilderImpl = new ToppingsBuilderImpl();
		SidesBuilder sidesBuilder = new SidesBuilderImpl();
		Order order = orderService.buildPizzaOrder("4", Arrays.asList("2", "4"), "1", Size.MEDIUM.getSize(), false);
		orderService.addSides(Arrays.asList("1", "2"), order);
		OrderedPizza orderedPizza = order.getItems().get(0);
		float expetedPizzaPrice = orderedPizza.getPizza().getSizeToPrice().get(Size.MEDIUM.getSize())
				+ toppingsBuilderImpl.getToppingById("4").getPrice()
				+ toppingsBuilderImpl.getToppingById("2").getPrice()
				+ sidesBuilder.getSidesById(String.valueOf(1)).getPrice() 
				+ sidesBuilder.getSidesById(String.valueOf(2)).getPrice();

		System.out.println("===========================================================================");
		System.out.println("Test Name : testMediumPizzaWithSides \n");
		System.out.println(orderService.placeOrder(order));
		System.out.println("===========================================================================");
		
		assertEquals(expetedPizzaPrice, order.getTotalPrice());
	}

	/* Pizza details :
	 * Pizza : Non-Veg Supreme pizza
	 * Toppings: Black olive(3)
	 * Crust : New hand tossed 
	 * ExtraCheese : No
	 * */
	@Test
	void testLargePizzaWithMaxTopping() throws Exception {
		try {
			ToppingsBuilderImpl toppingsBuilderImpl = new ToppingsBuilderImpl();
			Order order = orderService.buildPizzaOrder("4", Arrays.asList("1", "1", "1"), "1", Size.LARGE.getSize(), false);
			OrderedPizza orderedPizza = order.getItems().get(0);
			float expetedPizzaPrice = orderedPizza.getPizza().getSizeToPrice().get(Size.LARGE.getSize())
					+ toppingsBuilderImpl.getToppingById("4").getPrice();

			orderService.placeOrder(order);
			
		} catch (Exception ex) {
			System.out.println("===========================================================================");
			System.out.println("Test Name : testLargePizzaWithMaxTopping \n");
			System.out.println(ex.getMessage());
			assertEquals(ex.getMessage(), "Black olive size not available.");
			System.out.println("===========================================================================");
		}
	}

	/* Pizza details :
	 * Pizza : Non-Veg Supreme pizza
	 * Toppings: Chicken Tikka, Barbecue Chicken
	 * Crust : New hand tossed 
	 * ExtraCheese : Yes
	 * */
	@Test
	void testNonVegPizzaWithTwoNonVegTopping() {
		try {
			ToppingBuilder toppingsBuilder = new ToppingsBuilderImpl();
			Order order = orderService.buildPizzaOrder("4", Arrays.asList("6", "7"), "1", Size.LARGE.getSize(), true);
			OrderedPizza orderedPizza = order.getItems().get(0);

			order.getItems().add(orderedPizza);
			System.out.println("===========================================================================");
			System.out.println("Test Name : testNonVegPizzaWithTwoNonVegTopping \n");
			orderService.placeOrder(order);
			System.out.println("===========================================================================");
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			assertEquals(ex.getMessage(), "You can add only one of the non-veg toppings in non-vegetarian pizza.");
		}
	}
	
	/*
	 * Create a pizza with all its variant for regular/medium/large with price
	 * Create a new topping
	 * update inventory for same
	 * Place order using new pizza and toppings
	 * Create new sides and update inventory 
	 * add newly created sides in order
	 * */
	@Test
	void testAddPizzaAndPlaceOrder() {
		try {
			
			Map<String, Float> pizzaVariants = new HashMap<>();
			InventoryService inventoryService = new InventoryService();
			pizzaVariants.put(Size.REGULAR.getSize(), 160f);
			pizzaVariants.put(Size.MEDIUM.getSize(), 210f);
			pizzaVariants.put(Size.LARGE.getSize(), 305f);
			Pizza pizza = new Pizza(null, "Veg Maharaja", Type.VEG, pizzaVariants);
			int id = inventoryService.createNewPizza(pizza);
			
			Topping topping = new Topping(null, "Jalapeno", Type.VEG, 50f);
			int toppingId = inventoryService.createNewTopping(topping);
			
			Order order = orderService.buildPizzaOrder(String.valueOf(id), 
					Arrays.asList(String.valueOf(toppingId)), "1", Size.MEDIUM.getSize(), false);
			
			Sides side = new Sides(null, "Soda", 50f);
			int sidesId = inventoryService.createNewSides(side);
			order = orderService.addSides(Arrays.asList(String.valueOf(sidesId)), order);
			
			//System.out.println("Order : "+order);
			ToppingBuilder toppingsBuilder = new ToppingsBuilderImpl();
			OrderedPizza orderedPizza = order.getItems().get(0);
			float expetedPizzaPrice = orderedPizza.getPizza().getSizeToPrice().get(Size.MEDIUM.getSize())
					+ toppingsBuilder.getToppingById(String.valueOf(toppingId)).getPrice()
					+ 50;

			System.out.println("===========================================================================");
			System.out.println("Test Name : testAddPizzaAndPlaceOrder \n");
			System.out.println(orderService.placeOrder(order));
			System.out.println("===========================================================================");
			assertEquals(expetedPizzaPrice, order.getTotalPrice());
			
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	/*
	 * Update pizza price for all its variants
	 * */
	@Test
	void testUpdatePizza() {
		try {
			PizzaBuilder pizzaBuilder = new PizzaBuilderImpl();
			Map<String, Float> pizzaVariants = new HashMap<>();
			
			pizzaVariants.put(Size.REGULAR.getSize(), 240f);
			pizzaVariants.put(Size.MEDIUM.getSize(), 340f);
			pizzaVariants.put(Size.LARGE.getSize(), 550f);
			
			Pizza oldPizza = pizzaBuilder.getPizzaById("6");
			System.out.println("===========================================================================");
			System.out.println("Test Name : testUpdatePizza \n");
			System.out.println("Price Before Update : "+oldPizza.getSizeToPrice());
			System.out.println(PizzaBuilderImpl.pizzas);
			Pizza pizza = oldPizza;
			
			pizza.setSizeToPrice(pizzaVariants);
			PizzaBuilderImpl.pizzas.add(pizza);
			
			System.out.println("Price After update  : "+pizza.getSizeToPrice());
			System.out.println(PizzaBuilderImpl.pizzas);
			System.out.println("===========================================================================");
			
			assertEquals(oldPizza, pizza);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	/*
	 * Update toppings price
	 * */
	@Test
	void testUpdateToppings() {
		try {
			ToppingBuilder toppingBuilder = new ToppingsBuilderImpl();
			
			Topping oldTopping = toppingBuilder.getToppingById("5");
			System.out.println("===========================================================================");
			System.out.println("Test Name : testUpdateToppings \n");
			System.out.println("Price Before Update : "+oldTopping.getPrice());
			System.out.println(ToppingsBuilderImpl.toppings);
			Topping topping = oldTopping;
			
			topping.setPrice(20f);
			ToppingsBuilderImpl.toppings.add(topping);
			
			System.out.println("Price Before Update : "+topping.getPrice());
			System.out.println(ToppingsBuilderImpl.toppings);
			System.out.println("===========================================================================");
			
			assertEquals(oldTopping, topping);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	/*
	 * Update sides price
	 * */
	@Test
	void testUpdateSides() {
		try {
			SidesBuilder sidesBuilder = new SidesBuilderImpl();
			
			Sides oldSide = sidesBuilder.getSidesById("2");
			System.out.println("===========================================================================");
			System.out.println("Test Name : testUpdateSides \n");
			System.out.println("Price Before Update : "+oldSide.getPrice());
			System.out.println(SidesBuilderImpl.sides);
			Sides side = oldSide;
			
			side.setPrice(95f);
			SidesBuilderImpl.sides.add(side);
			
			System.out.println("Price Before Update : "+side.getPrice());
			System.out.println(SidesBuilderImpl.sides);
			System.out.println("===========================================================================");
			
			assertEquals(oldSide, side);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}
