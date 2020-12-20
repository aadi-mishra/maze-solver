package de.tuhh.diss.lab.sheet4;

public interface Turner {
	
	public void setSpeed(int degreesPerSecond);
	public void turn(int degrees);

}

class Rotate implements Turner{
	
	public void turn(int degrees) {
		
	}

	public void setSpeed(int degreesPerSecond) {
		
	}
}