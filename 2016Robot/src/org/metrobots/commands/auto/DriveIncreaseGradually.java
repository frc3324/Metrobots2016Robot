package org.metrobots.commands.auto;

public class DriveIncreaseGradually extends DriveStraightForward {
	public double rate;
	
	public DriveIncreaseGradually(double driveTime, double baseSpeed, double rate) {
		super(driveTime, baseSpeed);
		this.rate = rate;
	}
	
	public void execute() {
		super.execute();
		speed += rate;
	}
}
