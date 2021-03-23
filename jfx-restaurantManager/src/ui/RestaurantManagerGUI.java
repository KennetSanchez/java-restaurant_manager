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
    	if(user != "") {
    		userActive.setText(user); 	
    	}else {
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    	    alert.setHeaderText(null);
    	    alert.setTitle("Error");
    	    alert.setContentText("Usuario y/o contraseña inexistentes.");
    	    alert.showAndWait();
    	}
    	
    }

    @FXML
    void manageCostumers(ActionEvent event) {

    }

    @FXML
    void manageEmployees(ActionEvent event) {

    }

    @FXML
    void manageIngredients(ActionEvent event) {

    }

    @FXML
    void manageOrders(ActionEvent event) {

    }

    @FXML
    void manageProducts(ActionEvent event) {

    }

    @FXML
    void manageUsers(ActionEvent event) {

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
	
	private void initializateTableViewsIngredientWindow() {
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

		rm.addMeal(name, size, price, type, ingredientsTxt);
	}

	/*
	 * @FXML void showDataList(ActionEvent event) { String opc; opc =
	 * txtOpc.getOpc(); rm.createDataList(opc); }
	 */
	// jd

	private void initializateTableViewsMealWindow() {
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

	}

	// Create order code.

	@FXML
	private TableView<Costumer> tvOrderCostumers;

	@FXML
	private TableColumn<Costumer, String> tcOrderCostumers;

	@FXML
	private TableView<Meal> tvOrderFoodAvaible;

	@FXML
	private TableColumn<Meal , String> tcOrderFoodAvaible;

	@FXML
	private TableView<String> tvOrderFoodRequested;

	@FXML
	private TableColumn<Meal , String> tcOrderFoodRequested;

	@FXML
	private TextArea orderCostumerInfo;

	@FXML
	void createOrder(ActionEvent event) {

	}
	
	private void initializateTableViewsOrderWindow() {
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

		rm.addEmployee(employeeName, employeeLastname, employeeId);
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

		rm.addUser(userName, userPass, name, lastName, userId);
	}

	// Show windows code.

	public void showCreateIngredientWindow() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Ingredients.fxml"));
		fxmlLoader.setController(this);
		Parent addIngredient = fxmlLoader.load();
		mainPane.getChildren().setAll(addIngredient);
		initializateTableViewsIngredientWindow();

	}

	public void showCreateMealWindow() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CreateMeal.fxml"));
		fxmlLoader.setController(this);
		Parent addMeal = fxmlLoader.load();
		mainPane.getChildren().setAll(addMeal);
		initializateTableViewsMealWindow();
	}

	public void showCreateCostumerWindow() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CreateCostumer.fxml"));
		fxmlLoader.setController(this);
		Parent addCostumer = fxmlLoader.load();
		mainPane.getChildren().setAll(addCostumer);
	}
	
	public void showCreateUserWindow() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CreateUser.fxml"));
		fxmlLoader.setController(this);
		Parent addUser = fxmlLoader.load();
		mainPane.getChildren().setAll(addUser);
	}
	
	public void showCreateEmployee() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CreateEmployee.fxml"));
		fxmlLoader.setController(this);
		Parent addEmployee = fxmlLoader.load();
		mainPane.getChildren().setAll(addEmployee);
	}

	public void showMainWindow() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainFx.fxml"));
		fxmlLoader.setController(this);
		Parent addMain = fxmlLoader.load();
		mainPane.getChildren().setAll(addMain);
	}

	public void showCreateOrderWindow() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CreateOrder.fxml"));
		fxmlLoader.setController(this);
		Parent addOrder = fxmlLoader.load();
		mainPane.getChildren().setAll(addOrder);
		initializateTableViewsOrderWindow();
	}
}
