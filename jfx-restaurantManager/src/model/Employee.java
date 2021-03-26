package model;

import java.util.ArrayList;

public class Employee {

	String name, lastname, enabled;
	long id;
	ArrayList<Meal> mealsOrdered = null;
	int ordersToday=0;
	String sep = ","; //separator
	
	public Employee(String name, String lastname, long id, String enabled) {
		this.name = name;
		this.lastname = lastname;
		this.id = id;
		this.enabled = enabled;
		mealsOrdered=null;
	}
	
	public void addMeals(ArrayList<Meal> meals) {
		if(!meals.isEmpty()) {
			mealsOrdered = meals;
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
	
	public String getEnabled() {
		return enabled;
	}
	
	public String toString() {
		return name +sep+ lastname +sep+ id;
	}
}
