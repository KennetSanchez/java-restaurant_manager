package model;

public class User extends Employee{
	
	//User varaiables.
	String userName, password;
	
	//Employee variables.
	String name, lastname;
	long id;
	String sep = ","; //separator
	
	public User(String userName, String password, String name, String lastname, long id) {
		super(name, lastname, id);
		this.userName = userName;
		this.password = password;
	}
	
	public String getUsername() {
		return userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String toString() {
		return name +sep+ lastname +sep+ id +sep+ userName +sep+ password;
	}
}
