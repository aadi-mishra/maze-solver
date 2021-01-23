package de.tuhh.diss.lab.sheet5;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;

/* This class implements methods for all the movements of the motors as follows 
 * - Forward
 * - Backward
 * - Turn left and right 
 * - Motor Stop
 * 
 */

public class Motors {
	
	private EV3LargeRegulatedMotor rightMotor = new EV3LargeRegulatedMotor(MotorPort.C);
	private EV3LargeRegulatedMotor leftMotor = new EV3LargeRegulatedMotor(MotorPort.B);
	private Gyro gyroObject = new Gyro();
	private UsSensor usObject = new UsSensor();
	
	private int motorSpeed = 0;
	private final int K_P = 20;
	private final int K_F = 20;
	private final int K_S = 35;
	private float currentDistance = 0;
	public  float lastAngle = 0;
	
	public void setSpeed(int degreesPerSecond) {
		// Assign input parameter to the class variable motorSpeed
		this.motorSpeed = degreesPerSecond;
		// Assign speed values to the motors
		rightMotor.setSpeed(motorSpeed);
		leftMotor.setSpeed(motorSpeed);
	}

	public void turn(float degrees) {
		
		lastAngle = lastAngle + degrees;
		float currentAngle;
		
		do {

            currentAngle = gyroObject.getAngle();
			float error = lastAngle - currentAngle;
			this.motorSpeed =  (int) ( K_P * Math.abs(error));
			
			setSpeed(motorSpeed);
			// Turn robot CCW
			if (error > 0) {
				rightMotor.backward();
				leftMotor.forward();
			// Turn robot CW	
			}else if(error < 0) {
				rightMotor.forward();
				leftMotor.backward();
			// Stop the robot if setpoint reached
			}else {
				rightMotor.stop();
				leftMotor.stop();
				break;
			}
			
		}while(true);
	}
	
	public void moveForward() {
		float initialDistance = 0;
		float difference  = 0;
		do {
			currentDistance = usObject.getDistance();
			difference = (initialDistance-currentDistance);
			int speed = (int) (difference*K_F);
			setSpeed(speed);
			leftMotor.backward();
			rightMotor.backward();

		}while(difference<=-14);
	}
	
	public void moveForwardX() {
		int angle = angleRotation(-35); // move 35cm per step
		setSpeed(700);
		rightMotor.rotate(angle, true);
		leftMotor.rotate(angle, true);
		
		Delay.msDelay(7000);
	}
	public void moveForwardSlow() {
		float initialDistance = 0;
		float difference  = 0;
		do {
			currentDistance = usObject.getDistance();
			difference = (initialDistance-currentDistance);
			int speed = (int) (difference*K_S);
			setSpeed(speed);
			leftMotor.backward();
			rightMotor.backward();

		}
		while(difference<=-4);
	}

	public void moveBackward() {
		float initialDistance = 0;
		float difference  = 0;		
		do {
			currentDistance = usObject.getDistance();
			difference = (initialDistance-currentDistance);
			int speed = (int) (difference*K_F);
			setSpeed(speed);
			leftMotor.forward();
			rightMotor.forward();				

		}while(difference>-14);
	}
	
	public void motorStop() {
		setSpeed(0);
	}
		
	public void eKill() {
		leftMotor.stop();
		rightMotor.stop();
		
	}
	
	public int angleRotation(double distance) {
		
		final int MOTOR_GEAR_COG = 8;  // number of motor teeth 
		final int WHEEL_GEAR_COG = 24; // number of wheel teeth 
		final double WHEEL_DIAMETER = 5.4; // Robot's wheel diameter is D = 5.4 cm.
		final double GEAR_RATIO = WHEEL_GEAR_COG/MOTOR_GEAR_COG; 
		final double PERIMETER = Math.PI * WHEEL_DIAMETER;  // C = 2*pi*r = pi*D
		double wheelRotation = distance/PERIMETER;
		double motorRotation = GEAR_RATIO * wheelRotation;
		int angle = (int) (motorRotation * (360)); 
		
		return angle;
		
	}
}
