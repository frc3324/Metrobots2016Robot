package org.metrobots.commands.auto; //this code needs to be checked!

import org.metrobots.Robot;

import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ExtendorRetractShooter extends Command {
	
	private double startTime, passedTime;
	private boolean direction; //false: retract; true: extend
	
	public ExtendorRetractShooter(boolean direction) {
		requires((Subsystem) Robot.intakeLauncher);
		this.direction = direction;		
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		Robot.intakeLauncher.actuateAngle(0);
		Robot.chassis.sixMotorTankDrive(0, 0);
		this.passedTime = 0;
		this.startTime = Utility.getFPGATime();
		Robot.chassis.resetGyro();
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		passedTime = Utility.getFPGATime() - startTime;
		Robot.intakeLauncher.actuateAngle(direction ? 50 : -50); //why 50? //(boolean ? true result: false result)
	}

	@Override
	protected boolean isFinished() {
		/*if(direction) {
			if (Robot.ac.get())
				return true;
		}
		else {
			if (Robot.actuationTopLimit.get())
				return true;
		}
		//TODO Auto-generated method stub*/
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
