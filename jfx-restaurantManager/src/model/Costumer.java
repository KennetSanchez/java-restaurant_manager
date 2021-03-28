package model;

public class Costumer {

	String name, lastname, address, observations, enabled;
	long phone;
	String sep=","; //separator
	
	//Optional.
	long id;
	
	//Constructor whitout id.
	public Costumer(String name, String lastname, String address, String observations, long phone, String enabled) {
		this.name = name;
		this.lastname = lastname;
		this.address = address;
		this.observations = observations;
		this.phone = phone;
		this.enabled = enabled;
	}
	
	//Constructor with id.
	public Costumer(String name, String lastname, String address, String observations, long phone, String enabled, long id) {
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
	
	public String toString() {
		return name +sep+ lastname +sep+ address +sep+ observations +sep+ phone;
	}
	
	public void setEnabled(String newValue) {
		enabled = newValue;
	}

	public String getLastname() {
		return lastname;
	}

	public String getAddress() {
		return address;
	}

	public String getObservations() {
		return observations;
	}

	public String getEnabled() {
		return enabled;
	}

	public long getPhone() {
		return phone;
	}

	public String getSep() {
		return sep;
	}

	public long getId() {
		return id;
	}
	
	public void setState(String newState) {
		enabled = newState;
	}
}
