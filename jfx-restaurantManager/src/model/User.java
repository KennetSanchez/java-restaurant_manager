package model;

import java.util.ArrayList;

public class User extends Employee{
	
	//User varaiables.
	String userName, password, enabledU;
	
	//Employee variables.
	String name, lastname, enabledE;
	long id;
	ArrayList<Meal> mealsOrdered = null;
	int ordersToday = 0;
	String sep = ","; //separator
	
	public User(String userName, String password, String name, String lastname, long id, String enabledE, String enabledU) {
		super(name, lastname, id, enabledE);
		this.userName = userName;
		this.password = password;
		this.enabled = enabledU;
	}
	
	public String getUsername() {
		return userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getEnabledE() {
		return enabledE;
	}
	
	public String getEnabledU() {
		return enabledU;
	}
	
	@Override
	public long getId() {
		return super.getId();
	}
	
	public String toString() {
		return name +sep+ lastname +sep+ id +sep+ userName +sep+ password;
	}
}
