package model;

import java.io.Serializable;

public class Ingredient implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//Users
	User manager;
	User lastModifier;
	
	//Attributes
	String name, enabled;
	String sep = ","; // separator

	// This is an extra. The 8 most common are: milk, eggs, tree nuts,
	// peanuts, shellfish, wheat, soy, fish.
	boolean allergen;

	public Ingredient(String name, boolean allergen, String enabled) {
		this.name = name;
		this.allergen = allergen;
		this.enabled = enabled;
	}

	public String getName() {
		return name;
	}

	// This returns allergen as a boolean
	public boolean getAllergenB() {
		return allergen;
	}

	// This returns allergen as a String.
	public String getAllergen() {
		return allergen + "";
	}
	
	public String getEnabled() {
		return enabled;
	}

	public String toString() {
		return name + sep + allergen;
	}
	
	public void setState(String newState) {
		enabled = newState;
	}
	
	public void setName(String newName) {
		name = newName;
	}
}
