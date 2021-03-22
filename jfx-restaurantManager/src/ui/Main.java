package ui;
	
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	
	String restaurantName = "La casa dorada.";
	public BufferedReader br = null;
	public BufferedWriter bw = null;
	
	RestaurantManagerGUI restaurantManagerGUI;
	
	public Main() throws IOException {
		//br = new BufferedReader(new FileReader("input.txt.exampleeee"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
				
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
