 package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;

public class Order implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//Users
	User manager;
	User lastModifier;
	
	//Attributes
	String code, observations;
	OrderState status;
	Costumer owner;	
	Employee employeeInCharge;
	List<Meal> meals;
	String sep=","; //separator
	String date;
	ObjectState enabled;
	int statusNum;
	
	ArrayList<String> causes;
	String temporal;
	
	//Code and date are not include. The program should generate the code, and take the date from the pc.
	public Order(OrderState status, String observations, Costumer owner, Employee employeeInCharge, List<Meal> meals, ObjectState enabled) {
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
	
	public OrderState getStatus() {
		return status;
	}
	
	public String getObservations() {
		return observations;
	}
	
	public String getOwner() {
		return owner.getFullName();
	}
	
	public String getEmployeeInCharge() {
		return employeeInCharge.getFullName();
	}
	
	public List<Meal> getMeals() {
		return meals;
	}
	
	public String getDate() {
		return date.toString();
	}
	
	public ObjectState getEnabled() {
		return enabled;
	}
	
	public String toString() {
		return code +sep+ status +sep+ observations +sep+ owner +sep+ employeeInCharge +sep+ meals +sep+ date;
	}
	
	public void setStatus(OrderState newStatus) {
		status = newStatus;
	}
	
	public void setStatusNum(int newStatus) {
		statusNum = newStatus;
	}
	
	public void setState(ObjectState newState) {
		enabled = newState;
	}
}
