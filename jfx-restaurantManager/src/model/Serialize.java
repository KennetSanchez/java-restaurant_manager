package model;

import java.io.*;
/*import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
*/
public class Serialize {
	
	String fileName;
	 
    public void main(String[] args) {
    	fileName = null;
    }
    
    public void toSerialize(String xx) {
    	fileName="docs/Pedidos.csv";
    	FileOutputStream file;
		try {
			file = new FileOutputStream(fileName);
			ObjectOutputStream obOut = new ObjectOutputStream(file);
			obOut.writeObject(xx);
			obOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException i) {
			i.printStackTrace();
		}
    }
}
