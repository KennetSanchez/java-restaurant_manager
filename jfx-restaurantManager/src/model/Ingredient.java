package model;

public class Ingredient {

	String name;
	String sep=","; //separator
	
	//This is an extra. The 8 most common are: milk, eggs, tree nuts, 
	//peanuts, shellfish, wheat, soy, fish.
	boolean allergen;
	
	public Ingredient(String name, boolean allergen) {
		this.name = name;
		this.allergen = allergen;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean getAllergen() {
		return allergen;
	}
	
	public String toString() {
		return name +sep+ allergen;
	}
}
