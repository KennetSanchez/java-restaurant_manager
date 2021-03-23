package model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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

	// ArrayLists with the data. Without persistence.
	ArrayList<Meal> allMeals;
	ArrayList<Costumer> allCostumers;
	ArrayList<FoodType> allFoodTypes;
	ArrayList<Order> allOrders;
	ArrayList<Ingredient> allIngredients;
	ArrayList<Size> allSizes;
	ArrayList<Employee> allEmployees;
	ArrayList<User> allUsers;

	// Test cases.
	Meal newMealTestCase = new Meal("Coca-cola", "Big", "$10.000", "Drink", "Doesn't apply");
	FoodType newFoodTypeTestCase = new FoodType("Principal dish");
	Ingredient newIngredientsTestCase = new Ingredient("Nuts", true, "Sí.");
	Size newSizeTestCase = new Size("Family");
	Costumer newCostumerTestCase = new Costumer("Name 1", "Lastname1", "Street 21, Career 15", "None", 3005539864L, "Sí.");
	Order newOrderTestCase = new Order("Requested", "JD001");
	Employee newEmployeeTestCase = new Employee("Employee1", "Lastname1", 1006229432L, "Sí");
	
	// Admin user, used as a basic user.
	User adminUser = new User("admin", "root", "manager", "owner", 000L, "Sí", "Sí");

	public RestaurantManager() {

		allMeals = new ArrayList<Meal>();
		allCostumers = new ArrayList<Costumer>();
		allFoodTypes = new ArrayList<FoodType>();
		allOrders = new ArrayList<Order>();
		allIngredients = new ArrayList<Ingredient>();
		allSizes = new ArrayList<Size>();
		allEmployees = new ArrayList<Employee>();
		allUsers = new ArrayList<User>();

		// Test cases.
		allMeals.add(newMealTestCase);
		allIngredients.add(newIngredientsTestCase);
		allFoodTypes.add(newFoodTypeTestCase);
		allSizes.add(newSizeTestCase);
		allEmployees.add(newEmployeeTestCase);
		allCostumers.add(newCostumerTestCase);
		
		// Admin user
		allUsers.add(adminUser);
	}

	// Addition to the arrays from RestaurantManagerGUI:

	// In the next versions this will be with the classes type, not Strings.
	public void addMeal(String name, String size, String value, String type, String ingredients) {
		Meal newMeal = new Meal(name, size, value, type, ingredients);
		allMeals.add(newMeal);
	}

	public void addEmployee(String name, String lastname, long id) {
		String enabled = "Sí";
		Employee newEmployee = new Employee(name, lastname, id, enabled);
		allEmployees.add(newEmployee);
	}

	public void addIngredients(String name, boolean allergen) {
		String enabled = "Sí";
		Ingredient newIngredient = new Ingredient(name, allergen, enabled);
		allIngredients.add(newIngredient);
	}

	public void addFoodType(String name) {
		FoodType newFoodType = new FoodType(name);
		allFoodTypes.add(newFoodType);
	}

	public void addSize(String name) {
		Size newSize = new Size(name);
		allSizes.add(newSize);
	}

	public void addCostumer(String name, String lastname, String address, String observations, long phone, long id) {

		Costumer newCostumer = null;
		String enabled = "Sí";
		
		if (id != 0L) {
			newCostumer = new Costumer(name, lastname, address, observations, phone, enabled, id);
		} else {
			newCostumer = new Costumer(name, lastname, address, observations, phone, enabled);
		}

		allCostumers.add(newCostumer);

	}

	public void addOrder(String status, String observations, Costumer owner, Employee employeeInCharge,	ArrayList<Meal> meals) {
		Order newOrder = new Order(status, observations, owner, employeeInCharge, meals);
		allOrders.add(newOrder);
	}

	public void addUser(String userName, String userPass, String name, String lastName,  long userId) {
		String enabledE = "Sí";
		String enabledU = "Sí";
		
		User newUser = new User(userName, userPass, name, lastName, userId, enabledE, enabledU);
		allUsers.add(newUser);
	}

	public List<String> createDataList(String opt) {
		String txt = null;
		switch (opt) {
		case "costumer":
			txt = costumer.toString();
			break;
		case "employee":
			txt = employee.toString();
			break;
		case "foodType":
			txt = foodType.getType();
			break;
		case "ingredient":
			txt = ingredient.toString();
			break;
		case "meal":
			txt = meal.toString();
			break;
		case "order":
			txt = order.toString();
			break;
		case "size":
			txt = size.getName();
			break;
		case "user":
			txt = user.toString();
			break;
		default:
			System.out.println("There is an error"); // msg to the developers. Is for a while
		}

		String[] stArray=null; 
		if (txt != null) {
			stArray = txt.split(txt);
		}
		
		List<String> list = new ArrayList<String>();
	    Collections.addAll(list, stArray);
		return list;
	}
	/*public void serialize() {
		try {
	         FileOutputStream fileOut = new FileOutputStream("example.txt");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(createDataList("order"));
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in /tmp/employee.ser");
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
	}*/ 
	//jd
	
	public ArrayList<Meal> getMeals() {
		return allMeals;
	}

	public ArrayList<Ingredient> getIngredients() {
		return allIngredients;
	}

	public ArrayList<Costumer> getCostumers() {
		return allCostumers;
	}

	public ArrayList<Order> getOrders() {
		return allOrders;
	}

	public ArrayList<FoodType> getFoodTypes() {
		return allFoodTypes;
	}

	public ArrayList<Size> getSizes() {
		return allSizes;
	}
	
	public ArrayList<Employee> getEmployees(){
		return allEmployees;
	}
	
	public ArrayList<User> getUsers(){
		return allUsers;
	}

	public String login(String name, String password) {
		boolean founded = false;
		String userName = "";
		String userPass = "";
		String user = "";
		
		for(int i = 0 ; i < allUsers.size() && !founded; i++) {
			userName = allUsers.get(i).getUsername();
			userPass = allUsers.get(i).getPassword();
			if(name.equals(userName) && password.equals(userPass)) {
				user = allUsers.get(i).getName() + " " + allUsers.get(i).getLastname();
				founded = true;
			}
		}
		
		return user;
		
	}
	
}
