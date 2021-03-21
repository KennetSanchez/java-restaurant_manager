package ui;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	
	String restaurantName = "La casa dorada.";
	
	RestaurantManagerGUI restaurantManagerGUI;
	
	public Main() {
		restaurantManagerGUI = new RestaurantManagerGUI();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainFx.fxml"));
		
		fxmlLoader.setController(restaurantManagerGUI);
		
		Parent root = fxmlLoader.load();
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Gestión de: " + restaurantName);
		primaryStage.show();	
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
