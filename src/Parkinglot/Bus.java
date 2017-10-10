package Parkinglot;

import java.io.*;



public class Bus extends Vehicle {

	public enum BusTypes{
		van,
		coach,
		minibus;
	}
	
	public Bus(BusTypes butype, String license, boolean EorL) {
		this.butype = butype;
		this.license = license;
		this.EorL = EorL;
		type = Type.bus;
	}
	
	private BusTypes butype;
	
	public void fileWritter() {
		
		try{PrintWriter fr = new PrintWriter(new FileWriter("./ParkingLot Information.txt",true));
			if(EorL) {
				fr.println("Lisence:" + license + " Types:" + butype + " Status:E" + " Entering Time:" + time);
			}
			else {
				fr.println("Lisence:" + license + " Types:" + butype + " Status:L" + " Leaving Time:" + time);
			}
			
			fr.close();
			}catch(Exception e) {
				System.out.println("cannot find file");
			}
	}
	
}
