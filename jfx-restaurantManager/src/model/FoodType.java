package model;

public class FoodType {
	String type, enabled;
	
	public FoodType(String type) {
		this.type = type;
	}
	
	public String getType(){
		return type;
	}
	
	public void setEnabled(String newState) {
		enabled = newState;
	}
	
	public String getEnabled() {
		return enabled;
	}
}
