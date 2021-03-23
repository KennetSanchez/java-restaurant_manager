package model;

public class Ingredient {

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
}
