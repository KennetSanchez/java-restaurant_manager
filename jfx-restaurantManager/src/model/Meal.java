package model;

import java.util.ArrayList;

public class Meal {
	String name, size, price;
	FoodType type;
	ArrayList<Ingredient> ingredients;
	
	
	//This variables are for alert if the food may cause allergies 
	boolean allergen;
	String causes;
	
	//Dentro de RestaurantManager un array con los tipos principales para usarlos como "constantes".
	public Meal(String name, String size, String price, FoodType type, ArrayList<Ingredient> ingredients) {
		this.name = name;
		this.size = size;
		this.price = price;
		this.type = type;
		this.ingredients = ingredients;
		
		//There will be a method to check if the food has an allergen ingredient and alert, or if isn't necessary.
	}
}
