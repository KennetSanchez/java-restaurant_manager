package ui;

import javafx.application.Platform;
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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.FormatStyle;

import model.*;

public class RestaurantManagerGUI {

	RestaurantManager rm;

	public RestaurantManagerGUI() throws IOException, FileNotFoundException {
		rm = new RestaurantManager();
		
	}

	public void initialize() throws IOException {		
		new Thread() {
			public void run() {
				showHour();
			}
		}.start();
	}

	// This method crash the program, and enter into a loop.
	private void showHour() {
		boolean finished = false;
		// If the program isn't finished, it will refresh the date every 10000
		// miliseconds (1 second).
		while (!finished) {
			if (mainPane.isVisible()) {
				try {
					Platform.runLater(new Thread() {
						public void run() {
							date.setText(timeAndDate());
						}
					});
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
		FormatStyle timeStyle = FormatStyle.MEDIUM;
		
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedTime(timeStyle);
		String lt= LocalTime.now().format(formatter);
		
		
		DateTimeFormatter formatterH = DateTimeFormatter.ofLocalizedDate(timeStyle);
		String ld = LocalDate.now().format(formatterH);
		
		String msg = ld + " - " + lt;
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
	
    @FXML
    private Pane loginPane;


	// Menu code.

	@FXML
	void createCostumers(ActionEvent event) throws IOException {
		if(userActive.getText() != ""){
			showCreateCostumerWindow();
		}else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error.");
			alert.setContentText("Debe iniciar sesión con un usuario primero.");
			alert.showAndWait();
		}			
	}

	@FXML
	void createEmployees(ActionEvent event) throws IOException {
		if(userActive.getText() != ""){
			showCreateEmployee();
		}else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error.");
			alert.setContentText("Debe iniciar sesión con un usuario primero.");
			alert.showAndWait();
		}	
	}

	@FXML
	void createIngredients(ActionEvent event) throws IOException {
		if(userActive.getText() != ""){
			showCreateIngredientWindow();
		}else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error.");
			alert.setContentText("Debe iniciar sesión con un usuario primero.");
			alert.showAndWait();
		}	
	}

	@FXML
	void createOrders(ActionEvent event) throws IOException {
		if(userActive.getText() != ""){
			showCreateOrderWindow();
		}else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error.");
			alert.setContentText("Debe iniciar sesión con un usuario primero.");
			alert.showAndWait();
		}	
	}

	@FXML
	void createProducts(ActionEvent event) throws IOException {
		if(userActive.getText() != ""){
			showCreateMealWindow();
		}else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error.");
			alert.setContentText("Debe iniciar sesión con un usuario primero.");
			alert.showAndWait();
		}	
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
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Hecho.");
			alert.setContentText("Inicio de sesión exitoso.");
			alert.showAndWait();
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
		if(userActive.getText() != ""){
			showManageCostumers();
		}else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error.");
			alert.setContentText("Debe iniciar sesión con un usuario primero.");
			alert.showAndWait();
		}	
	}

	@FXML
	void manageEmployees(ActionEvent event) throws IOException {
		if(userActive.getText() != ""){
			showManageEmployees();
		}else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error.");
			alert.setContentText("Debe iniciar sesión con un usuario primero.");
			alert.showAndWait();
		}	
	}

	@FXML
	void manageIngredients(ActionEvent event) throws IOException {
		if(userActive.getText() != ""){
			showManageIngredients();
		}else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error.");
			alert.setContentText("Debe iniciar sesión con un usuario primero.");
			alert.showAndWait();
		}	
	}

	@FXML
	void manageOrders(ActionEvent event) throws IOException {
		if(userActive.getText() != ""){
			showManageOrders();
		}else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error.");
			alert.setContentText("Debe iniciar sesión con un usuario primero.");
			alert.showAndWait();
		}	
	}

	@FXML
	void manageProducts(ActionEvent event) throws IOException {
		if(userActive.getText() != ""){
			showManageMeals();
		}else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error.");
			alert.setContentText("Debe iniciar sesión con un usuario primero.");
			alert.showAndWait();
		}	
	}

	@FXML
	void manageUsers(ActionEvent event) throws IOException {
		if(userActive.getText() != ""){
			showManageUsers();
		}else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error.");
			alert.setContentText("Debe iniciar sesión con un usuario primero.");
			alert.showAndWait();
		}	
	}

	@FXML
	void showDevelopers(ActionEvent event) {

	}

	@FXML
	void showHelp(ActionEvent event) {

	}

	
	@FXML
    void changeUser(ActionEvent event) throws IOException {
		showLoginWindow();
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
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Hecho.");
			alert.setContentText("El ingrediente ha sido añadido exitosamente.");
			alert.showAndWait();
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
			txtMeal.clear();
			txtType.clear();
			txtPrice.clear();
			txtSize.clear();
			areaIngredients.clear();
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Creación completada.");
			alert.setContentText("El producto ha sido añadido exitosamente.");
			alert.showAndWait();
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
    private TextArea costumerObservationsArea;


	@FXML
	void createCostumer(ActionEvent event) {

		String name = costumerNameTxt.getText();
		String lastname = costumerLastnames.getText();
		String observations = costumerObservationsArea.getText();
		String address = costumerAddress.getText();

		long costumerPhoneLong = 0L;
		long costumerIdLong = 0L;

		if (!costumerPhone.getText().equals("")) {
			costumerPhoneLong = Long.parseLong(costumerPhone.getText());
		} else if (!costumerId.getText().equals("")) {
			costumerIdLong = Long.parseLong(costumerId.getText());
		} else if (!costumerPhone.getText().equals("")) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("Por favor ingrese el número de télefono del cliente.");
			alert.showAndWait();
		}

		if (name != "" && lastname != "" && address != "" && costumerPhoneLong != 0L) {
			rm.addCostumer(name, lastname, address, observations, costumerPhoneLong, costumerIdLong);
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Hecho.");
			alert.setContentText("El cliente ha sido añadida exitosamente.");
			alert.showAndWait();
			try {
				showCreateCostumerWindow();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("Por favor llene todos los campos.");
			alert.showAndWait();
		}
	}

	// Create order code.

		ObservableList<Meal> orderFood = null;
	
		@FXML
	    private TableView<Costumer> tvOrderCostumers;

	    @FXML
	    private TableColumn<Costumer, String> tcOrderCostumers;

	    @FXML
	    private TableColumn<Costumer, String> tcOrderCostumersPhone;

	    @FXML
	    private TableView<Meal> tvOrderFoodAvaible;

	    @FXML
	    private TableColumn<Meal, String> tcOrderFoodAvaible;

	    @FXML
	    private TableColumn<Meal, String> tcOrderFoodAvaibleSize;

	    @FXML
	    private TableColumn<Meal, Double> tcOrderFoodAvaiblePrice;

	    @FXML
	    private TableView<Meal> tvOrderFoodRequested;

	    @FXML
	    private TableColumn<Meal, String> tcOrderFoodRequested;


	    @FXML
	    private TableColumn<Meal, String> tcOrderFoodRequestedSize;
	    
	    @FXML
	    private TableColumn<Meal, Double> tcOrderFoodRequestedPrice;
	     
	    @FXML
	    private TextArea orderCostumerInfo;

	    @FXML
	    void addMealToOrder(ActionEvent event) {
	    	int index = tvOrderCostumers.getSelectionModel().getSelectedIndex();;
	    	if(index >= 0) {
	    		orderFood.add(rm.getMeals().get(index));
	    		tvOrderFoodRequested.setItems(orderFood);    	
	    	}
	    }

	    @FXML
	    void chooseCostumer(ActionEvent event) {
	    	int index = tvOrderCostumers.getSelectionModel().getSelectedIndex();
	    	orderCostumerInfo.setText(rm.getCostumers().get(index).getName() + " - " + rm.getCostumers().get(index).getPhone());    	
	    }


	@FXML
	void createOrder(ActionEvent event) throws IOException {

		ObservableList<Meal> orderFood = tvOrderFoodRequested.getItems();
		String costumer = orderCostumerInfo.getText();

		if (orderFood != null && costumer != "") {
			rm.createEmployeeList();
			initializeTableViewsOrderWindow();
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Hecho.");
			alert.setContentText("La orden ha sido añadido exitosamente.");
			alert.showAndWait();

		} else {
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
		tcOrderCostumersPhone.setCellValueFactory(new PropertyValueFactory<Costumer, String>("phone"));

		ObservableList<Meal> tvMealObservableList = FXCollections.observableArrayList(rm.getMeals());
		tvOrderFoodAvaible.setItems(tvMealObservableList);
		tcOrderFoodAvaible.setCellValueFactory(new PropertyValueFactory<Meal, String>("name"));
		tcOrderFoodAvaibleSize.setCellValueFactory(new PropertyValueFactory<Meal, String>("size"));
		tcOrderFoodAvaiblePrice.setCellValueFactory(new PropertyValueFactory<Meal, Double>("price"));
		
		
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

		if (employeeName != "" && createEmployeeId.getText() != "" && employeeLastname != "") {
			rm.addEmployee(employeeName, employeeLastname, employeeId);
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Hecho.");
			alert.setContentText("El empleado ha sido añadido exitosamente.");
			alert.showAndWait();
		} else {
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

		if (userName != "" && userPass != "" && name != "" && lastName != "" && userTxtId.getText() != "") {
			rm.addUser(userName, userPass, name, lastName, userId);
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Hecho.");
			alert.setContentText("El usuario ha sido creado exitosamente.");
			alert.showAndWait();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("Por favor llene todos los campos.");
			alert.showAndWait();
		}

	}

	// Management code.

	// Manage costumers.

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
	void deleteCostumer(ActionEvent event) throws IOException {
		int index = tvManageCostumers.getSelectionModel().getSelectedIndex();
		boolean founded = rm.deleteCostumer(index);
		
		if(founded) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Realizado");
			alert.setContentText("El cliente ha sido eliminado exitosamente.");
			showManageCostumers();
			alert.showAndWait();
		}else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("El cliente no ha sido eliminado. Seleccione uno e intente de nuevo.");
			alert.showAndWait();
		}
		
	}

	@FXML
	void disableCostumer(ActionEvent event) throws IOException {
		String state = "No";
		
		int index = tvManageCostumers.getSelectionModel().getSelectedIndex();
		boolean founded = rm.changeStateCostumer(index, state);
		
		if(founded) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Realizado");
			alert.setContentText("El cliente ha sido deshabilitado exitosamente.");
			showManageCostumers();		
			alert.showAndWait();
		}else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("El cliente no ha sido deshabilitado. Seleccione uno e intente de nuevo.");
			alert.showAndWait();
		}
	}

	@FXML
	void enableCostumer(ActionEvent event) throws IOException {
		String state = "Sí";
		
		int index = tvManageCostumers.getSelectionModel().getSelectedIndex();
		boolean founded = rm.changeStateCostumer(index, state);
		
		if(founded) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Realizado");
			alert.setContentText("El cliente ha sido deshabilitado exitosamente.");
			showManageCostumers();
			alert.showAndWait();
		}else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("El cliente no ha sido habilitado. Seleccione uno e intente de nuevo.");
			alert.showAndWait();
		}
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
	void deleteEmployee(ActionEvent event) throws IOException {
		int index = tvManageEmployees.getSelectionModel().getSelectedIndex();
		boolean founded = rm.deleteEmployee(index);
		
		if(founded) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Realizado");
			alert.setContentText("El empleado ha sido eliminado exitosamente.");
			showManageEmployees();
			alert.showAndWait();
		}else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("El empleado no ha sido eliminado. Seleccione uno e intente de nuevo.");
			alert.showAndWait();
		}
		
	}

	@FXML
	void disableEmployee(ActionEvent event) throws IOException {
		String state = "No";
		
		int index = tvManageEmployees.getSelectionModel().getSelectedIndex();
		boolean founded = rm.changeStateEmployee(index, state);
		
		if(founded) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Realizado");
			alert.setContentText("El usuario ha sido deshabilitado exitosamente.");
			showManageEmployees();
			alert.showAndWait();
		}else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("El usuario no ha sido deshabilitado. Seleccione uno e intente de nuevo.");
			alert.showAndWait();
		}
	}

	@FXML
	void enableEmployee(ActionEvent event) throws IOException {
		String state = "Sí";
		
		int index = tvManageEmployees.getSelectionModel().getSelectedIndex();
		boolean founded = rm.changeStateEmployee(index, state);
		
		if(founded) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Realizado");
			alert.setContentText("El empleado ha sido deshabilitado exitosamente.");
			showManageEmployees();
			alert.showAndWait();
		}else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("El empleado no ha sido deshabilitado. Seleccione uno e intente de nuevo.");
			alert.showAndWait();
		}
	}

	private void initializeManagerEmployeeWindow() {
		ObservableList<Employee> tvEmployeeObservableList = FXCollections.observableArrayList(rm.getEmployees());
		tvManageEmployees.setItems(tvEmployeeObservableList);
		tcManageEmployeesName.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
		tcManageEmployeesLastName.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastname"));
		tcManageEmployeesId.setCellValueFactory(new PropertyValueFactory<Employee, Long>("id"));
		tcManageEmployeesEnabled.setCellValueFactory(new PropertyValueFactory<Employee, String>("enabledE"));
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
	void deleteIngredient(ActionEvent event) throws IOException {
		int index = tvManageIngredients.getSelectionModel().getSelectedIndex();
		boolean founded = rm.deleteIngredient(index);
		
		if(founded) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Realizado");
			alert.setContentText("El ingrediente ha sido removido exitosamente.");
			showManageIngredients();
			alert.showAndWait();
		}else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("El ingrediente no ha sido removido. Seleccione uno e intente de nuevo.");
			alert.showAndWait();
		}
	}

	@FXML
	void disableIngredient(ActionEvent event) throws IOException {
		String state = "No";
		
		int index = tvManageIngredients.getSelectionModel().getSelectedIndex();
		boolean founded = rm.changeStateIngredient(index, state);
		
		if(founded) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Realizado");
			alert.setContentText("El ingrediente ha sido deshabilitado exitosamente.");
			showManageIngredients();
			alert.showAndWait();
		}else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("El ingrediente no ha sido deshabilitado. Seleccione uno e intente de nuevo.");
			alert.showAndWait();
		}
	}

	@FXML
	void enableIngredient(ActionEvent event) throws IOException {
		String state = "Sí";
		
		int index = tvManageIngredients.getSelectionModel().getSelectedIndex();
		boolean founded = rm.changeStateIngredient(index, state);
		
		if(founded) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Realizado");
			alert.setContentText("El ingrediente ha sido habilitado exitosamente.");
			showManageIngredients();
			alert.showAndWait();
		}else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("El ingrediente no ha sido habilitado. Seleccione uno e intente de nuevo.");
			alert.showAndWait();
		}
	}

	private void initializeManagerIngredientWindow() {
		ObservableList<Ingredient> tvIngredientObservableList = FXCollections.observableArrayList(rm.getIngredients());
		tvManageIngredients.setItems(tvIngredientObservableList);
		tcManageIngredientsName.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("name"));
		tcManageIngredientsAllergen.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("allergen"));
		tcManageIngredientsEnabled.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("enabled"));
	}

	// Manage users code.

	@FXML
	private TableView<User> tvManageUsers;

	@FXML
	private TableColumn<User, String> tcManageUsersUserName;

	@FXML
	private TableColumn<User, String> tcManageUsersName;

	@FXML
	private TableColumn<User, String> tcManageUsersLastName;

	@FXML
	private TableColumn<User, Long> tcManageUsersId;

	@FXML
	private TableColumn<User, String> tcManageUsersUserEnabled;

	@FXML
	private TableColumn<User, String> tcManageUsersEmployeeEnabled;

	private void initializeManagerUsersWindow() {
		ObservableList<User> tvUserObservableList = FXCollections.observableArrayList(rm.getUsers());
		tvManageUsers.setItems(tvUserObservableList);
		tcManageUsersUserName.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
		tcManageUsersName.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
		tcManageUsersLastName.setCellValueFactory(new PropertyValueFactory<User, String>("lastname"));
		tcManageUsersId.setCellValueFactory(new PropertyValueFactory<User, Long>("id"));
		tcManageUsersUserEnabled.setCellValueFactory(new PropertyValueFactory<User, String>("enabledU"));
		tcManageUsersEmployeeEnabled.setCellValueFactory(new PropertyValueFactory<User, String>("enabledE"));
	}

	@FXML
	void deleteUser(ActionEvent event) throws IOException {
		int index = tvManageUsers.getSelectionModel().getSelectedIndex();
		boolean founded = rm.deleteUser(index);
		
		if(founded) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Realizado");
			alert.setContentText("El usuario ha sido removido exitosamente.");
			showManageUsers();
			alert.showAndWait();
		}else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("El usuario no ha sido removido. Seleccione uno e intente de nuevo.");
			alert.showAndWait();
		}
	}

	@FXML
	void disableUser(ActionEvent event) throws IOException {
		String state = "No";
		
		int index = tvManageUsers.getSelectionModel().getSelectedIndex();
		boolean founded = rm.changeStateUsert(index, state);
		
		if(founded) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Realizado");
			alert.setContentText("El usuario ha sido deshabilitado exitosamente.");
			showManageUsers();
			alert.showAndWait();
		}else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("El usuario no ha sido deshabilitado. Seleccione uno e intente de nuevo.");
			alert.showAndWait();
		}
	}

	@FXML
	void enableUser(ActionEvent event) throws IOException {
		String state = "No";
		
		int index = tvManageUsers.getSelectionModel().getSelectedIndex();
		boolean founded = rm.changeStateUsert(index, state);
		
		if(founded) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Realizado");
			alert.setContentText("El usuario ha sido habilitado exitosamente.");
			showManageUsers();
			alert.showAndWait();
		}else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("El usuario no ha sido habilitado. Seleccione uno e intente de nuevo.");
			alert.showAndWait();
		}
	}

	// Manage Orders code.

	@FXML
	private TableView<Order> tvManageOrders;

	@FXML
	private TableColumn<Order, String> tcManageOrdersCostumer;

	@FXML
	private TableColumn<Order, String> tcManageOrdersEmployee;

	@FXML
	private TableColumn<Order, String> tcManageOrdersDate;

	@FXML
	private TableColumn<Order, String> tcManageOrdersCode;

	@FXML
	private TableColumn<Order, String> tcManageOrdersMeals;

	@FXML
	private TableColumn<Order, String> tcManageOrdersObservations;

	@FXML
	private TableColumn<Order, String> tcManageOrdersEnabled;

	@FXML
	private TableColumn<Order, String> tcManageOrdersState;

	private void initializeManagerOrdersWindow() {
		ObservableList<Order> tvOrderObservableList = FXCollections.observableArrayList(rm.getOrders());
		tvManageOrders.setItems(tvOrderObservableList);
		tcManageOrdersCostumer.setCellValueFactory(new PropertyValueFactory<Order, String>("costumerName"));
		tcManageOrdersEmployee.setCellValueFactory(new PropertyValueFactory<Order, String>("employeeName"));
		tcManageOrdersDate.setCellValueFactory(new PropertyValueFactory<Order, String>("date"));
		tcManageOrdersCode.setCellValueFactory(new PropertyValueFactory<Order, String>("code"));
		tcManageOrdersMeals.setCellValueFactory(new PropertyValueFactory<Order, String>("mealsTxt"));
		tcManageOrdersObservations.setCellValueFactory(new PropertyValueFactory<Order, String>("observations"));
		tcManageOrdersEnabled.setCellValueFactory(new PropertyValueFactory<Order, String>("enabled"));
		tcManageOrdersState.setCellValueFactory(new PropertyValueFactory<Order, String>("status"));
	}

	@FXML
	void deleteOrder(ActionEvent event) throws IOException {
		int index = tvManageOrders.getSelectionModel().getSelectedIndex();
		boolean founded = rm.deleteOrder(index);
		
		if(founded) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Realizado");
			alert.setContentText("La orden ha sido removida exitosamente.");
			showManageOrders();
			alert.showAndWait();
		}else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("La orden no ha sido removida. Seleccione una e intente de nuevo.");
			alert.showAndWait();
		}
	}

	@FXML
	void disableOrder(ActionEvent event) throws IOException {
		String state = "No";
		
		int index = tvManageOrders.getSelectionModel().getSelectedIndex();
		boolean founded = rm.changeStateOrder(index, state);
		
		if(founded) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Realizado");
			alert.setContentText("La orden ha sido deshabilitada exitosamente.");
			showManageOrders();
			alert.showAndWait();
		}else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("La orden no ha sido deshabilitada. Seleccione uno e intente de nuevo.");
			alert.showAndWait();
		}
	}

	@FXML
	void enableOrder(ActionEvent event) throws IOException {
		String state = "No";
		
		int index = tvManageIngredients.getSelectionModel().getSelectedIndex();
		boolean founded = rm.changeStateOrder(index, state);
		
		if(founded) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Realizado");
			alert.setContentText("La orden ha sido habilitada exitosamente.");
			showManageOrders();
			alert.showAndWait();
		}else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("La orden no ha sido habilitada. Seleccione uno e intente de nuevo.");
			alert.showAndWait();
		}
	}

	@FXML
	void refreshState(ActionEvent event) {
		
	}

	// Manage Meals code.

	@FXML
	private TableView<Meal> tvManageMeals;

	@FXML
	private TableColumn<Meal, String> tcManageMealsName;

	@FXML
	private TableColumn<Meal, String> tcManageMealsSize;

	@FXML
	private TableColumn<Meal, Double> tcManageMealsPrice;

	@FXML
	private TableColumn<Meal, String> tcManageMealsType;

	@FXML
	private TableColumn<Meal, String> tcManageMealsIngredients;

	@FXML
	private TableColumn<Meal, String> tcManageMealsAllergens;

	@FXML
	private TableColumn<Meal, String> tcManageMealsEnabled;

	private void initializeManagerMealsWindow() {
		ObservableList<Meal> tvMealObservableList = FXCollections.observableArrayList(rm.getMeals());
		tvManageMeals.setItems(tvMealObservableList);
		tcManageMealsName.setCellValueFactory(new PropertyValueFactory<Meal, String>("name"));
		tcManageMealsSize.setCellValueFactory(new PropertyValueFactory<Meal, String>("size"));
		tcManageMealsPrice.setCellValueFactory(new PropertyValueFactory<Meal, Double>("price"));
		tcManageMealsType.setCellValueFactory(new PropertyValueFactory<Meal, String>("type"));
		tcManageMealsIngredients.setCellValueFactory(new PropertyValueFactory<Meal, String>("ingredientsTxt"));
		tcManageMealsAllergens.setCellValueFactory(new PropertyValueFactory<Meal, String>("allergensTxt"));
		tcManageMealsEnabled.setCellValueFactory(new PropertyValueFactory<Meal, String>("enabled"));
	}

	@FXML
	void deleteMeal(ActionEvent event) throws IOException {
		int index = tvManageMeals.getSelectionModel().getSelectedIndex();
		boolean founded = rm.deleteMeal(index);
		
		if(founded) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Realizado");
			alert.setContentText("El producto ha sido removido exitosamente.");
			showManageMeals();
			alert.showAndWait();
		}else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("El producto no ha sido removido. Seleccione uno e intente de nuevo.");
			alert.showAndWait();
		}
	}

	@FXML
	void disableMeal(ActionEvent event) {

	}

	@FXML
	void enableMeal(ActionEvent event) {

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

	private void showLoginWindow() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
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

	// Show manage windows code.

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
		initializeManagerUsersWindow();
	}

	private void showManageMeals() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ManageMeals.fxml"));
		fxmlLoader.setController(this);
		Parent manageMeals = fxmlLoader.load();
		mainPane.getChildren().setAll(manageMeals);
		initializeManagerMealsWindow();
	}

	private void showManageOrders() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ManageOrders.fxml"));
		fxmlLoader.setController(this);
		Parent manageOrders = fxmlLoader.load();
		mainPane.getChildren().setAll(manageOrders);
		initializeManagerOrdersWindow();
	}

}
