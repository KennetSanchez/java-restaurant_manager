package ui;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.control.cell.TextFieldListCellBuilder;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.LineEvent.Type;

import model.*;

public class RestaurantManagerGUI {

	RestaurantManager rm;
	Employee currentEmployee;

	public RestaurantManagerGUI() throws IOException, FileNotFoundException, ClassNotFoundException, EOFException {
		rm = new RestaurantManager();

	}

	public void initialize() throws IOException {
		new Thread() {
			public void run() {
				showHour();
			}
		}.start();
	}

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

	public String timeAndDate() {
		FormatStyle timeStyle = FormatStyle.MEDIUM;

		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedTime(timeStyle);
		String lt = LocalTime.now().format(formatter);

		DateTimeFormatter formatterH = DateTimeFormatter.ofLocalizedDate(timeStyle);
		String ld = LocalDate.now().format(formatterH);

		String msg = ld + " - " + lt;
		return msg;
	}

	// Main items.

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
	void manageSizes(ActionEvent event) throws IOException {
		if (userActive.getText() != "") {
			showManageSizes();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error.");
			alert.setContentText("Debe iniciar sesión con un usuario primero.");
			alert.showAndWait();
		}
	}

	@FXML
	void manageTypes(ActionEvent event) throws IOException {
		if (userActive.getText() != "") {
			showManageTypes();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error.");
			alert.setContentText("Debe iniciar sesión con un usuario primero.");
			alert.showAndWait();
		}
	}

	@FXML
	void showCreateSize(ActionEvent event) throws IOException {
		if (userActive.getText() != "") {
			showCreateSizeWindow();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error.");
			alert.setContentText("Debe iniciar sesión con un usuario primero.");
			alert.showAndWait();
		}
	}

	@FXML
	void showCreateType(ActionEvent event) throws IOException {
		if (userActive.getText() != "") {
			showCreateTypeWindow();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error.");
			alert.setContentText("Debe iniciar sesión con un usuario primero.");
			alert.showAndWait();
		}

	}

	@FXML
	void createCostumers(ActionEvent event) throws IOException {
		if (userActive.getText() != "") {
			showCreateCostumerWindow();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error.");
			alert.setContentText("Debe iniciar sesión con un usuario primero.");
			alert.showAndWait();
		}
	}

	@FXML
	void createEmployees(ActionEvent event) throws IOException {
		if (userActive.getText() != "") {
			showCreateEmployee();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error.");
			alert.setContentText("Debe iniciar sesión con un usuario primero.");
			alert.showAndWait();
		}
	}

	@FXML
	void createIngredients(ActionEvent event) throws IOException {
		if (userActive.getText() != "") {
			showCreateIngredientWindow();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error.");
			alert.setContentText("Debe iniciar sesión con un usuario primero.");
			alert.showAndWait();
		}
	}

	@FXML
	void createOrders(ActionEvent event) throws IOException {
		if (userActive.getText() != "") {
			showCreateOrderWindow();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error.");
			alert.setContentText("Debe iniciar sesión con un usuario primero.");
			alert.showAndWait();
		}
	}

	@FXML
	void createProducts(ActionEvent event) throws IOException {
		if (userActive.getText() != "") {
			showCreateMealWindow();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error.");
			alert.setContentText("Debe iniciar sesión con un usuario primero.");
			alert.showAndWait();
		}
	}

	@FXML
	void createUsers(ActionEvent event) throws IOException {
		if (userActive.getText() != "") {
			showCreateUserWindow();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error.");
			alert.setContentText("Debe iniciar sesión con un usuario primero.");
			alert.showAndWait();
		}

	}

	@FXML
	void login(ActionEvent event) {
		String name = userText.getText();
		String password = passwordText.getText();
		String user="";

		currentEmployee = rm.login(name, password);
		if(currentEmployee!=null) { 
			user = currentEmployee.getName() + " " + currentEmployee.getLastname();
		}

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
		if (userActive.getText() != "") {
			showManageCostumers();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error.");
			alert.setContentText("Debe iniciar sesión con un usuario primero.");
			alert.showAndWait();
		}
	}

	@FXML
	void manageEmployees(ActionEvent event) throws IOException {
		if (userActive.getText() != "") {
			showManageEmployees();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error.");
			alert.setContentText("Debe iniciar sesión con un usuario primero.");
			alert.showAndWait();
		}
	}

	@FXML
	void manageIngredients(ActionEvent event) throws IOException {
		if (userActive.getText() != "") {
			showManageIngredients();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error.");
			alert.setContentText("Debe iniciar sesión con un usuario primero.");
			alert.showAndWait();
		}
	}

	@FXML
	void manageOrders(ActionEvent event) throws IOException {
		if (userActive.getText() != "") {
			showManageOrders();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error.");
			alert.setContentText("Debe iniciar sesión con un usuario primero.");
			alert.showAndWait();
		}
	}

	@FXML
	void manageProducts(ActionEvent event) throws IOException {
		if (userActive.getText() != "") {
			showManageMeals();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error.");
			alert.setContentText("Debe iniciar sesión con un usuario primero.");
			alert.showAndWait();
		}
	}

	@FXML
	void manageUsers(ActionEvent event) throws IOException {
		if (userActive.getText() != "") {
			showManageUsers();
		} else {
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
	
	@FXML
	void generateReport(ActionEvent event)throws IOException{
		if(userActive.getText() != "") {
			//showGenerateReport();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR); alert.setHeaderText(null);
			alert.setTitle("Error.");
			alert.setContentText("Debe iniciar sesión con un usuario primero.");
			alert.showAndWait(); 
		}
	}
	 

	@FXML
	void sellsByEm(ActionEvent event) throws IOException {
		rm.sellsByEmployee();
		if(userActive.getText() != "") {
			rm.sellsByEmployee(); Alert alert = new Alert(Alert.AlertType.INFORMATION); alert.setHeaderText(null);
			alert.setTitle("Reporte");
			alert.setContentText("Reporte de Empleados creado en el archivo.");
			alert.showAndWait();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error.");
			alert.setContentText("Debe iniciar sesión con un usuario primero.");
			alert.showAndWait();
		}
	}

	@FXML
	void sellsByPr(ActionEvent event) throws IOException {
		rm.sellsByProduct();
		if(userActive.getText() != "") {
			rm.sellsByProduct(); Alert alert = new Alert(Alert.AlertType.INFORMATION); alert.setHeaderText(null);
			alert.setTitle("Reporte");
			alert.setContentText("Reporte de ventas por Producto creado.");
			alert.showAndWait();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null); alert.setTitle("Error.");
			alert.setContentText("Debe iniciar sesión con un usuario primero.");
			alert.showAndWait();
		}
	}

	// Create code.

	// Create Report Code

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
	void createIngredient(ActionEvent event) throws IOException {
		String name = txtIngredient.getText();
		boolean allergen = allergenCheckBox.isSelected();
		if (!name.isEmpty()) {
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
		ObservableList<Ingredient> tvIngredientObservableList = FXCollections.observableArrayList(rm.getIngredientsEnabled());
			
		tvIngredientsCreated.setItems(tvIngredientObservableList);
		tcIngredientsCreated.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("name"));		
		
		tvIngredientsCreated.setEditable(true);
		tcIngredientsCreated.setEditable(true);
		
	}

	@FXML
	void sortIngredients(Event event) {
		System.out.println("Acomodando ingredientes.");
	}

	

	// Create size code.

	@FXML
	private Pane sizePane;

	@FXML
	private TextField txtSizeCreation;

	@FXML
	private TableView<Size> tvSizeCreated;

	@FXML
	private TableColumn<Size, String> tcSizeCreated;

	@FXML
	void createSize(ActionEvent event) throws IOException {
		String name = txtSizeCreation.getText();
		if (!name.isEmpty()) {
			rm.addSize(name);
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Realizado");
			alert.setContentText("Tamaño añadido.");
			alert.showAndWait();
			initializeCreationSize();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("No ha ingresado el tamaño.");
			alert.showAndWait();
		}
	}

	private void initializeCreationSize() {
		ObservableList<Size> tvSizesObservableList = FXCollections.observableArrayList(rm.getSizesEnabled());
		tvSizeCreated.setItems(tvSizesObservableList);
		tcSizeCreated.setCellValueFactory(new PropertyValueFactory<Size, String>("name"));
	}

	// Create type code.

	@FXML
	private Pane typePane;

	@FXML
	private TextField txtTypeCreation;

	@FXML
	private TableView<FoodType> tvTypeCreated;

	@FXML
	private TableColumn<FoodType, String> tcTypeCreated;

	@FXML
	void createType(ActionEvent event) throws IOException {
		String name = txtTypeCreation.getText();
		if (!name.isEmpty()) {
			rm.addFoodType(name);
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Realizado");
			alert.setContentText("Tamaño añadido.");
			alert.showAndWait();
			initializeCreationType();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("No ha ingresado el tamaño.");
			alert.showAndWait();
		}
	}

	private void initializeCreationType() {
		ObservableList<FoodType> tvTypeCreatedObservableList = FXCollections.observableArrayList(rm.getFoodTypesEnabled());
		tvTypeCreated.setItems(tvTypeCreatedObservableList);
		tcTypeCreated.setCellValueFactory(new PropertyValueFactory<FoodType, String>("name"));
	}
	
	/*
	 * @FXML void showDataList(ActionEvent event) { String opc; opc =
	 * txtOpc.getOpc(); rm.createDataList(opc); }
	 */
	// jd

	// Create meal code.

	//This helps us to save the ingredients of each order. And show the user when he adds an ingredient.
	
	ArrayList<Ingredient> ingredientsArray = new ArrayList<Ingredient>();
	String msg = "";
	
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
    void chooseIngredientMealCreation(ActionEvent event) {
		Ingredient ingredientChoosed = tvIngredients.getSelectionModel().getSelectedItem();
		ingredientsArray.add(ingredientChoosed);

		
		if(msg.equals("")) {
			msg = ingredientChoosed.getName() + ", ";
		}else {
			msg += ingredientChoosed.getName() + ", ";		
		}
	
		areaIngredients.setText(msg);
		initializeTableViewsMealWindow();
    }

    @FXML
    void chooseSizeMealCreation(ActionEvent event) {
    	Size sizeChoosed = tvSize.getSelectionModel().getSelectedItem();
    	txtSize.setText(sizeChoosed.getName());
    }

    @FXML
    void chooseTypeMealCreation(ActionEvent event) {
    	FoodType typeChoosed = tvType.getSelectionModel().getSelectedItem();
    	txtType.setText(typeChoosed.getName());
    }
    
	@FXML
	void createMeal(ActionEvent event) throws IOException {
		String name, type, price, size;
		String[] ingredients;
		name = txtMeal.getText();
		type = txtType.getText();
		price = txtPrice.getText();
		size = txtSize.getText();
		ingredients = areaIngredients.getText().split(",");
		String ingredientsTxt = ingredients.toString();

		if (!name.isEmpty() && !type.isEmpty() && !price.isEmpty() && !size.isEmpty() && !ingredientsTxt.isEmpty()) {
			
			txtMeal.clear();
			txtType.clear();
			txtPrice.clear();
			txtSize.clear();
			areaIngredients.clear();
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			
			//This validates if the user choosed the ingredients, or if he write them.
			if(ingredientsArray.size() > 0) {
				rm.addMeal(name, size, price, type, ingredientsArray);
			}else {
				rm.addMeal(name, size, price, type, ingredientsTxt);
			}
			
			
			alert.setTitle("Creación completada.");
			alert.setContentText("El producto ha sido añadido exitosamente.");
			alert.showAndWait();
			
			//Dudoso.
			ingredientsArray = null;
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("Por favor llene todos los campos.");
			alert.showAndWait();
		}

	}

	private void initializeTableViewsMealWindow() {
		ObservableList<Meal> tvMealObservableList = FXCollections.observableArrayList(rm.getMealsEnabled());
		tvMeal.setItems(tvMealObservableList);
		tcMeal.setCellValueFactory(new PropertyValueFactory<Meal, String>("name"));

		ObservableList<Ingredient> tvIngredientObservableList = FXCollections.observableArrayList(rm.getIngredients());
		tvIngredients.setItems(tvIngredientObservableList);
		tcIngredients.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("name"));

		ObservableList<FoodType> tvFoodTypeObservableList = FXCollections.observableArrayList(rm.getFoodTypes());
		tvType.setItems(tvFoodTypeObservableList);
		tcType.setCellValueFactory(new PropertyValueFactory<FoodType, String>("name"));

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
	void createCostumer(ActionEvent event) throws IOException {

		String name = costumerNameTxt.getText();
		String lastname = costumerLastnames.getText();
		String observations = costumerObservationsArea.getText();
		String address = costumerAddress.getText();
		
		long costumerPhoneLong = 0L;
		long costumerIdLong = 0L;

		if (!costumerPhone.getText().equals("")) {
			costumerPhoneLong = Long.parseLong(costumerPhone.getText());;
		} else if (!costumerId.getText().equals("")) {
			costumerIdLong = Long.parseLong(costumerId.getText());
		} else if (costumerPhone.getText().equals("")) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("Por favor ingrese el número de télefono del cliente.");
			alert.showAndWait();
		}

		if (!name.isEmpty() && !lastname.isEmpty() && !address.isEmpty() && costumerPhoneLong != 0L) {
			rm.addCostumer(name, lastname, address, observations, costumerPhoneLong, costumerIdLong);
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Hecho.");
			alert.setContentText("El cliente ha sido añadido exitosamente.");
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
	private TextArea orderObservations;

	@FXML
	void addMealToOrder(ActionEvent event) throws IOException {
		Meal meal = tvOrderFoodAvaible.getSelectionModel().getSelectedItem();
		rm.addMealToOrder(meal);

		ObservableList<Meal> tvOrder = FXCollections.observableArrayList(rm.getOrderFood());

		tvOrderFoodRequested.setItems(tvOrder);
		tcOrderFoodRequested.setCellValueFactory(new PropertyValueFactory<Meal, String>("name"));
		tcOrderFoodRequestedSize.setCellValueFactory(new PropertyValueFactory<Meal, String>("size"));
		tcOrderFoodRequestedPrice.setCellValueFactory(new PropertyValueFactory<Meal, Double>("price"));
	}

	@FXML
	void chooseCostumer(ActionEvent event) {
		int index = tvOrderCostumers.getSelectionModel().getSelectedIndex();
		orderCostumerInfo.setText(rm.getCostumersEnabled().get(index).getNameAndPhone());
	}

	@FXML
	void createOrder(ActionEvent event) throws IOException {

		ObservableList<Meal> orderFood = tvOrderFoodRequested.getItems();
		List<Meal> meals = orderFood;

		String costumerNameAndPhone = orderCostumerInfo.getText();
		Costumer costumer = rm.getCostumerObject(costumerNameAndPhone);

		String observations = orderObservations.getText();

		if (observations.equals("") || observations.equals(" ")) {
			observations = "Sin observaciones";
		}

		if (orderFood.size() != 0 && costumerNameAndPhone != "") {
			// rm.sellsByEmployee();
			// Serializar luego **
			initializeTableViewsOrderWindow();
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Hecho.");
			alert.setContentText("La orden ha sido añadido exitosamente.");
			alert.showAndWait();
			rm.addOrder(observations, costumer, currentEmployee, meals);
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("Por favor llene todos los campos.");
			alert.showAndWait();
		}
	}

	private void initializeTableViewsOrderWindow() {
		ObservableList<Costumer> tvCostumerObservableList = FXCollections.observableArrayList(rm.getCostumersEnabled());
		tvOrderCostumers.setItems(tvCostumerObservableList);
		tcOrderCostumers.setCellValueFactory(new PropertyValueFactory<Costumer, String>("name"));
		tcOrderCostumersPhone.setCellValueFactory(new PropertyValueFactory<Costumer, String>("phone"));

		ObservableList<Meal> tvMealObservableList = FXCollections.observableArrayList(rm.getMealsEnabled());
		tvOrderFoodAvaible.setItems(tvMealObservableList);
		tcOrderFoodAvaible.setCellValueFactory(new PropertyValueFactory<Meal, String>("name"));
		tcOrderFoodAvaibleSize.setCellValueFactory(new PropertyValueFactory<Meal, String>("size"));
		tcOrderFoodAvaiblePrice.setCellValueFactory(new PropertyValueFactory<Meal, Double>("price"));
		
		tcOrderFoodAvaible.setEditable(true);
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
	void createEmployee(ActionEvent event) throws IOException {
		String employeeName = createEmployeeName.getText();
		long employeeId = Long.parseLong(createEmployeeId.getText());
		String employeeLastname = createEmployeeLastname.getText();

		if (!employeeName.isEmpty() && !createEmployeeId.getText().isEmpty() && !employeeLastname.isEmpty()) {
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
	void createUser(ActionEvent event) throws IOException {
		String userName = userTxtUsername.getText();
		String userPass = userTxtPassword.getText();
		long userId = Long.parseLong(userTxtId.getText());
		String name = userTxtName.getText();
		String lastName = userTxtLastname.getText();

		if (!userName.isEmpty() && !userPass.isEmpty() && !name.isEmpty() && !lastName.isEmpty() && !userTxtId.getText().isEmpty()) {
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

		if (founded) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Realizado");
			alert.setContentText("El cliente ha sido eliminado exitosamente.");
			showManageCostumers();
			alert.showAndWait();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("El cliente no ha sido eliminado. Seleccione uno e intente de nuevo.");
			alert.showAndWait();
		}

	}

	@FXML
	void disableCostumer(ActionEvent event) throws IOException {
		int state = 2;

		int index = tvManageCostumers.getSelectionModel().getSelectedIndex();
		boolean founded = rm.changeStateCostumer(index, state);

		if (founded) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Realizado");
			alert.setContentText("El cliente ha sido deshabilitado exitosamente.");
			showManageCostumers();
			alert.showAndWait();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("El cliente no ha sido deshabilitado. Seleccione uno e intente de nuevo.");
			alert.showAndWait();
		}
	}

	@FXML
	void enableCostumer(ActionEvent event) throws IOException {
		int state = 1;

		int index = tvManageCostumers.getSelectionModel().getSelectedIndex();
		boolean founded = rm.changeStateCostumer(index, state);

		if (founded) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Realizado");
			alert.setContentText("El cliente ha sido deshabilitado exitosamente.");
			showManageCostumers();
			alert.showAndWait();
		} else {
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

		if (founded) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Realizado");
			alert.setContentText("El empleado ha sido eliminado exitosamente.");
			showManageEmployees();
			alert.showAndWait();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("El empleado no ha sido eliminado. Seleccione uno e intente de nuevo.");
			alert.showAndWait();
		}

	}

	@FXML
	void disableEmployee(ActionEvent event) throws IOException {
		int state = 2;

		int index = tvManageEmployees.getSelectionModel().getSelectedIndex();
		boolean founded = rm.changeStateEmployee(index, state);

		if (founded) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Realizado");
			alert.setContentText("El usuario ha sido deshabilitado exitosamente.");
			showManageEmployees();
			alert.showAndWait();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("El usuario no ha sido deshabilitado. Seleccione uno e intente de nuevo.");
			alert.showAndWait();
		}
	}

	@FXML
	void enableEmployee(ActionEvent event) throws IOException {
		int state = 1;

		int index = tvManageEmployees.getSelectionModel().getSelectedIndex();
		boolean founded = rm.changeStateEmployee(index, state);

		if (founded) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Realizado");
			alert.setContentText("El empleado ha sido deshabilitado exitosamente.");
			showManageEmployees();
			alert.showAndWait();
		} else {
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

	// Manage types windows code.

	@FXML
	private TableView<FoodType> tvManageType;

	@FXML
	private TableColumn<FoodType, String> tcManageTypeName;

	@FXML
	private TableColumn<FoodType, String> tcManageTypeEnabled;

	@FXML
	void deleteType(ActionEvent event) throws IOException {
		int index = tvManageType.getSelectionModel().getSelectedIndex();
		boolean founded = rm.deleteType(index);

		if (founded) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Realizado");
			alert.setContentText("El tipo de producto ha sido eliminado exitosamente.");
			showManageTypes();
			alert.showAndWait();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("El tipo de producto no ha sido eliminado. Seleccione uno e intente de nuevo.");
			alert.showAndWait();
		}
	}

	@FXML
	void disableType(ActionEvent event) throws IOException {
		int state = 2;

		int index = tvManageType.getSelectionModel().getSelectedIndex();
		boolean founded = rm.changeStateType(index, state);

		if (founded) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Realizado");
			alert.setContentText("El tipo de producto ha sido deshabilitado exitosamente.");
			showManageTypes();
			alert.showAndWait();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("El tipo de producto no ha sido deshabilitado. Seleccione uno e intente de nuevo.");
			alert.showAndWait();
		}
	}

	@FXML
	void enableType(ActionEvent event) throws IOException {
		int state = 1;

		int index = tvManageType.getSelectionModel().getSelectedIndex();
		boolean founded = rm.changeStateType(index, state);

		if (founded) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Realizado");
			alert.setContentText("El tipo de producto ha sido deshabilitado exitosamente.");
			showManageTypes();
			alert.showAndWait();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("El tipo de producto no ha sido deshabilitado. Seleccione uno e intente de nuevo.");
			alert.showAndWait();
		}
	}

	private void initializeManagerTypesWindow() {

		ObservableList<FoodType> tvTypeObservableList = FXCollections.observableArrayList(rm.getFoodTypes());
		tvManageType.setItems(tvTypeObservableList);
		tcManageTypeName.setCellValueFactory(new PropertyValueFactory<FoodType, String>("name"));
		tcManageTypeEnabled.setCellValueFactory(new PropertyValueFactory<FoodType, String>("state"));
	}

	// Manage size windows code.
	@FXML
	private TableView<Size> tvManageSize;

	@FXML
	private TableColumn<Size, String> tcManageSizeName;

	@FXML
	private TableColumn<Size, String> tcManageSizeEnabled;

	@FXML
	void deleteSize(ActionEvent event) throws IOException {
		int index = tvManageSize.getSelectionModel().getSelectedIndex();
		boolean founded = rm.deleteSize(index);

		if (founded) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Realizado");
			alert.setContentText("El tamaño ha sido eliminado exitosamente.");
			showManageSizes();
			alert.showAndWait();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("El tamaño no ha sido eliminado. Seleccione uno e intente de nuevo.");
			alert.showAndWait();
		}
	}

	@FXML
	void disableSize(ActionEvent event) throws IOException {
		int state = 2;

		int index = tvManageSize.getSelectionModel().getSelectedIndex();
		boolean founded = rm.changeStateSize(index, state);

		if (founded) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Realizado");
			alert.setContentText("El tamaño ha sido deshabilitado exitosamente.");
			showManageSizes();
			alert.showAndWait();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("El tamaño no ha sido deshabilitado. Seleccione uno e intente de nuevo.");
			alert.showAndWait();
		}
	}

	@FXML
	void enableSize(ActionEvent event) throws IOException {
		int state = 1;

		int index = tvManageSize.getSelectionModel().getSelectedIndex();
		boolean founded = rm.changeStateSize(index, state);

		if (founded) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Realizado");
			alert.setContentText("El tamaño ha sido deshabilitado exitosamente.");
			showManageSizes();
			alert.showAndWait();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("El tamaño no ha sido deshabilitado. Seleccione uno e intente de nuevo.");
			alert.showAndWait();
		}
	}

	private void initializeManagerSizeWindow() {

		ObservableList<Size> tvSizeObservableList = FXCollections.observableArrayList(rm.getSizes());
		tvManageSize.setItems(tvSizeObservableList);
		tcManageSizeName.setCellValueFactory(new PropertyValueFactory<Size, String>("name"));
		tcManageSizeEnabled.setCellValueFactory(new PropertyValueFactory<Size, String>("state"));
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

		if (founded) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Realizado");
			alert.setContentText("El ingrediente ha sido removido exitosamente.");
			showManageIngredients();
			alert.showAndWait();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("El ingrediente no ha sido removido. Seleccione uno e intente de nuevo.");
			alert.showAndWait();
		}
	}

	@FXML
	void disableIngredient(ActionEvent event) throws IOException {
		int state = 2;

		int index = tvManageIngredients.getSelectionModel().getSelectedIndex();
		boolean founded = rm.changeStateIngredient(index, state);

		if (founded) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Realizado");
			alert.setContentText("El ingrediente ha sido deshabilitado exitosamente.");
			showManageIngredients();
			alert.showAndWait();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("El ingrediente no ha sido deshabilitado. Seleccione uno e intente de nuevo.");
			alert.showAndWait();
		}
	}

	@FXML
	void enableIngredient(ActionEvent event) throws IOException {
		int state = 1;

		int index = tvManageIngredients.getSelectionModel().getSelectedIndex();
		boolean founded = rm.changeStateIngredient(index, state);

		if (founded) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Realizado");
			alert.setContentText("El ingrediente ha sido habilitado exitosamente.");
			showManageIngredients();
			alert.showAndWait();
		} else {
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

		if (founded) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Realizado");
			alert.setContentText("El usuario ha sido removido exitosamente.");
			showManageUsers();
			alert.showAndWait();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("El usuario no ha sido removido. Seleccione uno e intente de nuevo.");
			alert.showAndWait();
		}
	}

	@FXML
	void disableUser(ActionEvent event) throws IOException {
		int state = 2;

		int index = tvManageUsers.getSelectionModel().getSelectedIndex();
		boolean founded = rm.changeStateUsert(index, state);

		if (founded) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Realizado");
			alert.setContentText("El usuario ha sido deshabilitado exitosamente.");
			showManageUsers();
			alert.showAndWait();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("El usuario no ha sido deshabilitado. Seleccione uno e intente de nuevo.");
			alert.showAndWait();
		}
	}

	@FXML
	void enableUser(ActionEvent event) throws IOException {
		int state = 1;

		int index = tvManageUsers.getSelectionModel().getSelectedIndex();
		boolean founded = rm.changeStateUsert(index, state);

		if (founded) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Realizado");
			alert.setContentText("El usuario ha sido habilitado exitosamente.");
			showManageUsers();
			alert.showAndWait();
		} else {
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
		tcManageOrdersCostumer.setCellValueFactory(new PropertyValueFactory<Order, String>("owner"));
		tcManageOrdersEmployee.setCellValueFactory(new PropertyValueFactory<Order, String>("employeeInCharge"));
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

		if (founded) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Realizado");
			alert.setContentText("La orden ha sido removida exitosamente.");
			showManageOrders();
			alert.showAndWait();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("La orden no ha sido removida. Seleccione una e intente de nuevo.");
			alert.showAndWait();
		}
	}

	@FXML
	void disableOrder(ActionEvent event) throws IOException {
		int state = 2;

		int index = tvManageOrders.getSelectionModel().getSelectedIndex();
		boolean founded = rm.changeStateOrder(index, state);

		if (founded) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Realizado");
			alert.setContentText("La orden ha sido deshabilitada exitosamente.");
			showManageOrders();
			alert.showAndWait();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("La orden no ha sido deshabilitada. Seleccione uno e intente de nuevo.");
			alert.showAndWait();
		}
	}

	@FXML
	void enableOrder(ActionEvent event) throws IOException {
		int state = 1;

		int index = tvManageIngredients.getSelectionModel().getSelectedIndex();
		boolean founded = rm.changeStateOrder(index, state);

		if (founded) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Realizado");
			alert.setContentText("La orden ha sido habilitada exitosamente.");
			showManageOrders();
			alert.showAndWait();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("La orden no ha sido habilitada. Seleccione uno e intente de nuevo.");
			alert.showAndWait();
		}
	}

	@FXML
	void refreshStateInProcess(ActionEvent event) throws IOException {
		int index = tvManageOrders.getSelectionModel().getSelectedIndex();
		int state = 2;
		boolean made = rm.refreshStatus(index, state);

		if (made) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Estado actualizado");
			alert.setContentText("Estado actualizado a: en preparación");
			System.out.println(rm.getOrdersEnabled().get(index).getStatusNum());
			showManageOrders();
			alert.showAndWait();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("El estado solo puede avanzar. No es posible retroceder");
			alert.showAndWait();
		}
	}

	@FXML
	void refreshStateSend(ActionEvent event) throws IOException {
		int index = tvManageOrders.getSelectionModel().getSelectedIndex();
		int state = 3;
		boolean made = rm.refreshStatus(index, state);

		if (made) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Estado actualizado");
			alert.setContentText("Estado actualizado a: en entrega");
			System.out.println(rm.getOrdersEnabled().get(index).getStatusNum());
			showManageOrders();
			alert.showAndWait();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("El estado solo puede avanzar. No es posible retroceder");
			alert.showAndWait();
		}
	}

	@FXML
	void refreshStateDelivered(ActionEvent event) throws IOException {
		int index = tvManageOrders.getSelectionModel().getSelectedIndex();
		int state = 4;
		boolean made = rm.refreshStatus(index, state);

		if (made) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Estado actualizado");
			alert.setContentText("Estado actualizado a: entregado");
			System.out.println(rm.getOrdersEnabled().get(index).getStatusNum());
			showManageOrders();
			alert.showAndWait();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("El estado solo puede avanzar. No es posible retroceder");
			alert.showAndWait();
		}
	}

	@FXML
	void refreshStateCanceled(ActionEvent event) throws IOException {
		int index = tvManageOrders.getSelectionModel().getSelectedIndex();
		int state = 5;
		boolean made = rm.refreshStatus(index, state);

		if (made) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Estado actualizado");
			alert.setContentText("Estado actualizado a: en cancelado");
			System.out.println(rm.getOrdersEnabled().get(index).getStatusNum());
			showManageOrders();
			alert.showAndWait();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("El estado solo puede avanzar. No es posible retroceder");
			alert.showAndWait();
		}
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
		tcManageMealsAllergens.setCellValueFactory(new PropertyValueFactory<Meal, String>("causes"));
		tcManageMealsEnabled.setCellValueFactory(new PropertyValueFactory<Meal, String>("enabled"));
	}

	@FXML
	void deleteMeal(ActionEvent event) throws IOException {
		int index = tvManageMeals.getSelectionModel().getSelectedIndex();
		boolean founded = rm.deleteMeal(index);

		if (founded) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Realizado");
			alert.setContentText("El producto ha sido removido exitosamente.");
			showManageMeals();
			alert.showAndWait();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("El producto no ha sido removido. Seleccione uno e intente de nuevo.");
			alert.showAndWait();
		}
	}

	@FXML
	void disableMeal(ActionEvent event) throws IOException {
		int state = 2;

		int index = tvManageMeals.getSelectionModel().getSelectedIndex();
		boolean founded = rm.changeStateMeal(index, state);

		if (founded) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Realizado");
			alert.setContentText("Este plato ha sido deshabilitado exitosamente.");
			showManageMeals();
			alert.showAndWait();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("Este plato no ha sido deshabilitado. Seleccione uno e intente de nuevo.");
			alert.showAndWait();
		}
	}

	@FXML
	void enableMeal(ActionEvent event) throws IOException {
		int state = 1;

		int index = tvManageMeals.getSelectionModel().getSelectedIndex();
		boolean founded = rm.changeStateMeal(index, state);

		if (founded) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Realizado");
			alert.setContentText("Este plato ha sido deshabilitado exitosamente.");
			showManageMeals();
			alert.showAndWait();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("Este plato no ha sido deshabilitado. Seleccione uno e intente de nuevo.");
			alert.showAndWait();
		}
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

	public void showLoginWindow() throws IOException {
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

	private void showCreateTypeWindow() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CreateType.fxml"));
		fxmlLoader.setController(this);
		Parent addOrder = fxmlLoader.load();
		mainPane.getChildren().setAll(addOrder);
		initializeCreationType();
	}

	private void showCreateSizeWindow() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CreateSize.fxml"));
		fxmlLoader.setController(this);
		Parent addOrder = fxmlLoader.load();
		mainPane.getChildren().setAll(addOrder);
		initializeCreationSize();
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

	private void showManageSizes() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ManageSize.fxml"));
		fxmlLoader.setController(this);
		Parent manageOrders = fxmlLoader.load();
		mainPane.getChildren().setAll(manageOrders);
		initializeManagerSizeWindow();
	}

	private void showManageTypes() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ManageTypes.fxml"));
		fxmlLoader.setController(this);
		Parent manageOrders = fxmlLoader.load();
		mainPane.getChildren().setAll(manageOrders);
		initializeManagerTypesWindow();
	}
	
}
