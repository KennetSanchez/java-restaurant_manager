package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class Order {
	String code, status, observations;
	Costumer owner;
	Employee employeeInCharge;
	ArrayList<Meal> meals;
	Date date;
	
	ArrayList<String> causes;
	String temporal;
	
	//Code and date are not include. The program should generate the code, and take the date from the pc.
	public Order(String status, String observations, Costumer owner, Employee employeeInCharge, ArrayList<Meal> meals) {
		this.status = status;
		this.observations = observations;
		this.owner = owner;
		this.employeeInCharge = employeeInCharge;
		this.meals = meals;
		code = generateCode();
	}
	
	public void allergenByFood() {
		for(int i=0; i<meals.size() ;i++) {
			temporal = meals.get(i).getCauses();
			causes.add(temporal);
		}
	}
	
	public String generateCode() {
		LocalDateTime ldt = LocalDateTime.now();
		String msg = owner.getName() + " - "+ ldt;
		return msg;
	}
	
	public String getCode() {
		return code;
	}
	
	public String getStatus() {
		return status;
	}
	
	public String getObservations() {
		return observations;
	}
	
	public Costumer getOwner() {
		return owner;
	}
	
	public Employee getEmployeeInCharge() {
		return employeeInCharge;
	}
	
	public ArrayList<Meal> getMeals() {
		return meals;
	}
	
	public Date getDate() {
		return date;
	}
}
