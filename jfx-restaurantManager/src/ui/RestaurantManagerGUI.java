package ui;

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
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.time.LocalDateTime;

public class RestaurantManagerGUI {

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
	private TableView<?> tvMeal;

	@FXML
	private TableColumn<?, ?> tcMeal;

	@FXML
	private TableView<?> tvType;

	@FXML
	private TableColumn<?, ?> tcType;

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
	private TableView<?> tvIngredients;

	@FXML
	private TableColumn<?, ?> tcIngredients;

	@FXML
	void createMeal(ActionEvent event) {

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
