package model;

import java.util.ArrayList;

public class Meal {
	String name, size, price;
	String type;
	String ingredientsTxt;
	String sep=","; //separator
	//ArrayList<Ingredient> ingredients;
	
	
	//This variables are for alert if the food may cause allergies 
	boolean allergen;
	String causes;
	
	//Dentro de RestaurantManager un array con los tipos principales para usarlos como "constantes".
	public Meal(String name, String size, String price, String type, String ingredients) {
		this.name = name;
		this.size = size;
		this.price = price;
		this.type = type;
		this.ingredientsTxt = ingredients;
		
		//We are going to work with it as a String for a while.
		//this.ingredients = ingredients;
		
		
		//There will be a method to check if the food has an allergen ingredient and alert, or if isn't necessary.
	}
	
	public String getName() {
		return name;
	}
	
	public String getSize() {
		return size;
	}
	
	public String getPrice() {
		return price;
	}
	
	public String getType() {
		return type;
	}
	
	public String getIngredients(){
		return ingredientsTxt;
	}
	
	public String getCauses() {
		return causes;
	}
	
	public String toString() {
		return name +sep+ size +sep+ price +sep+ type +sep+ ingredientsTxt +sep+ causes;
	}
}
