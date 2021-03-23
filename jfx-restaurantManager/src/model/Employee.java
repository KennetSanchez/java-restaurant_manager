package model;

public class Employee {

	String name, lastname, enabled;
	long id;
	String sep = ","; //separator
	
	public Employee(String name, String lastname, long id, String enabled) {
		this.name = name;
		this.lastname = lastname;
		this.id = id;
		this.enabled = enabled;
	}

	public String getName() {
		return name;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public long getId() {
		return id;
	}
	
	public String getEnabled() {
		return enabled;
	}
	
	public String toString() {
		return name +sep+ lastname +sep+ id;
	}
}
