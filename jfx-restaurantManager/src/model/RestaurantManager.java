package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class RestaurantManager {
	
	public static BufferedReader br = null;
	
	public RestaurantManager() throws FileNotFoundException {
		br = new BufferedReader(new FileReader("input.txt.exampleeee"));
	}
}
