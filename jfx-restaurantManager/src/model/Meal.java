package model;

import java.io.Serializable;

//import java.util.ArrayList;

public class Meal implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//Users
	User manager;
	User lastModifier;
	
	//Attributes
	String name, size;
	String type;
	String ingredientsTxt;
	String price;
	String sep=","; //separator
	ObjectState enabled;
	//ArrayList<Ingredient> ingredients;
	
	
	//This variables are for alert if the food may cause allergies 
	boolean allergen;
	String causes;
	
	//Dentro de RestaurantManager un array con los tipos principales para usarlos como "constantes".
	public Meal(String name, String size, String price, String type, String ingredients, ObjectState enabled) {
		this.name = name;
		this.size = size;
		this.price = price;
		this.type = type;
		this.ingredientsTxt = ingredients;
		this.enabled = enabled;
		
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
	
	public double getPrice() {
		return Double.parseDouble(price);
	}
	
	public String getType() {
		return type;
	}
	
	public String getIngredientsTxt(){
		return ingredientsTxt;
	}
	
	public String getCauses() {
		return causes;
	}
	
	public String toString() {
		return name +sep+ size +sep+ price +sep+ type +sep+ ingredientsTxt +sep+ causes;
	}
	
	public void setState(ObjectState newState) {
		enabled = newState;
	}
	
	public ObjectState getEnabled() {
		return enabled;
	}
	
}

