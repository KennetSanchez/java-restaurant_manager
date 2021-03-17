package model;

public class User extends Employee{
	
	//User varaiables.
	String userName, password;
	
	//Employee variables.
	String name, lastname;
	int id;
	
	public User(String userName, String password, String name, String lastname, int id) {
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
}
