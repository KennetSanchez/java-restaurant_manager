package model;

import java.util.ArrayList;
import java.util.Date;

public class Order {
	String code, status, observations;
	Costumer owner;
	Employee employeeInCharge;
	ArrayList<Meal> meals;
	Date date;
	
	//Code and date are not include. The program should generate the code, and take the date from the pc.
	public Order(String status, String observations, Costumer owner, Employee employeeInCharge, ArrayList<Meal> meals) {
		this.status = status;
		this.observations = observations;
		this.owner = owner;
		this.employeeInCharge = employeeInCharge;
		this.meals = meals;
	}
}
