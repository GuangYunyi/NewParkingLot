package Parkinglot;

public class ParkingSpot {

	// the parkingspot has three different types of size
	public enum spotsize{
		small,
		compact,
		large;
	}
	
	
	public boolean isFree() {
		return free;
	}

	public void setFree(boolean free) {
		this.free = free;
	}

	protected boolean free;	// free or occupied
	protected boolean normal;	// disable or normal
	protected boolean charging;	// can charge or not
	protected spotsize size;	// types of ParkingSpot
	protected String ID;	// car license
	protected int row;	// which row
	protected int num;	// location
	
	protected long enteringtime;	// time record
	protected long leavingtime;
	
	
	public ParkingSpot() {
		
		row = num / 50 ;
		
	}
	
	// the spot is occupied
	public void setSpot(Vehicle b) {
		free = false;
		ID = b.license;
		b.num = num;
		
		b.time = System.currentTimeMillis();	// decide the entering time
		enteringtime = b.time;
	}
	
	// the spot is free
	public void clearSpot(Vehicle b) {
		free = true;
		ID = null;
		
		b.time = System.currentTimeMillis();
		leavingtime = b.time;
	}
}
