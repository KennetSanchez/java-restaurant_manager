package model;

import java.io.Serializable;

public class FoodType implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//Users
	User manager;
	User lastModifier;
	
	//Attributes
	String name;
	ObjectState enabled;
	
	public FoodType(String type, ObjectState enabled) {
		this.name = type;
		this.enabled = enabled;
	}
	
	public String getName(){
		return name;
	}
	
	public void setState(ObjectState newState) {
		enabled = newState;
	}
	
	public ObjectState getState() {
		return enabled;
	}
}
