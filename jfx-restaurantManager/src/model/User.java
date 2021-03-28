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
		this.enabledU = enabledU;
	}
	
	public String getUsername() {
		return userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	@Override
	public String getEnabledE() {
		return super.getEnabledE();
	}
	
	public String getEnabledU() {
		return enabledU;
	}
	

	@Override
	public String getName() {
		return super.getName();
	}

	@Override
	public String getLastname() {
		return super.getLastname();
	}


	@Override
	public long getId() {
		return super.getId();
	}
	
	@Override
	public String toString() {
		return name +sep+ lastname +sep+ id +sep+ userName +sep+ password;
	}
	
	public void setState(String newState) {
		enabledU = newState;
	}
}
