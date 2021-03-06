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
	String name, lastname;
	ObjectState enabled;
	long id;
	ArrayList<Meal> mealsOrdered;
	int ordersToday;
	String sep = ","; //separator
	
	public Employee(String name, String lastname, long id, ObjectState enabled) {
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
	
	public ObjectState getEnabledE() {
		return enabled;
	}
	
	public long getSerial() {
		return serialVersionUID;
	}
	public String toString() {
		return name +sep+ lastname +sep+ id;
	}
	
	public void setState(ObjectState newState) {
		enabled = newState;
	}

	public String getFullName() {
		return name + "   " + lastname;
	}
}
