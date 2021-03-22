package model;

public class Employee {

	String name, lastname;
	int id;
	String sep = ","; //separator
	
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
	
	public String toString() {
		return name +sep+ lastname +sep+ id;
	}
}
