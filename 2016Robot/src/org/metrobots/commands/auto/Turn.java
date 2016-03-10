package org.metrobots.commands.auto;

import org.metrobots.Robot;

import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Turn extends Command {
	private float turnAngle;
	private double turnSpeed;
	private long startTime, passedTime;

	public Turn(float angle, double speed) {
		requires((Subsystem) Robot.chassis);
		this.turnAngle = angle;
		this.turnSpeed = speed;
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		Robot.chassis.tankDrive(0, 0);
		Robot.chassis.resetGyro();
		startTime = Utility.getFPGATime();
		passedTime = 0; 
	}

	@Override
	protected void execute() {
		float startAngle = Robot.comms.getOrientation(true)[2];
		if (turnAngle > 0) {
			Robot.chassis.tankDrive(-turnSpeed, turnSpeed);
		}
		else if (turnAngle < 0) {
			Robot.chassis.tankDrive(turnSpeed, -turnSpeed);
		}
		
		float currentAngle = Robot.comms.getOrientation(true)[2];
		while (Math.abs(currentAngle - startAngle) > Math.abs(turnAngle)) {
			currentAngle = Robot.comms.getOrientation(true)[2];
		}
		Robot.chassis.tankDrive(0, 0);
		
		passedTime = Utility.getFPGATime() - startTime;
			
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
