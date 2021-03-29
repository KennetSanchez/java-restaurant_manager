package model;

import java.io.Serializable;

public class FoodType implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//Users
	User manager;
	User lastModifier;
	
	//Attributes
	String type, enabled;
	
	public FoodType(String type) {
		this.type = type;
	}
	
	public String getType(){
		return type;
	}
	
	public void setEnabled(String newState) {
		enabled = newState;
	}
	
	public String getEnabled() {
		return enabled;
	}
}
