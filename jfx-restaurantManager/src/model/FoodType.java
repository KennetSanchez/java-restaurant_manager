package model;

import java.io.Serializable;

public class FoodType implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//Users
	User manager;
	User lastModifier;
	
	//Attributes
	String name, enabled;
	
	public FoodType(String type) {
		this.name = type;
		enabled = "Sí";
	}
	
	public String getName(){
		return name;
	}
	
	public void setState(String newState) {
		enabled = newState;
	}
	
	public String getState() {
		return enabled;
	}
}
