package model;

public class Employee {

	String name, lastname;
	int id;
	
	public Employee(String name, String lastname, int id) {
		this.name = name;
		this.lastname = lastname;
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public int getId() {
		return id;
	}
}
