package model;

import java.io.Serializable;

public class Size implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//Users
	User manager;
	User lastModifier;
	
	//Attributes
	String name = "";
	
	public Size(String newSize) {
		name = newSize;
	}
	
	public String getName() {
		return name;
	}
}
