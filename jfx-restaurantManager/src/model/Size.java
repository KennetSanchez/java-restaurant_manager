package model;

import java.io.Serializable;

public class Size implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//Users
	User manager;
	User lastModifier;
	
	//Attributes
	String name = "", enabled = "";
	
	public Size(String newSize) {
		name = newSize;
		enabled = "Sí";
	}
	
	public String getName() {
		return name;
	}
	
	public String getState() {
		return enabled;
	}
	
	public void setState(String newStatus) {
		enabled = newStatus;
	}
}
