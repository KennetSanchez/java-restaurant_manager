package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Employee implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//Users
	User manager;
	User lastModifier;
	
	//Attributes
	String name, lastname, enabled;
	long id;
	ArrayList<Meal> mealsOrdered;
	int ordersToday;
	String sep = ","; //separator
	
	public Employee(String name, String lastname, long id, String enabled) {
		this.name = name;
		this.lastname = lastname;
		this.id = id;
		this.enabled = enabled;
		mealsOrdered = null;
		ordersToday=0;
	}
	
	public void addMeals(List<Meal> meals) {
		if(!meals.isEmpty()) {
			mealsOrdered = (ArrayList<Meal>) meals;
		}
	}
	
	public void addOrder() {
		ordersToday +=1;
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
	
	public int getOrdersToday() {
		return ordersToday;
	}
	
	public ArrayList<Meal> getMeals(){
		return mealsOrdered;
	}
	
	public String getEnabledE() {
		return enabled;
	}
	
	public String toString() {
		return name +sep+ lastname +sep+ id;
	}
	
	public void setState(String newState) {
		enabled = newState;
	}

	public String getFullName() {
		return name + "   " + lastname;
	}
}
