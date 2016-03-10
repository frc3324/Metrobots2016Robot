package org.metrobots.commands.auto; //code needs to be checked

import org.metrobots.Robot;

import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Launch extends Command {
	public double speed, time;
	public double startTime, passedTime;
	
	public Launch(double speed, double time) {
		requires((Subsystem) Robot.intakeLauncher);
		this.speed = speed;
		this.time = time;
		
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		Robot.intakeLauncher.intake(0);
		startTime = Utility.getFPGATime();
		passedTime = 0;
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		passedTime = Utility.getFPGATime() - startTime;
		Robot.intakeLauncher.intake (speed);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		if((passedTime / 1000000) > time) {
			return true;
		}
		else
			return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		Robot.intakeLauncher.intake(0);
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
