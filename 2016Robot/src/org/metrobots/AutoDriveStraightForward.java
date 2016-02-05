package org.metrobots;

import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class AutoDriveStraightForward extends Command {
	
	public double driveTime, speed;
	public double startTime, passedTime;
		

	public AutoDriveStraightForward(double driveTime, double speed) 
	{
		requires((Subsystem) Robot.chassis);
		this.driveTime = driveTime;
		this.speed = speed;
	}
	
	@Override
	protected void end() {
		Robot.chassis.tankDrive(0, 0);
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		passedTime = Utility.getFPGATime() - startTime;
		Robot.chassis.tankDrive (speed, speed);
	}

	@Override
	protected void initialize() {
		Robot.chassis.tankDrive(0, 0);
		Robot.chassis.resetGyro();
		startTime = Utility.getFPGATime();
		passedTime = 0;
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isFinished() {
		if((passedTime / 1000000) > driveTime)
		{
			return true;
		}
		else
			return false;
		// TODO Auto-generated method stub
		
	
	}

}
