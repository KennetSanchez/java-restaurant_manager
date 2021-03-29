 package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;

public class Order {
	String code, status, observations;
	Costumer owner;	
	Employee employeeInCharge;
	List<Meal> meals;
	String sep=","; //separator
	String date;
	String enabled;
	int statusNum;
	
	ArrayList<String> causes;
	String temporal;
	
	//Code and date are not include. The program should generate the code, and take the date from the pc.
	public Order(String status, String observations, Costumer owner, Employee employeeInCharge, List<Meal> meals, String enabled) {
		this.status = status;
		this.observations = observations;
		this.owner = owner;
		this.employeeInCharge = employeeInCharge;
		this.meals = meals;
		code = generateCode();
		date = takeDate();
		this.enabled = enabled;
		statusNum = 1;
		//addMealsToEmp();		
	}
	
	//This constructor it's only for testing.
	public Order(String status, String observations, String enabled) {
		this.status = status;
		this.observations = observations;
		this.enabled = enabled;
	}
	
	public String takeDate() {
		FormatStyle timeStyle = FormatStyle.MEDIUM;		
		DateTimeFormatter formatterH = DateTimeFormatter.ofLocalizedDate(timeStyle);
		String newDate = LocalDate.now().format(formatterH);
		
		return newDate;
	}
	
	public int getStatusNum() {
		return statusNum;
	}
	
	public String getMealsTxt() {
		String msg = "";
		
		for(int i = 0; i < meals.size() ; i++) {
			msg+= meals.get(i).getName();
		}
		
		return msg;
	}
	
	public void allergenByFood() {
		for(int i=0; i < meals.size() ;i++) {
			temporal = meals.get(i).getCauses();
			causes.add(temporal);
		}
	}
	
	public String generateCode() {
		String msg = "";
		LocalDateTime ldt = LocalDateTime.now();
		try {
			msg = owner.getName() + " - "+ ldt;
		} catch (Exception e) {
			msg = "Error con el código.";
		}		
		
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
	
	public String getOwner() {
		return null;
	}
	
	public String getEmployeeInCharge() {
		return null;
	}
	
	public List<Meal> getMeals() {
		return meals;
	}
	
	public String getDate() {
		return date.toString();
	}
	
	public String getEnabled() {
		return enabled;
	}
	
	public String toString() {
		return code +sep+ status +sep+ observations +sep+ owner +sep+ employeeInCharge +sep+ meals +sep+ date;
	}
	
	public void setStatus(String newStatus) {
		status = newStatus;
	}
	
	public void setStatusNum(int newStatus) {
		statusNum = newStatus;
	}
	
	public void setState(String newState) {
		enabled = newState;
	}
}
