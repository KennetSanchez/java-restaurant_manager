package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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

	public RestaurantManagerGUI() throws IOException{
		rm = new RestaurantManager();
	}

	// It's not sure that this keeps actualizated.
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

	// Menu code.

	@FXML
	void gestionateClients(ActionEvent event) throws IOException {
		showCostumerWindow();
	}

	@FXML
	void gestionateEmployees(ActionEvent event) {

	}

	@FXML
	void gestionateProducts(ActionEvent event) throws IOException {
		showMealWindow();
	}

	@FXML
	void gestionateUsers(ActionEvent event) {

	}

	@FXML
	void login(ActionEvent event) {

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
	private TableView<?> tvIngredientsCreated;

	@FXML
	private TableColumn<?, ?> tcIngredientsCreated;

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

		Meal newMeal = new Meal(name, size, price, type, ingredientsTxt);
		rm.addMeal(newMeal);
	}

	private void initializateAllTableViews() {
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
	private TableView<?> tvOrderCostumers;

	@FXML
	private TableColumn<?, ?> tcOrderCostumers;

	@FXML
	private TableView<?> tvOrderFoodAvaible;

	@FXML
	private TableColumn<?, ?> tcOrderFoodAvaible;

	@FXML
	private TableView<?> tvOrderFoodRequested;

	@FXML
	private TableColumn<?, ?> tcOrderFoodRequested;

	@FXML
	private TextArea orderCostumerInfo;

	@FXML
	void createOrder(ActionEvent event) {

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

    }
    
    //Create users code.
    
    @FXML
    private Pane createUserPane;

    @FXML
    private TextField userTxtName;

    @FXML
    private TextField userTxtId;

    @FXML
    private PasswordField userTxtPassword;

    @FXML
    void createUser(ActionEvent event) {

    }

	// Show windows code.

	public void showIngredientWindow() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Ingredients.fxml"));
		fxmlLoader.setController(this);
		Parent addIngredient = fxmlLoader.load();
		mainPane.getChildren().setAll(addIngredient);
	}

	public void showMealWindow() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CreateMeal.fxml"));
		fxmlLoader.setController(this);
		Parent addMeal = fxmlLoader.load();
		mainPane.getChildren().setAll(addMeal);
		initializateAllTableViews();
	}

	public void showCostumerWindow() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CreateCostumer.fxml"));
		fxmlLoader.setController(this);
		Parent addCostumer = fxmlLoader.load();
		mainPane.getChildren().setAll(addCostumer);
	}

	public void showMainWindow() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainFx.fxml"));
		fxmlLoader.setController(this);
		Parent addMain = fxmlLoader.load();
		mainPane.getChildren().setAll(addMain);
	}

	public void showOrderWindow() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CreateOrder.fxml"));
		fxmlLoader.setController(this);
		Parent addOrder = fxmlLoader.load();
		mainPane.getChildren().setAll(addOrder);
	}
}
