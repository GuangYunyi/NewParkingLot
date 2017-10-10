/*
 * the parkinglot has 5 level
 * each level contains 20 rows
 * each row has 10:20:20 spots for small:compact:large
 * bikes can be parked in any type of spot
 * cars can be parked in compact and large spot
 * buses can be only be parked in large spot and each bus will occupy 4 consecutive spots
 * 
 */


package Parkinglot;

import java.util.*;


public class ParkingLot {

	protected int freeSpots = 1000;	// number of free spots
	
	
	
	ArrayList<ParkingSpot> SmallSpot = new ArrayList<>();
	ArrayList<ParkingSpot> CompactSpot = new ArrayList<>();
	ArrayList<ParkingSpot> LargeSpot = new ArrayList<>();


	// record the vehicle license and the corresponding location of the parking lot
	HashMap<String, ParkingSpot> bikeParkingMap = new HashMap<>();
	HashMap<String, ParkingSpot> carParkingMap = new HashMap<>();
	HashMap<String, ParkingSpot> busParkingMap = new HashMap<>();

	

	
	public void setArrayList(int n, ArrayList<ParkingSpot> sp, ParkingSpot.spotsize s) {
		int i = 0;

		
		for(i=0;i<n;i++) {
			ParkingSpot e = new ParkingSpot();
			sp.add(e);
		}
		
		for(i=0;i<n;i++) {
			sp.get(i).size = s;
			sp.get(i).free = true;
			sp.get(i).row = i/50;
			
			switch(s) {
			
			case small:
				sp.get(i).num = sp.get(i).row * 50 + i%10 ;	//set the num
				break;
				
			case compact:
				sp.get(i).num = sp.get(i).row * 50 + 10 + i%20 ;
				break;
				
			case large:
				sp.get(i).num = sp.get(i).row * 50 + 30 + i%20 ;
				break;
			}
			
		}
		
		for(i=0;i<20;i++) {
			sp.get(i).normal = false;	// the first 20 spots in one floor are for disabled people
		}
		
		for(i=n-20;i<n;i++) {
			sp.get(i).charging = true;	// the last 20 spots in one floor can charge battery
		}
	}
	
	
	// for Bike Types
	public void setBikeInformation(Bike v) {
		signal:
		{if(v.isEorL()) {
			for(int i=0;i<200;i++) {
				
				if(SmallSpot.get(i).isFree()) {
					//set the status
					SmallSpot.get(i).setSpot(v);	//change the status
					bikeParkingMap.put(v.license, SmallSpot.get(i));
					freeSpots--;
					
					System.out.println("License:" + v.license + "Entering" + " Row:" + SmallSpot.get(i).row + " Number:" + SmallSpot.get(i).num);
					System.out.println("FreeSpotLeft:" + freeSpots);
					v.fileWritter();	//record

					break signal;
				}
				
				
			}
			
			for(int i=0;i<400;i++) {
				
				if(CompactSpot.get(i).isFree()) {
					
					CompactSpot.get(i).setSpot(v);
					bikeParkingMap.put(v.license, CompactSpot.get(i));
					freeSpots--;
					
					System.out.println("License:" + v.license + "Entering" + " Row:" + CompactSpot.get(i).row + " Number:" + CompactSpot.get(i).num);
					System.out.println("FreeSpotLeft:" + freeSpots);
					
					v.fileWritter();
					
					break signal;
					
				}
			}
			
			
			for(int i=0;i<400;i++) {
				
				if(LargeSpot.get(i).isFree()) {
					
					LargeSpot.get(i).setSpot(v);
					bikeParkingMap.put(v.license, LargeSpot.get(i));
					freeSpots--;
					
					System.out.println("License:" + v.license + "Entering" + " Row:" + LargeSpot.get(i).row + " Number:" + LargeSpot.get(i).num);
					System.out.println("FreeSpotLeft:" + freeSpots);
					
					v.fileWritter();
					
					break signal;
					
				}
			}
			
			System.out.println("no parking area" );
		}
		
		
	
		 else {
			
			ParkingSpot b = new ParkingSpot();
			b =	bikeParkingMap.get(v.license);
			
			System.out.println("License:" + v.license + "Leaving" + " Row:" + b.row + " Number:" + b.num);
			b.clearSpot(v);
			
			
			bikeParkingMap.put(v.license,b);
			freeSpots++;
			
			
			System.out.println("FreeSpotLeft:" + freeSpots);
			
			v.fileWritter();
		 }
		
		}
	}
	
	
	
	// for car types
	public void setCarInformation(Car v) {
		signal:
		{if(v.isEorL()) {
			
			for(int i=0;i<400;i++) {
				
				if(CompactSpot.get(i).isFree()) {
					
					CompactSpot.get(i).setSpot(v);
					carParkingMap.put(v.license, CompactSpot.get(i));
					freeSpots--;
					
					System.out.println("License:" + v.license + "Entering" + " Row:" + CompactSpot.get(i).row + " Number:" + CompactSpot.get(i).num);
					System.out.println("FreeSpotLeft:" + freeSpots);
					
					v.fileWritter();
					
					break signal;
					
				}
			}
			
			
			for(int i=0;i<400;i++) {
				
				if(LargeSpot.get(i).isFree()) {
					
					LargeSpot.get(i).setSpot(v);
					carParkingMap.put(v.license, LargeSpot.get(i));
					freeSpots--;
					
					System.out.println("License:" + v.license + "Entering" + " Row:" + LargeSpot.get(i).row + " Number:" + LargeSpot.get(i).num);
					System.out.println("FreeSpotLeft:" + freeSpots);
					
					v.fileWritter();
					
					break signal;
					
				}
			}
			
			System.out.println("no parking area" );
		}
		
		
	
		 else {
			
			ParkingSpot b = carParkingMap.get(v.license);
			
			System.out.println("License:" + v.license + "Leaving" + " Row:" + b.row + " Number:" + b.num);
			
			b.clearSpot(v);
			carParkingMap.put(v.license,b);
			
			freeSpots++;
			System.out.println("FreeSpotLeft:" + freeSpots);
			
			v.fileWritter();
		 }
		
		}
		
	}
	
	
	
	public boolean findLargeSpot(Bus v) {
		
		for(int i=0;i<397;i++) {	// count row
			if(LargeSpot.get(i).row == LargeSpot.get(i+3).row) {	// in the same row
				// if there are 4 consecutive free spots and all in the same row
				if(LargeSpot.get(i).free && LargeSpot.get(i+1).free && LargeSpot.get(i+2).free && LargeSpot.get(i+3).free) {
					
					// set all the 4 spots into occupied
					LargeSpot.get(i).setSpot(v);
					LargeSpot.get(i+1).setSpot(v);
					LargeSpot.get(i+2).setSpot(v);
					LargeSpot.get(i+3).setSpot(v);
					
					busParkingMap.put(v.license, LargeSpot.get(i));
					freeSpots = freeSpots - 4;
					
					System.out.println("License:" + v.license + "Entering" + " Row:" + LargeSpot.get(i).row + " Number:" + LargeSpot.get(i).num);
					System.out.println("FreeSpotLeft:" + freeSpots);
					
					v.fileWritter();
					return true;
				}
			}
		}
		
		return false;
	}
	
	
	// for bus types
	public void setBusInformation(Bus v) {
		signal:
		{if(v.isEorL()) {
			
			if(findLargeSpot(v)) {
				break signal;
			}
			
			else {
				System.out.println("no parking area");
			}
		}
		
	
		 else {
			
			ParkingSpot b = busParkingMap.get(v.license);
			
			// get the location of these 4 largespots in ArrayList
			int i = 20*b.row + (b.num%50 - 30);
			
			System.out.println("License:" + v.license + "Leaving" + " Row:" + b.row + " Number:" +b.num);

			// clear 4 consecutive spots
			b.clearSpot(v);
			LargeSpot.get(i+1).clearSpot(v);
			LargeSpot.get(i+2).clearSpot(v);
			LargeSpot.get(i+3).clearSpot(v);
			
			busParkingMap.put(v.license,b);
			
			freeSpots = freeSpots + 4;
			System.out.println("FreeSpotLeft:" + freeSpots);

			v.fileWritter();
		 }
		
		}
	}
	
	
	
	public static void main(String[] args) {
		
		// initialize
		ParkingLot P = new ParkingLot();
	
		P.setArrayList(200,P.SmallSpot,ParkingSpot.spotsize.small);
		P.setArrayList(400,P.CompactSpot, ParkingSpot.spotsize.compact);
		P.setArrayList(400,P.LargeSpot, ParkingSpot.spotsize.large);

		Vehicle a = new Vehicle();

		// Input
		Scanner s = new Scanner(System.in);
		while(true) {
		System.out.println("Types:");
		String store = s.next();
		System.out.println("License:");
		a.license = s.next();
		System.out.println("Entering?");
		a.EorL = s.nextBoolean();
		
		// transfer String into Vehicle.Types
		switch(store) {
		case "motorbike":
			Bike b1 = new Bike(Bike.BikeTypes.motorbike, a.license, a.EorL);
			P.setBikeInformation(b1);
			break;
		case "cycle":
			Bike b2 = new Bike(Bike.BikeTypes.cycle, a.license, a.EorL);
			P.setBikeInformation(b2);
			break;
		case "sedan":
			Car c1 = new Car(Car.CarTypes.sedan, a.license, a.EorL);
			P.setCarInformation(c1);
			break;
		case "coupe":
			Car c2 = new Car(Car.CarTypes.coupe, a.license, a.EorL);
			P.setCarInformation(c2);
			break;
		case "suv":
			Car c3 = new Car(Car.CarTypes.suv, a.license, a.EorL);
			P.setCarInformation(c3);
			break;
		case "van":
			Bus bu1 = new Bus(Bus.BusTypes.van, a.license, a.EorL);
			P.setBusInformation(bu1);
			break;
		case "coach":
			Bus bu2 = new Bus(Bus.BusTypes.coach, a.license, a.EorL);
			P.setBusInformation(bu2);
			break;
		case "minibus":
			Bus bu3 = new Bus(Bus.BusTypes.minibus, a.license, a.EorL);
			P.setBusInformation(bu3);
			break;
		}
		}
		
		
	}
	
	// input the License, Types and Entering or not (true or false)
	// then the screen will show the vehicle information and how many free space left
	// the file records more information which contains License, Types, E/L and time
	// the input ends with "Ctrl+z"
}
