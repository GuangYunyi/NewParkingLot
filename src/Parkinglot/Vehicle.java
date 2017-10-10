package Parkinglot;

public class Vehicle {
	
	public enum Type{
		bike,
		car,
		bus;
	}

	
	public boolean isEorL() {
		return EorL;
	}
	public void setEorL(boolean eorL) {
		EorL = eorL;
	}
	
	
	
	protected String license;		//driving license
	protected int num=0;		// location number
	protected boolean EorL;	// entering or leaving
	protected long time;	// entering time and leaving time depends on EroL
	protected int money;	// how much it will pay
	protected Type type;	//decide which type
	
	public void setEorL() {
		EorL = true;	// entering
	}
	
	public void clearEorL() {
		EorL = false;	// leaving
	}
	
}
