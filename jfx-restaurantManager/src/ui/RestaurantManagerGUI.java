package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import java.time.LocalDateTime;

public class RestaurantManagerGUI {

	// It's not sure that this keeps actualizated.
	public String timeAndDate() {
		LocalDateTime ldt = LocalDateTime.now();
		String msg = ldt + "";
		return msg;
	}

	// Mainpane items.

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

	//Menu code.
	@FXML
	void gestionateClients(ActionEvent event) {

	}

	@FXML
	void gestionateEmployees(ActionEvent event) {

	}

	@FXML
	void gestionateProducts(ActionEvent event) {

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


	//Create meal code.
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

}
