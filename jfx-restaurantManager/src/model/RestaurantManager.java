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
//import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

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
	
	//Reports csv
	private final static String SELLS_PRODUCT = "docs/Pr-List.csv";
	private final static String SELLS_EMPLOYEE = "docs/Emp-list.csv";

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
	
	public RestaurantManager() throws IOException, FileNotFoundException, ClassNotFoundException, EOFException {
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
		
		//TEST
		if(adminUser.getSerial()!=1) {
			toSerialize();
		}
		
		toSerialize();
		deserialize();
	}
	
	public void changeIngredientName(int index, String newName) throws IOException {
		ArrayList<Ingredient> ingredients = getIngredientsEnabled();
		ingredients.get(index).setName(newName);		
		toSerialize();
	}
	
	// Make the report of sells by employee and sells of each product
	
	public void toReadEmployees() throws IOException {
		ArrayList<String> list = new ArrayList<>();
		br = new BufferedReader(new FileReader("docs/example.csv"));
		list.add(br.readLine());
        br.close();
        toSerialize();
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
		oos.writeObject(allSizes);
		oos.close();
		
		oos = new ObjectOutputStream(new FileOutputStream(FILE_USER));
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
		//int totalOrders = 0;
		
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
	
	//Create Reports
	public void createReportbyP() throws IOException, EOFException {
		ArrayList<String> al = sellsByProduct();
		bw = new BufferedWriter (new FileWriter(SELLS_PRODUCT));
		for(int i=0; i<sellsByProduct().size(); i++) {
			bw.write(al.get(i));
		}
		toSerialize();
	}
	
	//To sort arraylist. Selection
	public void selectionSort(){
		ArrayList<Costumer> aux = allCostumers;
		int pos;
		Costumer temp;  
	    for(int i=0;i<aux.size();i++)  
	    {  
	        pos = smallest(aux,i);  
	        temp = aux.get(i);  
	        aux.set(i,aux.get(pos));  
	        aux.set(pos, temp);  
	    }
	}
	
	public static int smallest(ArrayList <Costumer> ac, int i) {  
	    Costumer small;
		int pos;  
	    small = ac.get(i);  
	    pos = i;  
	    
    	for(int j=i; j<ac.size(); j++)  
	    {  
	        if(ac.get(j).getName().compareToIgnoreCase(small.getName())<0)  
	        {  
	            small = ac.get(j);  
	            pos = j;  
	        }  
	    }
    	
	    return pos;  
	}  
	
	//To sort. Insertion
	public void InsertionSort() {  
	    ArrayList<Employee> ar = allEmployees;
	    
	    for(int i=1; i<ar.size(); i++)   
	    {  
	        Employee temp = ar.get(i);  
	        int j=i-1;
	        for(j=i-1; j>=0 && temp.getName().compareToIgnoreCase(ar.get(j).getName())<0; j--) {  
	            ar.set(j+1, ar.get(j));  
	        }
	        ar.set(j+1, temp);
	    }
	    allEmployees = ar;
	}  
	
	//Search
	public String searchCostumer(String name){
		ArrayList<Costumer> aux = allCostumers;
	   
		String courrier = "";
	   
		int min, max, mid;
		max= aux.size()-1;
		min = 0;
		mid = (max+min)/2;
		
		String costumerName = name;
		String foundedName = "";
		
		long start = System.currentTimeMillis();
		long end;
		long time;
		
		int comparation = 0;
		boolean founded = false;
		
		while(min <= max && !founded) {		
			mid = (max+min)/2;
			foundedName = aux.get(mid).getFullName();
			
			comparation = costumerName.compareToIgnoreCase(foundedName);
			System.out.println(comparation);
			System.out.println(foundedName + " buscando " + costumerName);
			
			if(comparation == 0) {
				end = System.currentTimeMillis();
				time = (end-start);
				courrier = foundedName + " tardó: " + time + " milisegundos en ser encontrado. \n";
				founded = true;
			
				start = System.currentTimeMillis();
			}else if(comparation < 0){
				max = mid-1;
				System.out.println("Mínimo: " + min + "maximo " + max);
			}else {
				min = mid+1;
				System.out.println("Mínimo: " + min + "maximo " + max);
			}
			System.out.println(mid);
			mid = (max+min)/2;
			System.out.println(mid);
		}
		return courrier;
	}
	
	// Enable /Disable objects methods.

	public boolean changeStateIngredient(int index, int option) throws IOException {
		boolean founded = false;
		boolean posible = false;

		if (allIngredients.size() >= index && index >= 0) {
			posible = true;
		}

		ObjectState state = null;
		switch(option) {
			case 1: state = ObjectState.HABILITADO;
					break;
		
			case 2: state = ObjectState.DESHABILITADO;
					break;
		}
		
		
		if (posible) {
			allIngredients.get(index).setState(state);
			toSerialize();
			founded = true;
		}
		return founded;
	}
	
	//Meals.
	public boolean changeStateMeal(int index, int option) throws IOException {
		boolean founded = false;
		boolean posible = false;

		if (allMeals.size() >= index && index >= 0) {
			posible = true;
		}

		ObjectState state = null;
		switch(option) {
			case 1: state = ObjectState.HABILITADO;
					break;
		
			case 2: state = ObjectState.DESHABILITADO;
					break;
		}
		
		if (posible) {
			allMeals.get(index).setState(state);
			toSerialize();
			founded = true;
		}
		return founded;
	}

	//Orders
	public boolean refreshStatus(int index, int state) throws IOException {
		boolean made = false;
		ArrayList<Order> orders = getOrdersEnabled();
		
		Order orderChoosed = orders.get(index);

		//Validation.
		if(orderChoosed.getStatusNum() < state) {
			made = true;
			
			switch(state) {
			
			case 2: orderChoosed.setStatus(OrderState.PREPARÁNDOSE);
					orderChoosed.setStatusNum(state);
					break;
					
			case 3: orderChoosed.setStatus(OrderState.ENVIADO);	
					orderChoosed.setStatusNum(state);
					break;
					
			case 4: orderChoosed.setStatus(OrderState.ENTREGADO);
					orderChoosed.setStatusNum(state);
					break;
					
			case 5: orderChoosed.setStatus(OrderState.CANCELADO);
					orderChoosed.setStatusNum(state);
					break;
			}
		}
		//toSerialize();
		return made;
	}
	
	
	public boolean changeStateCostumer(int index, int option) throws IOException {
		boolean founded = false;
		boolean posible = false;

		if (allCostumers.size() >= index && index >= 0) {
			posible = true;
		}

		ObjectState state = null;
		switch(option) {
			case 1: state = ObjectState.HABILITADO;
					break;
		
			case 2: state = ObjectState.DESHABILITADO;
					break;
		}
		
		if (posible) {
			allCostumers.get(index).setState(state);
			toSerialize();
			founded = true;
		}
		return founded;
	}

	public boolean changeStateEmployee(int index, int option) throws IOException {
		boolean founded = false;
		boolean posible = false;

		if (allEmployees.size() >= index && index >= 0) {
			posible = true;
		}
		
		ObjectState state = null;
		switch(option) {
			case 1: state = ObjectState.HABILITADO;
					break;
		
			case 2: state = ObjectState.DESHABILITADO;
					break;
		}

		if (posible) {
			allEmployees.get(index).setState(state);
			;
			toSerialize();
			founded = true;
		}
		return founded;
	}

	public boolean changeStateOrder(int index, int option) throws IOException {
		boolean founded = false;
		boolean posible = false;

		if (allOrders.size() >= index && index >= 0) {
			posible = true;
		}

		
		ObjectState state = null;
		switch(option) {
			case 1: state = ObjectState.HABILITADO;
					break;
		
			case 2: state = ObjectState.DESHABILITADO;
					break;
		}
		
		if (posible) {
			allOrders.get(index).setState(state);
			;
			toSerialize();
			founded = true;
		}
		return founded;
	}

	public boolean changeStateUsert(int index, int option) throws IOException {
		boolean founded = false;
		boolean posible = false;

		if (allUsers.size() >= index && index >= 0) {
			posible = true;
		}

		ObjectState state = null;
		switch(option) {
			case 1: state = ObjectState.HABILITADO;
					break;
		
			case 2: state = ObjectState.DESHABILITADO;
					break;
		}
		
		if (posible) {
			allUsers.get(index).setState(state);
			;
			toSerialize();
			founded = true;
		}
		return founded;
	}
	
	public boolean changeStateSize(int index, int option) throws IOException {
		boolean founded = false;
		boolean posible = false;

		if (allSizes.size() >= index && index >= 0) {
			posible = true;
		}
		
		ObjectState state = null;
		switch(option) {
			case 1: state = ObjectState.HABILITADO;
					break;
		
			case 2: state = ObjectState.DESHABILITADO;
					break;
		}
		
		if (posible) {
			allSizes.get(index).setState(state);
			;
			toSerialize();
			founded = true;
		}
		return founded;
	}
	
	public boolean changeStateType(int index, int option) throws IOException {
		boolean founded = false;
		boolean posible = false;

		if (allFoodTypes.size() >= index && index >= 0) {
			posible = true;
		}

		ObjectState state = null;
		switch(option) {
			case 1: state = ObjectState.HABILITADO;
					break;
		
			case 2: state = ObjectState.DESHABILITADO;
					break;
		}
		
		if (posible) {
			allFoodTypes.get(index).setState(state);
			founded = true;
			;
			toSerialize();
		}
		
		return founded;
	}

	// Delete objects methods.

	public boolean deleteIngredient(int index) throws IOException {
		boolean founded = false;
		boolean posible = false;

		if (allIngredients.size() >= index && index >= 0) {
			posible = true;
		}

		if (posible) {
			allIngredients.remove(index);
			founded = true;
			toSerialize();
		}
		
		return founded;
	}

	public boolean deleteMeal(int index) throws IOException {
		boolean founded = false;
		boolean posible = false;

		if (allMeals.size() >= index && index >= 0) {
			posible = true;
		}
		if (posible) {
			allMeals.remove(index);
			founded = true;
			toSerialize();
		}
		
		return founded;
	}
	
	public boolean deleteSize(int index) throws IOException {
		boolean founded = false;
		boolean posible = false;

		if (allSizes.size() >= index && index >= 0) {
			posible = true;
		}
		if (posible) {
			allSizes.remove(index);
			founded = true;
			toSerialize();
		}
		
		return founded;
	}
	
	public boolean deleteType(int index) throws IOException {
		boolean founded = false;
		boolean posible = false;

		if (allFoodTypes.size() >= index && index >= 0) {
			posible = true;
		}
		if (posible) {
			allFoodTypes.remove(index);
			founded = true;
			toSerialize();
		}
		
		return founded;
	}

	public boolean deleteEmployee(int index) throws IOException {
		boolean founded = false;
		boolean posible = false;

		if (allEmployees.size() >= index && index >= 0) {
			posible = true;
		}
		if (posible) {
			allEmployees.remove(index);
			founded = true;
			toSerialize();
		}
		
		return founded;
	}

	public boolean deleteUser(int index) throws IOException {
		boolean founded = false;
		boolean posible = false;

		if (allUsers.size() >= index && index >= 0) {
			posible = true;
		}

		if (posible) {
			allUsers.remove(index);
			founded = true;
			toSerialize();
		}
		
		return founded;
	}

	public boolean deleteCostumer(int index) throws IOException {
		boolean founded = false;
		boolean posible = false;

		if (allCostumers.size() >= index && index >= 0) {
			posible = true;
		}

		if (posible) {
			allCostumers.remove(index);
			founded = true;
			toSerialize();
		}
		
		return founded;
	}

	public boolean deleteOrder(int index) throws IOException {
		boolean founded = false;
		boolean posible = false;

		if (allOrders.size() >= index && index >= 0) {
			posible = true;
		}

		if (posible) {
			allOrders.remove(index);
			founded = true;
			toSerialize();
		}
		
		return founded;
	}

	// Addition to the arrays from RestaurantManagerGUI:

	// If the user write the ingredients instead of choosing them.
	public void addMeal(String name, String size, String value, String type, String ingredients) throws IOException {
		ObjectState enabled = ObjectState.HABILITADO;
		Meal newMeal = new Meal(name, size, value, type, ingredients, enabled);
		allMeals.add(newMeal);
		toSerialize();
	}
	
	//If the user chooses the ingredients from the table.
	public void addMeal(String name, String size, String value, String type, ArrayList<Ingredient> ingredients) throws IOException {
		ObjectState enabled = ObjectState.HABILITADO;
		Meal newMeal = new Meal(name, size, value, type, ingredients, enabled);
		allMeals.add(newMeal);
		toSerialize();
	}
	public void addEmployee(String name, String lastname, long id) throws IOException {
		ObjectState enabled = ObjectState.HABILITADO;
		Employee newEmployee = new Employee(name, lastname, id, enabled);
		allEmployees.add(newEmployee);
		InsertionSort();
		toSerialize();
	}

	public void addIngredients(String name, boolean allergen) throws IOException {
		ObjectState enabled = ObjectState.HABILITADO;
		Ingredient newIngredient = new Ingredient(name, allergen, enabled);
		allIngredients.add(newIngredient);
		toSerialize();
	}

	public void addFoodType(String name) throws IOException {
		ObjectState enabled = ObjectState.HABILITADO;
		FoodType newFoodType = new FoodType(name, enabled);
		allFoodTypes.add(newFoodType);
		toSerialize();
	}

	public void addSize(String name) throws IOException {
		ObjectState enabled = ObjectState.HABILITADO;
		Size newSize = new Size(name, enabled);
		allSizes.add(newSize);
		toSerialize();
	}

	public void addCostumer(String name, String lastname, String address, String observations, long phone, long id) throws IOException {

		Costumer newCostumer = null;
		ObjectState enabled = ObjectState.HABILITADO;

		int minimum = 0;
		boolean done = false;
		int comparation = 0;

		int min = 0;
		int max = allCostumers.size() - 1;
		int mid = max / 2;

		String foundedName = "";

		while (min <= max && !done) {
			mid = (max + min) / 2;
			foundedName = allCostumers.get(mid).getName();

			comparation = name.compareToIgnoreCase(foundedName);
			System.out.println(comparation);
			System.out.println(foundedName + " buscando " + name);

			if (comparation == 0) {
				allCostumers.add(mid + 1, newCostumer);
				done = true;

			} else if (comparation < 0) {
				max = mid - 1;
				System.out.println("Mínimo: " + min + "maximo " + max);
			} else {
				min = mid + 1;
				System.out.println("Mínimo: " + min + "maximo " + max);
			}

			// Choosing the minimum difference. We multiply them to make both positive.

			if ((comparation * comparation) < (minimum * minimum)) {
				minimum = comparation;
			}
		}

		if (id != 0L) {
			newCostumer = new Costumer(name, lastname, address, observations, phone, enabled, id);
		} else {
			newCostumer = new Costumer(name, lastname, address, observations, phone, enabled);
		}
				
		if(allCostumers.size() > mid+1) {
			allCostumers.add(mid+1, newCostumer);
		}else {
			allCostumers.add(mid, newCostumer);
		}
	}
	
	public void addMealToOrder(Meal meal) throws IOException {
		Meal enabledMeal = null;
		Meal mealTest = null;

		for (int i = 0; i < getMealsEnabled().size(); i++) {
			mealTest = getMealsEnabled().get(i);
			if (meal == getMealsEnabled().get(i)) {
				enabledMeal = mealTest;
			}
		}

		orderFood.add(enabledMeal);
		 toSerialize();
	}
	
	public ArrayList<Meal> getOrderFood(){
		return orderFood;
	}

	public void cleanOrderFood() throws IOException {
		orderFood.clear();
		toSerialize();
	}
	public void addOrder(String observations, Costumer owner, Employee employeeInCharge,	List<Meal> meals) throws IOException {
		ObjectState enabled = ObjectState.HABILITADO;
		
		Order newOrder = new Order(OrderState.SOLICITADO, observations, owner, employeeInCharge, meals, enabled);
		allOrders.add(newOrder);
		toSerialize();
	}

	public void addUser(String userName, String userPass, String name, String lastName, long userId) throws IOException {
		ObjectState enabledE = ObjectState.HABILITADO;
		ObjectState enabledU = ObjectState.HABILITADO;

		User newUser = new User(userName, userPass, name, lastName, userId, enabledE, enabledU);
		allUsers.add(newUser);
		toSerialize();
	}
	
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
			if (allIngredients.get(i).getEnabled() == ObjectState.HABILITADO) {
				enabledArray.add(allIngredients.get(i));
			}
		}

		return enabledArray;
	}

	public ArrayList<Costumer> getCostumersEnabled() {

		ArrayList<Costumer> enabledArray = new ArrayList<Costumer>();

		for (int i = 0; i < allCostumers.size(); i++) {
			if (allCostumers.get(i).getEnabled() == ObjectState.HABILITADO) {
				enabledArray.add(allCostumers.get(i));
			}
		}

		return enabledArray;
	}

	public ArrayList<Order> getOrdersEnabled() {

		ArrayList<Order> enabledArray = new ArrayList<Order>();

		for (int i = 0; i < allOrders.size(); i++) {
			if (allOrders.get(i).getEnabled() == ObjectState.HABILITADO) {
				enabledArray.add(allOrders.get(i));
			}
		}

		return enabledArray;
	}

	public ArrayList<FoodType> getFoodTypesEnabled() {

		ArrayList<FoodType> enabledArray = new ArrayList<FoodType>();

		for (int i = 0; i < allFoodTypes.size(); i++) {
			if (allFoodTypes.get(i).getState() == ObjectState.HABILITADO) {
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
			if (allEmployees.get(i).getEnabledE() == ObjectState.HABILITADO) {
				enabledArray.add(allEmployees.get(i));
			}
		}

		return enabledArray;
	}

	public ArrayList<User> getUsersEnabled() {

		ArrayList<User> enabledArray = new ArrayList<User>();

		for (int i = 0; i < allUsers.size(); i++) {
			if (allUsers.get(i).getEnabledU() == ObjectState.HABILITADO) {
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
	User adminUser = new User("admin", "root", "manager", "owner", 000L, ObjectState.HABILITADO, ObjectState.HABILITADO);

	public void addIngredientToMeal(Ingredient choosedIngredient, int choosedMeal) {
		ArrayList<Meal> meals = getMealsEnabled();
		meals.get(choosedMeal);
	}

	public static String getSellsEmployee() {
		return SELLS_EMPLOYEE;
	}

}
