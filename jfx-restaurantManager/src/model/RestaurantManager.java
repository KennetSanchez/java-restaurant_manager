package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
//import java.util.Objects;

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

	//
	BufferedReader br = null;
	BufferedWriter bw = null;
	FileWriter fileW = null;
	FileReader fileR = null;

	public RestaurantManager() throws IOException, FileNotFoundException {
		//br = new BufferedReader(new InputStreamReader(System.in));
		fileR = new FileReader("docs/em-List.txt"); //example. That isn't the name
		br = new BufferedReader(fileR);
	     
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

		// TEST -------
		// createEmployeeList();
	}
	//Make the report of sells by employee and sells of each product
	
	public void reports(String fileName) throws IOException {
		//fileName = "docs/em-List.txt";
		fileW = new FileWriter(fileName);
		bw = new BufferedWriter(fileW);
		//bw.write("");
		bw.close();
		//I could make two methods with the fors, 
	}
	public void sellsByEmployee() throws IOException {
		double cost = 0;
		double total = 0;
		
		String fileName = "docs/em-List.txt";
		fileW = new FileWriter(fileName);
		bw = new BufferedWriter(fileW);
		for (int i = 0; i < allEmployees.size() && !allEmployees.isEmpty(); i++) {

			if (allEmployees.get(0).getMeals() != null) {

				for (int j = 0; j < allEmployees.get(i).getMeals().size(); j++) {
					cost += allEmployees.get(i).getMeals().get(j).getPrice();
				}
			}
			bw.write(allEmployees.get(i).getName() + ":  Pedido(s): " + allEmployees.get(i).getOrdersToday()
					+ ". Valor: " + cost+"\n");
			total = cost;
			cost = 0;
		}
		
		bw.write("\n           -                -           Total:"+total);
		bw.close();
	}
	
	public void sellsByProduct() throws IOException{
		String fileName = "docs/times-ordered-product.txt";
		fileW = new FileWriter(fileName);
		bw= new BufferedWriter(fileW);
		bw.write("SIsirve");
		bw.close();
	}
	
	//Enable /Disable objects methods.
	
	public boolean changeStateIngredient(int index, String newState) {
		boolean founded = false;
		boolean posible = false;

		if (allIngredients.size() >= index && index >= 0) {
			posible = true;
		}
		
		if (posible) {
			allIngredients.get(index).setState(newState);;
			founded = true;
		}
		return founded;
	}
	
	public boolean changeStateCostumer(int index, String newState) {
		boolean founded = false;
		boolean posible = false;

		if (allCostumers.size() >= index && index >= 0) {
			posible = true;
		}
		
		if (posible) {
			allCostumers.get(index).setState(newState);;
			founded = true;
		}
		return founded;
	}
	
	public boolean changeStateEmployee(int index, String newState) {
		boolean founded = false;
		boolean posible = false;

		if (allEmployees.size() >= index && index >= 0) {
			posible = true;
		}
		
		if (posible) {
			allEmployees.get(index).setState(newState);;
			founded = true;
		}
		return founded;
	}
	
	public boolean changeStateOrder(int index, String newState) {
		boolean founded = false;
		boolean posible = false;

		if (allOrders.size() >= index && index >= 0) {
			posible = true;
		}
		
		if (posible) {
			allOrders.get(index).setState(newState);;
			founded = true;
		}
		return founded;
	}
	
	public boolean changeStateUsert(int index, String newState) {
		boolean founded = false;
		boolean posible = false;

		if (allUsers.size() >= index && index >= 0) {
			posible = true;
		}
		
		if (posible) {
			allUsers.get(index).setState(newState);;
			founded = true;
		}
		return founded;
	}
	
	
	// Delete objects methods.

	public boolean deleteIngredient(int index) {
		boolean founded = false;
		boolean posible = false;

		if (allIngredients.size() >= index && index >= 0) {
			posible = true;
		}

		if (posible) {
			allIngredients.remove(index);
			founded = true;
		}

		return founded;
	}

	public boolean deleteMeal(int index) {
		boolean founded = false;
		boolean posible = false;

		if (allMeals.size() >= index && index >= 0) {
			posible = true;
		}
		if (posible) {
			allMeals.remove(index);
			founded = true;
		}

		return founded;
	}

	public boolean deleteEmployee(int index) {
		boolean founded = false;
		boolean posible = false;
		
		if(allEmployees.size() >= index && index >= 0) {
			posible = true;
		}
		if (posible) {
			allEmployees.remove(index);
			founded = true;
		}

		return founded;
	}

	public boolean deleteUser(int index) {
		boolean founded = false;
		boolean posible = false;

		if (allUsers.size() >= index && index >= 0) {
			posible = true;
		}
		
		if (posible) {
			allUsers.remove(index);
			founded = true;
		}

		return founded;
	}

	public boolean deleteCostumer(int index) {
		boolean founded = false;
		boolean posible = false;

		if (allCostumers.size() >= index && index >= 0) {
			posible = true;
		}
		
		if (posible) {
			allCostumers.remove(index);
			founded = true;
		}

		return founded;
	}

	public boolean deleteOrder(int index) {
		boolean founded = false;
		boolean posible = false;

		if (allOrders.size() >= index && index >= 0) {
			posible = true;
		}
		
		if (posible) {
			allOrders.remove(index);
			founded = true;
		}

		return founded;
	}

	// Addition to the arrays from RestaurantManagerGUI:

	// In the next versions this will be with the classes type, not Strings.
	public void addMeal(String name, String size, String value, String type, String ingredients) {
		Meal newMeal = new Meal(name, size, value, type, ingredients, "Sí");
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

	public void addOrder(String status, String observations, Costumer owner, Employee employeeInCharge,
			ArrayList<Meal> meals) {
		Order newOrder = new Order(status, observations, owner, employeeInCharge, meals, "Sí");
		allOrders.add(newOrder);
	}

	public void addUser(String userName, String userPass, String name, String lastName, long userId) {
		String enabledE = "Sí";
		String enabledU = "Sí";

		User newUser = new User(userName, userPass, name, lastName, userId, enabledE, enabledU);
		allUsers.add(newUser);
	}
	
	//DataList
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

		String[] stArray = null;
		if (txt != null) {
			stArray = txt.split(txt);
		}

		List<String> list = new ArrayList<String>();
		Collections.addAll(list, stArray);
		return list;
	}

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

	public ArrayList<Employee> getEmployees() {
		return allEmployees;
	}

	public ArrayList<User> getUsers() {
		return allUsers;
	}

	public String login(String name, String password) {
		boolean founded = false;
		String userName = "";
		String userPass = "";
		String user = "";

		for (int i = 0; i < allUsers.size() && !founded; i++) {
			userName = allUsers.get(i).getUsername();
			userPass = allUsers.get(i).getPassword();
			if (name.equals(userName) && password.equals(userPass)) {
				user = allUsers.get(i).getName() + " " + allUsers.get(i).getLastname();
				founded = true;
			}
		}

		return user;

	}

	// Test cases.
	Meal newMealTestCase = new Meal("Coca-cola", "Big", "10000", "Drink", "Doesn't apply", "Sí");
	FoodType newFoodTypeTestCase = new FoodType("Principal dish");
	Ingredient newIngredientsTestCase = new Ingredient("Nuts", true, "Sí.");
	Size newSizeTestCase = new Size("Family");
	Costumer newCostumerTestCase = new Costumer("Name 1", "Lastname1", "Street 21, Career 15", "None", 3005539864L,"Sí.");
	Order newOrderTestCase = new Order("Requested", "JD001", "Sí");
	Employee newEmployeeTestCase = new Employee("Employee1", "Lastname1", 1006229432L, "Sí");

	// Admin user, used as a basic user.
	User adminUser = new User("admin", "root", "manager", "owner", 000L, "Sí", "Sí");

}
