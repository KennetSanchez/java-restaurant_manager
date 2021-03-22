package model;

import java.util.ArrayList;

public class RestaurantManager {
	
	String name;
	
	Costumer costumer;
	Employee employee;
	FoodType foodType;
	Ingredient ingredient;
	Meal meal;
	Order order;
	Size size;
	User user;
	
	//ArrayLists with the data. Without persistence.
	ArrayList<Meal> allMeals;
	ArrayList<Costumer> allCostumers;
	ArrayList<FoodType> allFoodTypes;
	ArrayList<Order> allOrders;
	ArrayList<Ingredient> allIngredients;
	ArrayList<Size> allSizes;
	
	//Test cases.
	Meal newMealTestCase = new Meal("Coca-cola", "Big", "$10.000", "Drink", "Doesn't apply");	
	FoodType newFoodTypeTestCase = new FoodType("Principal dish");	
	Ingredient newIngredientsTestCase = new Ingredient("Nuts", true);
	Size newSize = new Size("Family");
	
	///Test cases not used yet.
	Costumer newCostumerTestCase = new Costumer("Name 1", "Lastname1", "Street 21, Career 15", "None", 3005539864L);
	Order newOrderTestCase = new Order("Requested", "JD001");
	
	
	
	//Commented for test purposes.
	/*	
		allMeals.add(newMealTestCase);
		allIngredients.add(newIngredientsTestCase);
		allFoodTypes.add(newFoodTypeTestCase);
	*/
	
	public RestaurantManager() {
		
		allMeals = new ArrayList<Meal>();
		allCostumers  = new ArrayList<Costumer>();
		allFoodTypes = new ArrayList<FoodType>();
		allOrders = new ArrayList<Order>();
		allIngredients = new ArrayList<Ingredient>();	
		allSizes = new ArrayList<Size>();
		
		allMeals.add(newMealTestCase);
		allIngredients.add(newIngredientsTestCase);
		allFoodTypes.add(newFoodTypeTestCase);
		//test
		//allFoodTypes=createDataList("costumer");
		
		allSizes.add(newSize);
	}
	
	public void addMeal(Meal newMeal) {
		allMeals.add(newMeal);
	}
	
	public String[] createDataList(String opt) {
		String txt = null;
		switch (opt){
			case "costumer": txt = costumer.toString();
			break;
			case "employee": txt = employee.toString();
			break;
			case "foodType": txt = foodType.getType();
			break;
			case "ingredient": txt = ingredient.toString();
			break;
			case "meal": txt = meal.toString();
			break;
			case "order": txt = order.toString();
			break;
			case "size": txt = size.getName();
			break;
			case "user": txt = user.toString();
			break;
			default: System.out.println("There is an error"); //msg to the developers. Is for a while
		}
		
		String[] stArray = null;
		if(txt!=null) {
			stArray= txt.split(",");
		}
		
		return stArray;
	}
	
	public ArrayList<Meal> getMeals() {		
		return allMeals;		
	}
	
	public ArrayList<Ingredient> getIngredients(){
		return allIngredients;
	}
	
	public ArrayList<Costumer> getCostumers(){
		return allCostumers;
	}

	public ArrayList<Order> getOrders(){
		return allOrders;
	}
	
	public ArrayList<FoodType> getFoodTypes(){
		return allFoodTypes;
	}
	
	public ArrayList<Size> getSizes(){
		return allSizes;
	}
}
