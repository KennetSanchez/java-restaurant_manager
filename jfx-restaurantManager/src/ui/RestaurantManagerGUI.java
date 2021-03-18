package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import java.time.LocalDateTime;



public class RestaurantManagerGUI {

	//It's not sure that this keeps actualizated.
	public String timeAndDate() {
		LocalDateTime ldt = LocalDateTime.now();
		String msg = ldt + "";
		return msg;
	}
	
	//Mainpane items.

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
	
	//Ingredients code.
	
	@FXML
    private Pane ingredientsPane;
	
	
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

}
