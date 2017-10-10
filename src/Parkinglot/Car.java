package Parkinglot;

import java.io.*;


public class Car extends Vehicle{

	public enum CarTypes{
		sedan,
		suv,
		coupe;
	}
	
	public Car(CarTypes ctype, String license, boolean EorL) {
		this.ctype = ctype;
		this.license = license;
		this.EorL = EorL;
		type = Type.car;
	}
	
	private CarTypes ctype;
	
	public void fileWritter() {
		
		try{PrintWriter fr = new PrintWriter(new FileWriter("./ParkingLot Information.txt",true));
			if(EorL) {
				fr.println("Lisence:" + license + " Types:" + ctype + " Status:E" + " Entering Time:" + time);
			}
			else {
				fr.println("Lisence:" + license + " Types:" + ctype + " Status:L" + " Leaving Time:" + time);
			}
			
			fr.close();
			}catch(Exception e) {
				System.out.println("cannot find file");
			}
	}
	
}
