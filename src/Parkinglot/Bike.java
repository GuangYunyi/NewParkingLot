package Parkinglot;

import java.io.*;

public class Bike extends Vehicle{

	public enum BikeTypes{
		motorbike,
		cycle;
	}

	protected BikeTypes bktype;

	
	public Bike(BikeTypes bktype, String license, boolean EorL) {
		this.bktype = bktype;
		this.license = license;
		this.EorL = EorL;
		type = Type.bike;
	}
	
	
	public void fileWritter() {
		
		try{PrintWriter fr = new PrintWriter(new FileWriter("./ParkingLot Information.txt",true));
			if(EorL) {
				fr.println("Lisence:" + license + " Types:" + bktype + " Status:E" + " Entering Time:" + time);
			}
			else {
				fr.println("Lisence:" + license + " Types:" + bktype + " Status:L" + " Leaving Time:" + time);
			}
		
			fr.close();
			}catch(Exception e) {
				System.out.println("cannot find file");
			}
	}
	
	
}

