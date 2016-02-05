package org.metrobots;

import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class AutoRotation extends Command {
	
	public double speed;
	public boolean clockwise;
	public double driveTime;
	
	public double startTime, passedTime;
	
	public AutoRotation(double speed, boolean clockwise,double driveTime)
	{
		requires((Subsystem) Robot.chassis);
		this.speed = speed;
		this.clockwise = clockwise;
		this.driveTime = driveTime;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		Robot.chassis.tankDrive(0, 0);
	}

	@Override
	protected void execute() {
		passedTime = Utility.getFPGATime() - startTime;
		Robot.chassis.tankDrive(speed, -speed);
		// TODO Auto-generated method stub
	}

	@Override
	protected void initialize() {
		Robot.chassis.tankDrive(0, 0);
		this.passedTime = 0;
		this.startTime = Utility.getFPGATime();
		Robot.chassis.resetGyro();
		
		if(clockwise)
			speed = -speed;
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
		// TODO Auto-generated method stub
		return false;
	}

}
	