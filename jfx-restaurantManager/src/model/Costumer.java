package model;

public class Costumer {

	String name, lastname, address, observations;
	long phone;
	
	//Optional.
	int id;
	
	//Constructor whitout id.
	public Costumer(String name, String lastname, String address, String observations, long phone) {
		this.name = name;
		this.lastname = lastname;
		this.address = address;
		this.observations = observations;
		this.phone = phone;
	}
	
	//Constructor with id.
	public Costumer(String name, String lastname, String address, String observations, long phone, int id) {
		this.name = name;
		this.lastname = lastname;
		this.address = address;
		this.observations = observations;
		this.phone = phone;
		this.id = id;
	}

	public String getName() {
		return name;
	}
}
