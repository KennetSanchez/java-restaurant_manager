 package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class Order {
	String code, status, observations;
	Costumer owner;
	Employee employeeInCharge;
	ArrayList<Meal> meals;
	String sep=","; //separator
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
		addMealsToEmp();
	}
	
	//This constructor it's only for testing.
	public Order(String status, String observations) {
		this.status = status;
		this.observations = observations;
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
	
	public void addMealsToEmp() {
		employeeInCharge.addMeals(meals);
		employeeInCharge.addOrder();
	}
	
	//Getters
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
	
	public String toString() {
		return code +sep+ status +sep+ observations +sep+ owner +sep+ employeeInCharge +sep+ meals +sep+ date;
	}
	
}
