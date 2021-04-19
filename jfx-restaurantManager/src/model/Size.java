package model;

import java.io.Serializable;

public class Size implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//Users
	User manager;
	User lastModifier;
	
	//Attributes
	String name = "";
	ObjectState enabled;
	
	public Size(String newSize, ObjectState enabled) {
		name = newSize;
		this.enabled = enabled;
	}
	
	public String getName() {
		return name;
	}
	
	public ObjectState getState() {
		return enabled;
	}
	
	public void setState(ObjectState newState) {
		enabled = newState;
	}
}
