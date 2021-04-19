package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
//import java.io.InputStreamReader;
//import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//import java.util.Objects;

public class RestaurantManager {

	private final static String FILE_COSTUMER = "docs/sCostumer.txt";
	private final static String FILE_EMPLOYEE = "docs/sEmployee.txt";
	private final static String FILE_FOODTYPE = "docs/sFoodType.txt";
	private final static String FILE_INGREDIENT = "docs/sIngredient.txt";
	private final static String FILE_MEAL = "docs/sMeal.txt";
	private final static String FILE_ORDER = "docs/sOrder.txt";
	private final static String FILE_SIZE= "docs/sSize.txt";
	private final static String FILE_USER = "docs/sUser.txt";
	
	//Reports
	private final static String FILE_SELLS_PRODUCT = "docs/sSellsProduct.txt";
	private final static String FILE_SELLS_EMPLOYEE = "docs/sSellsEmployee.txt";

	String name;
	
	Costumer costumer;
	Employee employee = null;
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

	// Order observableList
	ArrayList<Meal> orderFood;

	//
	BufferedReader br = null;
	BufferedWriter bw = null;
	FileWriter fileW = null;
	FileReader fileR = null;

	//ArrayList for test the order.
	
	public RestaurantManager() throws IOException, FileNotFoundException, ClassNotFoundException, EOFException {
		// br = new BufferedReader(new InputStreamReader(System.in));
		//fileR = new FileReader("docs/em-List.txt"); // example. That isn't the name
		//br = new BufferedReader(fileR);

		allMeals = new ArrayList<Meal>();
		allCostumers = new ArrayList<Costumer>();
		allFoodTypes = new ArrayList<FoodType>();
		allOrders = new ArrayList<Order>();
		allIngredients = new ArrayList<Ingredient>();
		allSizes = new ArrayList<Size>();
		allEmployees = new ArrayList<Employee>();
		allUsers = new ArrayList<User>();

		
		// Admin user
		allUsers.add(adminUser);

		orderFood = new ArrayList<Meal>();
		
		// TEST ------
		toSerialize();
		deserialize();
	}
	
	public void changeIngredientName(int index, String newName) {
		ArrayList<Ingredient> ingredients = getIngredientsEnabled();
		ingredients.get(index).setName(newName);			
	}
	
	// Make the report of sells by employee and sells of each product
	
	public void toReadEmployees() throws IOException {
		ArrayList<String> list = new ArrayList<>();
		br = new BufferedReader(new FileReader("docs/example.csv"));
		list.add(br.readLine());
        br.close();
	}

	public void toSerialize() throws IOException{
		
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_COSTUMER));
		oos.writeObject(allCostumers);
		oos.close();
		
		oos = new ObjectOutputStream(new FileOutputStream(FILE_EMPLOYEE));
		oos.writeObject(allEmployees);
		oos.close();
		
		oos = new ObjectOutputStream(new FileOutputStream(FILE_FOODTYPE));
		oos.writeObject(allFoodTypes);
		oos.close();
		
		oos = new ObjectOutputStream(new FileOutputStream(FILE_INGREDIENT));
		oos.writeObject(allIngredients);
		oos.close();
		
		oos = new ObjectOutputStream(new FileOutputStream(FILE_MEAL));
		oos.writeObject(allMeals);
		oos.close();
		
		oos = new ObjectOutputStream(new FileOutputStream(FILE_ORDER));
		oos.writeObject(allOrders);
		oos.close();
		
		oos = new ObjectOutputStream(new FileOutputStream(FILE_SIZE));
		oos.writeObject(allUsers);
		oos.close();
		
		//Reports
		oos = new ObjectOutputStream(new FileOutputStream(FILE_SELLS_EMPLOYEE));
		oos.writeObject(sellsByEmployee());
		oos.close();
		
		oos = new ObjectOutputStream(new FileOutputStream(FILE_SELLS_PRODUCT));
		oos.writeObject(sellsByProduct());
		oos.close();
    }
	
	@SuppressWarnings("unchecked")
	public void deserialize() throws IOException, ClassNotFoundException{
		try {
		ObjectInputStream oisC = new ObjectInputStream(new FileInputStream(FILE_COSTUMER));
		allCostumers = (ArrayList<Costumer>)oisC.readObject();
		oisC.close();
		
		ObjectInputStream oisE = new ObjectInputStream(new FileInputStream(FILE_EMPLOYEE));
		allEmployees = (ArrayList<Employee>)oisE.readObject();
		oisE.close();
		
		ObjectInputStream oisF = new ObjectInputStream(new FileInputStream(FILE_FOODTYPE));
		allFoodTypes = (ArrayList<FoodType>)oisF.readObject();
		oisF.close();
		
		ObjectInputStream oisI = new ObjectInputStream(new FileInputStream(FILE_INGREDIENT));
		allIngredients = (ArrayList<Ingredient>)oisI.readObject();
		oisI.close();
		
		ObjectInputStream oisM = new ObjectInputStream(new FileInputStream(FILE_MEAL));
		allMeals = (ArrayList<Meal>)oisM.readObject();
		oisM.close();
		
		ObjectInputStream oisO = new ObjectInputStream(new FileInputStream(FILE_ORDER));
		allOrders = (ArrayList<Order>)oisO.readObject();
		oisO.close();
		
		ObjectInputStream oisS = new ObjectInputStream(new FileInputStream(FILE_SIZE));
		allSizes = (ArrayList<Size>)oisS.readObject();
		oisS.close();
		
		ObjectInputStream oisU = new ObjectInputStream(new FileInputStream(FILE_USER));
		allUsers = (ArrayList<User>)oisU.readObject();
		oisU.close();
		}
		catch(EOFException e){
		}
	}

	public ArrayList<String> sellsByEmployee() throws IOException {
		double cost = 0;
		double totalValue = 0;
		int totalOrders = 0;
		ArrayList<String> stList= new ArrayList<>(); 
		
		stList.add("Empleado,N° Ordenes,Valor\n");
		
		for (int i = 0; i < allEmployees.size() && !allEmployees.isEmpty(); i++) {

			if (allEmployees.get(i).getMeals() != null) {

				for (int j = 0; j < allEmployees.get(i).getMeals().size(); j++) {
					cost += allEmployees.get(i).getMeals().get(j).getPrice();
				}
			}
			stList.add(allEmployees.get(i).getName() + "," +allEmployees.get(i).getLastname()
					+ allEmployees.get(i).getOrdersToday() + "," + cost + "\n");
			totalValue += cost;
			cost = 0;
			totalOrders += allEmployees.get(i).getOrdersToday();
		}
		stList.add("\nTotal:," + totalOrders + "," + totalValue);
		
		return stList;
	}

	public ArrayList<String> sellsByProduct() throws IOException {
		ArrayList<String> stList= new ArrayList<>(); 
		
		double cost = 0;
		double totalValue = 0;
		int totalOrders = 0;
		
		for (int i = 0; i < allMeals.size() && !allMeals.isEmpty(); i++) {

			if (allMeals.get(i).getPrice() != 0) {

				for (int j = 0; j < allMeals.size(); j++) {
					cost += allMeals.get(i).getPrice();
				}
			}
			stList.add(allEmployees.get(i).getName() + "," +allEmployees.get(i).getLastname()
					+ allEmployees.get(i).getOrdersToday() + "," + cost + "\n");
			totalValue += cost;
			cost = 0;
		}
		stList.add("Producto,N° Ordenes,Valor\n");
		stList.add("\nTotal:,"+ "" +","+totalValue);

		return stList;
	}
	
	/*public void sortAlg(){
		   ArrayList<Costumer> aux = allCostumers;
		   
			for(int i = 0; i<aux.size();i++){
			   String min = aux.get(i).getName();

			    for(int j=i+1; j<aux.size(); j++){
				if(aux.get(j).getName().compareToIgnoreCase(min)<0){
					String temp = aux.get(j).getName();
					aux.set(j,allmin);
					min = temp;
				}		
		            }
				aux.get(i).setName(min);
			}
		}*/

	// Enable /Disable objects methods.

	public boolean changeStateIngredient(int index, String newState) {
		boolean founded = false;
		boolean posible = false;

		if (allIngredients.size() >= index && index >= 0) {
			posible = true;
		}

		if (posible) {
			allIngredients.get(index).setState(newState);
			;
			founded = true;
		}
		return founded;
	}
	
	//Meals.
	public boolean changeStateMeal(int index, String newState) {
		boolean founded = false;
		boolean posible = false;

		if (allMeals.size() >= index && index >= 0) {
			posible = true;
		}

		if (posible) {
			allMeals.get(index).setState(newState);
			founded = true;
		}
		return founded;
	}

	//Orders
	public boolean refreshStatus(int index, int state) {
		boolean made = false;
		ArrayList<Order> orders = getOrdersEnabled();
		
		Order orderChoosed = orders.get(index);

		//Validation.
		if(orderChoosed.getStatusNum() < state) {
			made = true;
			
			switch(state) {
			
			case 2: orderChoosed.setStatus("En preparación");
					orderChoosed.setStatusNum(state);
					break;
					
			case 3: orderChoosed.setStatus("En entrega");	
					orderChoosed.setStatusNum(state);
					break;
					
			case 4: orderChoosed.setStatus("Entregado");
					orderChoosed.setStatusNum(state);
					break;
					
			case 5: orderChoosed.setStatus("Cancelado.");
					orderChoosed.setStatusNum(state);
					break;
			}
		}
		
		return made;
	}
	
	
	public boolean changeStateCostumer(int index, String newState) {
		boolean founded = false;
		boolean posible = false;

		if (allCostumers.size() >= index && index >= 0) {
			posible = true;
		}

		if (posible) {
			allCostumers.get(index).setState(newState);
			;
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
			allEmployees.get(index).setState(newState);
			;
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
			allOrders.get(index).setState(newState);
			;
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
			allUsers.get(index).setState(newState);
			;
			founded = true;
		}
		return founded;
	}
	
	public boolean changeStateSize(int index, String newState) {
		boolean founded = false;
		boolean posible = false;

		if (allSizes.size() >= index && index >= 0) {
			posible = true;
		}

		if (posible) {
			allSizes.get(index).setState(newState);
			;
			founded = true;
		}
		return founded;
	}
	
	public boolean changeStateType(int index, String newState) {
		boolean founded = false;
		boolean posible = false;

		if (allFoodTypes.size() >= index && index >= 0) {
			posible = true;
		}

		if (posible) {
			allFoodTypes.get(index).setState(newState);
			;
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
	
	public boolean deleteSize(int index) {
		boolean founded = false;
		boolean posible = false;

		if (allSizes.size() >= index && index >= 0) {
			posible = true;
		}
		if (posible) {
			allSizes.remove(index);
			founded = true;
		}

		return founded;
	}
	
	public boolean deleteType(int index) {
		boolean founded = false;
		boolean posible = false;

		if (allFoodTypes.size() >= index && index >= 0) {
			posible = true;
		}
		if (posible) {
			allFoodTypes.remove(index);
			founded = true;
		}

		return founded;
	}

	public boolean deleteEmployee(int index) {
		boolean founded = false;
		boolean posible = false;

		if (allEmployees.size() >= index && index >= 0) {
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

	public void addMealToOrder(Meal meal) {
		Meal enabledMeal = null;
		Meal mealTest = null;
				
		for(int i = 0; i < getMealsEnabled().size() ; i++) {
			mealTest = getMealsEnabled().get(i);
			if(meal == getMealsEnabled().get(i)) {
				enabledMeal = mealTest;
			}
		}
		
		orderFood.add(enabledMeal);
	}
	
	public ArrayList<Meal> getOrderFood(){
		return orderFood;
	}

	public void cleanOrderFood() {
		orderFood.clear();
	}
	public void addOrder(String status, String observations, Costumer owner, Employee employeeInCharge,	List<Meal> meals) {
		Order newOrder = new Order(status, observations, owner, employeeInCharge, meals, "Sí");
		allOrders.add(newOrder);
	}

	public void addUser(String userName, String userPass, String name, String lastName, long userId) {
		String enabledE = "Sí";
		String enabledU = "Sí";

		User newUser = new User(userName, userPass, name, lastName, userId, enabledE, enabledU);
		allUsers.add(newUser);
	}

	// DataList
	/*public List<String> createDataList(String opt) {
		String txt = null;
		switch (opt) {
		case "costumer":
			txt = allCostumers;
			break;
		case "employee":
			txt = employee.toString();
			break;
		case "foodType":
			txt = foodType.getName();
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
		List<String> list = new ArrayList<String>();
		if (txt != null) {
			Collections.addAll(list, stArray);
		}
		
		if(list!=null) {
			
		}

		return list;
	}*/
	
	public void createDataCostumer() {
		if(!allCostumers.isEmpty()) {
			for(int i=0; i < allCostumers.size() ;i++) {
				allCostumers.get(i).toString();	
			}
		}
	}

	// Get the arrays only with the enabled items.
	public ArrayList<Meal> getMealsEnabled() {
		return allMeals;
	}

	public ArrayList<Ingredient> getIngredientsEnabled() {

		ArrayList<Ingredient> enabledArray = new ArrayList<Ingredient>();

		for (int i = 0; i < allIngredients.size(); i++) {
			if (allIngredients.get(i).getEnabled().equalsIgnoreCase("Sí")) {
				enabledArray.add(allIngredients.get(i));
			}
		}

		return enabledArray;
	}

	public ArrayList<Costumer> getCostumersEnabled() {

		ArrayList<Costumer> enabledArray = new ArrayList<Costumer>();

		for (int i = 0; i < allCostumers.size(); i++) {
			if (allCostumers.get(i).getEnabled().equalsIgnoreCase("Sí")) {
				enabledArray.add(allCostumers.get(i));
			}
		}

		return enabledArray;
	}

	public ArrayList<Order> getOrdersEnabled() {

		ArrayList<Order> enabledArray = new ArrayList<Order>();

		for (int i = 0; i < allOrders.size(); i++) {
			if (allOrders.get(i).getEnabled().equalsIgnoreCase("Sí")) {
				enabledArray.add(allOrders.get(i));
			}
		}

		return enabledArray;
	}

	public ArrayList<FoodType> getFoodTypesEnabled() {

		ArrayList<FoodType> enabledArray = new ArrayList<FoodType>();

		for (int i = 0; i < allFoodTypes.size(); i++) {
			if (allFoodTypes.get(i).getState().equalsIgnoreCase("Sí")) {
				enabledArray.add(allFoodTypes.get(i));
				
			}
		}

		return enabledArray;
	}

	public ArrayList<Size> getSizesEnabled() {
		return allSizes;
	}

	public ArrayList<Employee> getEmployeesEnabled() {

		ArrayList<Employee> enabledArray = new ArrayList<Employee>();

		for (int i = 0; i < allEmployees.size(); i++) {
			if (allEmployees.get(i).getEnabledE().equalsIgnoreCase("Sí")) {
				enabledArray.add(allEmployees.get(i));
			}
		}

		return enabledArray;
	}

	public ArrayList<User> getUsersEnabled() {

		ArrayList<User> enabledArray = new ArrayList<User>();

		for (int i = 0; i < allUsers.size(); i++) {
			if (allUsers.get(i).getEnabledU().equalsIgnoreCase("Sí")) {
				enabledArray.add(allUsers.get(i));
			}
		}

		return enabledArray;
	}

	// Get the complete arrays.
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

	public Costumer getCostumerObject(String nameAndPhone) {
		Costumer costumer = null;
		boolean founded = false;

		for (int i = 0; i < allCostumers.size() && !founded; i++) {
			if (allCostumers.get(i).getNameAndPhone().equals(nameAndPhone)) {
				costumer = allCostumers.get(i);
				founded = true;
			}
		}

		return costumer;
	}

	public Employee getEmployeeObject(String name) {
		Employee costumer = null;
		boolean founded = false;
		String[] fullName = name.split(" ");

		for (int i = 0; i < allEmployees.size() && !founded; i++) {
			if (allEmployees.get(i).getName().equals(fullName[0])
					&& allEmployees.get(i).getName().equals(fullName[1])) {
				costumer = allEmployees.get(i);
				founded = true;
			}
		}

		return costumer;
	}

	public Employee login(String name, String password) {
		boolean founded = false;
		String userName = "";
		String userPass = "";
		Employee user = null;

		for (int i = 0; i < allUsers.size() && !founded; i++) {
			userName = allUsers.get(i).getUsername();
			userPass = allUsers.get(i).getPassword();
			if (name.equals(userName) && password.equals(userPass)) {
				user = allUsers.get(i);
				founded = true;
			}
		}

		return user;

	}

	// Admin user, used as a basic user.
	User adminUser = new User("admin", "root", "manager", "owner", 000L, "Sí", "Sí");

}
