package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.time.LocalDateTime;

import model.*;

public class RestaurantManagerGUI {

	RestaurantManager rm;

	public RestaurantManagerGUI() throws IOException {
		rm = new RestaurantManager();
	}

	// This method crash the program, and enter into a loop.
	private void showHour() {
		boolean finished = false;
		// If the program isn't finished, it will refresh the date every 10000
		// miliseconds (1 second).
		while (!finished) {
			if (mainPane.isVisible()) {
				try {
					date.setText(timeAndDate());
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				finished = true;
			}
		}
	}

	// This method crash the program.
	public String timeAndDate() {
		LocalDateTime ldt = LocalDateTime.now();
		String msg = ldt + "";
		return msg;
	}

	// Main items.

	@FXML
	private Label companyLogo;

	@FXML
	private Label date;

	@FXML
	private Pane mainPane;

	@FXML
	private TextField userText;

	@FXML
	private PasswordField passwordText;

	@FXML
	private Label userActive;

	// Menu code.

	@FXML
	void createCostumers(ActionEvent event) throws IOException {
		showCreateCostumerWindow();
	}

	@FXML
	void createEmployees(ActionEvent event) throws IOException {
		showCreateEmployee();
	}

	@FXML
	void createIngredients(ActionEvent event) throws IOException {
		showCreateIngredientWindow();
	}

	@FXML
	void createOrders(ActionEvent event) throws IOException {
		showCreateOrderWindow();
	}

	@FXML
	void createProducts(ActionEvent event) throws IOException {
		showCreateMealWindow();
	}

	@FXML
	void createUsers(ActionEvent event) throws IOException {
		showCreateUserWindow();
	}

	@FXML
	void login(ActionEvent event) {
		String name = userText.getText();
		String password = passwordText.getText();

		String user = rm.login(name, password);
		if (user != "") {
			userActive.setText(user);
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("Usuario y/o contraseña inexistentes.");
			alert.showAndWait();
		}

	}

	@FXML
	void manageCostumers(ActionEvent event) throws IOException {
		showManageCostumers();
	}

	@FXML
	void manageEmployees(ActionEvent event) throws IOException {
		showManageEmployees();
	}

	@FXML
	void manageIngredients(ActionEvent event) throws IOException {
		showManageIngredients();
	}

	@FXML
	void manageOrders(ActionEvent event) throws IOException {
		showManageOrders();
	}

	@FXML
	void manageProducts(ActionEvent event) throws IOException {
		showManageMeals();
	}

	@FXML
	void manageUsers(ActionEvent event) throws IOException {
		showManageUsers();
	}

	@FXML
	void showDevelopers(ActionEvent event) {

	}

	@FXML
	void showHelp(ActionEvent event) {

	}
	
	// Ingredients code.

	@FXML
	private Pane ingredientsPane;

	@FXML
	private TextField txtIngredient;

	@FXML
	private CheckBox allergenCheckBox;

	@FXML
	private TableView<Ingredient> tvIngredientsCreated;

	@FXML
	private TableColumn<Ingredient, String> tcIngredientsCreated;

	@FXML
	void createIngredient(ActionEvent event) {
		String name = txtIngredient.getText();
		boolean allergen = allergenCheckBox.isSelected();
		if (name != "") {
			rm.addIngredients(name, allergen);
			initializeTableViewsIngredientWindow();
			txtIngredient.clear();
			allergenCheckBox.setSelected(false);
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("No ha ingresado el nombre del ingrediente.");
			alert.showAndWait();
		}
	}

	private void initializeTableViewsIngredientWindow() {
		ObservableList<Ingredient> tvIngredientObservableList = FXCollections.observableArrayList(rm.getIngredients());
		tvIngredientsCreated.setItems(tvIngredientObservableList);
		tcIngredientsCreated.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("name"));
	}

	// Create meal code.

	@FXML
	private TextField txtMeal;

	@FXML
	private TextField txtType;

	@FXML
	private TextField txtPrice;

	@FXML
	private TextField txtSize;

	@FXML
	private TextArea areaIngredients;

	@FXML
	private TableView<Meal> tvMeal;

	@FXML
	private TableColumn<Meal, String> tcMeal;

	@FXML
	private TableView<FoodType> tvType;

	@FXML
	private TableColumn<FoodType, String> tcType;

	@FXML
	private TableView<Ingredient> tvIngredients;

	@FXML
	private TableColumn<Ingredient, String> tcIngredients;

	@FXML
	private TableView<Size> tvSize;

	@FXML
	private TableColumn<Size, String> tcSize;

	@FXML
	void createMeal(ActionEvent event) {
		String name, type, price, size;
		String[] ingredients;
		name = txtMeal.getText();
		type = txtType.getText();
		price = txtPrice.getText();
		size = txtSize.getText();
		ingredients = areaIngredients.getText().split(",");
		String ingredientsTxt = ingredients.toString();

		if (name != "" && type != "" && price != "" && size != "" && ingredientsTxt != "") {
			rm.addMeal(name, size, price, type, ingredientsTxt);
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("Por favor llene todos los campos.");
			alert.showAndWait();
		}

	}

	/*
	 * @FXML void showDataList(ActionEvent event) { String opc; opc =
	 * txtOpc.getOpc(); rm.createDataList(opc); }
	 */
	// jd

	private void initializeTableViewsMealWindow() {
		ObservableList<Meal> tvMealObservableList = FXCollections.observableArrayList(rm.getMeals());
		tvMeal.setItems(tvMealObservableList);
		tcMeal.setCellValueFactory(new PropertyValueFactory<Meal, String>("name"));

		ObservableList<Ingredient> tvIngredientObservableList = FXCollections.observableArrayList(rm.getIngredients());
		tvIngredients.setItems(tvIngredientObservableList);
		tcIngredients.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("name"));

		ObservableList<FoodType> tvFoodTypeObservableList = FXCollections.observableArrayList(rm.getFoodTypes());
		tvType.setItems(tvFoodTypeObservableList);
		tcType.setCellValueFactory(new PropertyValueFactory<FoodType, String>("type"));

		ObservableList<Size> tvSizeObservableList = FXCollections.observableArrayList(rm.getSizes());
		tvSize.setItems(tvSizeObservableList);
		tcSize.setCellValueFactory(new PropertyValueFactory<Size, String>("name"));
	}

	// Create costumer code.

	@FXML
	private Pane createCostumerPane;

	@FXML
	private Label costumerObservationsArea;

	@FXML
	private TextField costumerNameTxt;

	@FXML
	private TextField costumerLastnames;

	@FXML
	private TextField costumerId;

	@FXML
	private TextField costumerPhone;

	@FXML
	private TextField costumerAddress;

	@FXML
	void createCostumer(ActionEvent event) {
		
		String name = costumerNameTxt.getText(); 
		String lastname = costumerLastnames.getText();
		String observations = costumerObservationsArea.getText();
		String address = costumerAddress.getText();
		
		long costumerPhoneLong = 0L;
		long costumerIdLong = 0L;
		
		if(!costumerPhone.getText().equals("")) {
			costumerPhoneLong = Long.parseLong(costumerPhone.getText()); 
		}else if(!costumerId.getText().equals("")){
			costumerIdLong = Long.parseLong(costumerId.getText());
		}else if(!costumerPhone.getText().equals("")) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
    	    alert.setHeaderText(null);
    	    alert.setTitle("Error");
    	    alert.setContentText("Por favor ingrese el número de télefono del cliente.");
    	    alert.showAndWait();
		}
		
		
		if(name != "" && lastname != "" && address != "" && costumerPhoneLong != 0L) {
			rm.addCostumer(name, lastname, address, observations, costumerPhoneLong, costumerIdLong);
			try {
				showCreateCostumerWindow();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
    	    alert.setHeaderText(null);
    	    alert.setTitle("Error");
    	    alert.setContentText("Por favor llene todos los campos.");
    	    alert.showAndWait();
		}
	}

	// Create order code.

	@FXML
	private TableView<Costumer> tvOrderCostumers;

	@FXML
	private TableColumn<Costumer, String> tcOrderCostumers;

	@FXML
	private TableView<Meal> tvOrderFoodAvaible;

	@FXML
	private TableColumn<Meal, String> tcOrderFoodAvaible;

	@FXML
	private TableView<String> tvOrderFoodRequested;

	@FXML
	private TableColumn<Meal, String> tcOrderFoodRequested;

	@FXML
	private TextArea orderCostumerInfo;

	@FXML
	void createOrder(ActionEvent event) {
		
		ObservableList<String> orderFood = tvOrderFoodRequested.getItems();
		String costumer = orderCostumerInfo.getText();
		
		if(orderFood != null && costumer != "") {
			
		}else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
    	    alert.setHeaderText(null);
    	    alert.setTitle("Error");
    	    alert.setContentText("Por favor llene todos los campos.");
    	    alert.showAndWait();
		}
	}

	private void initializeTableViewsOrderWindow() {
		ObservableList<Costumer> tvCostumerObservableList = FXCollections.observableArrayList(rm.getCostumers());
		tvOrderCostumers.setItems(tvCostumerObservableList);
		tcOrderCostumers.setCellValueFactory(new PropertyValueFactory<Costumer, String>("name"));

		ObservableList<Meal> tvMealObservableList = FXCollections.observableArrayList(rm.getMeals());
		tvOrderFoodAvaible.setItems(tvMealObservableList);
		tcOrderFoodAvaible.setCellValueFactory(new PropertyValueFactory<Meal, String>("name"));
	}

	// Create employees code.

	@FXML
	private Pane EmployeePane;

	@FXML
	private TextField createEmployeeName;

	@FXML
	private TextField createEmployeeId;

	@FXML
	private TextField createEmployeeLastname;

	@FXML
	void createEmployee(ActionEvent event) {
		String employeeName = createEmployeeName.getText();
		long employeeId = Long.parseLong(createEmployeeId.getText());
		String employeeLastname = createEmployeeLastname.getText();

		if(employeeName != "" && createEmployeeId.getText() != "" && employeeLastname != "") {
			rm.addEmployee(employeeName, employeeLastname, employeeId);
		}else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
    	    alert.setHeaderText(null);
    	    alert.setTitle("Error");
    	    alert.setContentText("Por favor llene todos los campos.");
    	    alert.showAndWait();
		}
	}

	// Create users code.

	@FXML
	private Pane createUserPane;

	@FXML
	private TextField userTxtUsername;

	@FXML
	private TextField userTxtId;

	@FXML
	private PasswordField userTxtPassword;

	@FXML
	private TextField userTxtName;

	@FXML
	private TextField userTxtLastname;

	@FXML
	void createUser(ActionEvent event) {
		String userName = userTxtUsername.getText();
		String userPass = userTxtPassword.getText();
		long userId = Long.parseLong(userTxtId.getText());
		String name = userTxtName.getText();
		String lastName = userTxtLastname.getText();

		if(userName != "" && userPass != "" && name != "" && lastName != "" && userTxtId.getText() != "") {
			rm.addUser(userName, userPass, name, lastName, userId);
		}else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
    	    alert.setHeaderText(null);
    	    alert.setTitle("Error");
    	    alert.setContentText("Por favor llene todos los campos.");
    	    alert.showAndWait();
		}
		
	}
	
	//Management code.
	
	//Manage costumers.
	 
	@FXML
	    private TableView<Costumer> tvManageCostumers;

	    @FXML
	    private TableColumn<Costumer, String> tcManageCostumersName;

	    @FXML
	    private TableColumn<Costumer, String> tcManageCostumersLastName;

	    @FXML
	    private TableColumn<Costumer, String> tcManageCostumersAddress;

	    @FXML
	    private TableColumn<Costumer, Long> tcManageCostumersPhone;

	    @FXML
	    private TableColumn<Costumer, Long> tcManageCostumersId;

	    @FXML
	    private TableColumn<Costumer, String> tcManageCostumersObservations;

	    @FXML
	    private TableColumn<Costumer, String> tcManageCostumersEnabled;

	    @FXML
	    void deleteCostumer(ActionEvent event) {

	    }

	    @FXML
	    void disableCostumer(ActionEvent event) {

	    }

	    @FXML
	    void enableCostumer(ActionEvent event) {

	    }
	    
	    private void initializeManagerCostumerWindow() {
	    	ObservableList<Costumer> tvCostumerObservableList = FXCollections.observableArrayList(rm.getCostumers());
	    	tvManageCostumers.setItems(tvCostumerObservableList);
	    	tcManageCostumersName.setCellValueFactory(new PropertyValueFactory<Costumer, String>("name"));
	    	tcManageCostumersLastName.setCellValueFactory(new PropertyValueFactory<Costumer, String>("lastname"));
	    	tcManageCostumersAddress.setCellValueFactory(new PropertyValueFactory<Costumer, String>("address"));
	    	tcManageCostumersObservations.setCellValueFactory(new PropertyValueFactory<Costumer, String>("observations"));
	    	tcManageCostumersId.setCellValueFactory(new PropertyValueFactory<Costumer, Long>("phone"));
	    	tcManageCostumersPhone.setCellValueFactory(new PropertyValueFactory<Costumer, Long>("id"));
	    	tcManageCostumersEnabled.setCellValueFactory(new PropertyValueFactory<Costumer, String>("enabled"));
	    }
	    
	    // Manage employees window code.
	    
	    @FXML
	    private TableView<Employee> tvManageEmployees;

	    @FXML
	    private TableColumn<Employee, String> tcManageEmployeesName;

	    @FXML
	    private TableColumn<Employee, String> tcManageEmployeesLastName;

	    @FXML
	    private TableColumn<Employee, Long> tcManageEmployeesId;

	    @FXML
	    private TableColumn<Employee, String> tcManageEmployeesEnabled;

	    @FXML
	    void deleteEmployee(ActionEvent event) {

	    }

	    @FXML
	    void disableEmployee(ActionEvent event) {

	    }

	    @FXML
	    void enableEmployee(ActionEvent event) {

	    }
	    
	    private void initializeManagerEmployeeWindow() {
	    	ObservableList<Employee> tvEmployeeObservableList = FXCollections.observableArrayList(rm.getEmployees());
	    	tvManageEmployees.setItems(tvEmployeeObservableList);
	    	tcManageEmployeesName.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
	    	tcManageEmployeesLastName.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastname"));
	    	tcManageEmployeesId.setCellValueFactory(new PropertyValueFactory<Employee, Long>("id"));
	    	tcManageEmployeesEnabled.setCellValueFactory(new PropertyValueFactory<Employee, String>("enabled"));
	    }	
	    
	    // Manage ingredients window code.
	    
	    @FXML
	    private TableView<Ingredient> tvManageIngredients;

	    @FXML
	    private TableColumn<Ingredient, String> tcManageIngredientsName;

	    @FXML
	    private TableColumn<Ingredient, String> tcManageIngredientsAllergen;

	    @FXML
	    private TableColumn<Ingredient, String> tcManageIngredientsEnabled;

	    @FXML
	    void deleteIngredient(ActionEvent event) {

	    }

	    @FXML
	    void disableIngredient(ActionEvent event) {

	    }

	    @FXML
	    void enableIngredient(ActionEvent event) {

	    }
	    
	    private void initializeManagerIngredientWindow() {
	    	ObservableList<Ingredient> tvIngredientObservableList = FXCollections.observableArrayList(rm.getIngredients());
	    	tvManageIngredients.setItems(tvIngredientObservableList);
	    	tcManageIngredientsName.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("name"));
	    	tcManageIngredientsAllergen.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("allergen"));
	    	tcManageIngredientsEnabled.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("enabled"));
	    }	
	    
	// Show addition windows code.

	private void showCreateIngredientWindow() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Ingredients.fxml"));
		fxmlLoader.setController(this);
		Parent addIngredient = fxmlLoader.load();
		mainPane.getChildren().setAll(addIngredient);
		initializeTableViewsIngredientWindow();

	}

	private void showCreateMealWindow() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CreateMeal.fxml"));
		fxmlLoader.setController(this);
		Parent addMeal = fxmlLoader.load();
		mainPane.getChildren().setAll(addMeal);
		initializeTableViewsMealWindow();
	}

	private void showCreateCostumerWindow() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CreateCostumer.fxml"));
		fxmlLoader.setController(this);
		Parent addCostumer = fxmlLoader.load();
		mainPane.getChildren().setAll(addCostumer);
	}

	private void showCreateUserWindow() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CreateUser.fxml"));
		fxmlLoader.setController(this);
		Parent addUser = fxmlLoader.load();
		mainPane.getChildren().setAll(addUser);
	}

	private void showCreateEmployee() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CreateEmployee.fxml"));
		fxmlLoader.setController(this);
		Parent addEmployee = fxmlLoader.load();
		mainPane.getChildren().setAll(addEmployee);
	}

	private void showMainWindow() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainFx.fxml"));
		fxmlLoader.setController(this);
		Parent addMain = fxmlLoader.load();
		mainPane.getChildren().setAll(addMain);
	}

	private void showCreateOrderWindow() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CreateOrder.fxml"));
		fxmlLoader.setController(this);
		Parent addOrder = fxmlLoader.load();
		mainPane.getChildren().setAll(addOrder);
		initializeTableViewsOrderWindow();
	}
	
	//Show manage windows code.
	
	private void showManageCostumers() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ManageCostumers.fxml"));
		fxmlLoader.setController(this);
		Parent manageCostumer = fxmlLoader.load();
		mainPane.getChildren().setAll(manageCostumer);
		initializeManagerCostumerWindow();
	}
	
	private void showManageEmployees() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ManageEmployees.fxml"));
		fxmlLoader.setController(this);
		Parent manageEmployees = fxmlLoader.load();
		mainPane.getChildren().setAll(manageEmployees);
		initializeManagerEmployeeWindow();
	}
	
	private void showManageIngredients() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ManageIngredients.fxml"));
		fxmlLoader.setController(this);
		Parent manageIngredients = fxmlLoader.load();
		mainPane.getChildren().setAll(manageIngredients);
		initializeManagerIngredientWindow();
	}
	
	private void showManageUsers() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ManageUsers.fxml"));
		fxmlLoader.setController(this);
		Parent manageUsers = fxmlLoader.load();
		mainPane.getChildren().setAll(manageUsers);
	}
	
	private void showManageMeals() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ManageMeals.fxml"));
		fxmlLoader.setController(this);
		Parent manageMeals = fxmlLoader.load();
		mainPane.getChildren().setAll(manageMeals);
	}
	
	private void showManageOrders() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ManageOrders.fxml"));
		fxmlLoader.setController(this);
		Parent manageOrders = fxmlLoader.load();
		mainPane.getChildren().setAll(manageOrders);
	}
}
