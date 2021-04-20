package model;

import java.io.Serializable;
import java.util.ArrayList;


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
	ArrayList<Ingredient> ingredients;
	
	
	//This variables are for alert if the food may cause allergies 
	boolean allergen;
	String causes;
	
	//Constructor if the user write the ingredients instead of choosing them from the table.
	public Meal(String name, String size, String price, String type, String ingredients, ObjectState enabled) {
		this.name = name;
		this.size = size;
		this.price = price;
		this.type = type;
		this.ingredientsTxt = ingredients;
		this.enabled = enabled;
	}
	
	public Meal(String name, String size, String price, String type, ArrayList<Ingredient> ingredients, ObjectState enabled) {
		this.name = name;
		this.size = size;
		this.price = price;
		this.type = type;
		this.ingredients = ingredients;
		this.enabled = enabled;
	}
	
	private String checkCauses() {
		String msg = "";
		int founded = 0;
		
		for(int i = 0; i < ingredients.size() ; i++) {
			if(ingredients.get(i).getAllergenB() == true) {
				msg += ingredients.get(i).getName() + ", ";				
			}
		}
		
		if(founded == 0) {
			msg = "No tiene alérgenos.";
		}
		
		return msg;
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
		causes = checkCauses();
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

