package org.metrobots;

import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class AutoIntakeLaunch extends Command {
	
	public double speed, driveTime;
	public double startTime, passedTime;

	public AutoIntakeLaunch(double speed, double driveTime)
	{
		requires((Subsystem) Robot.intakeLauncher);
		this.speed = speed;
		this.driveTime = driveTime;
		
	}

	@Override
	protected void initialize() {
		Robot.intakeLauncher.intake(0);
		startTime = Utility.getFPGATime();
		passedTime = 0;
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		passedTime = Utility.getFPGATime() - startTime;
		Robot.intakeLauncher.intake (speed);
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		if((passedTime / 1000000) > driveTime)
		{
			return true;
		}
		else
			return false;
	}

	@Override
	protected void end() {
		Robot.intakeLauncher.intake(0);
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
