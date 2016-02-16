package org.metrobots.commands.teleop;

import org.metrobots.MetroXboxController;
import org.metrobots.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveSixMotor extends Command {

	public DriveSixMotor() {
		requires((Subsystem) Robot.chassis);
	
	}
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		double dly = -Robot.driver.getAxis(MetroXboxController.LEFT_Y);
		double dry = Robot.driver.getAxis(MetroXboxController.RIGHT_Y);
		Robot.chassis.sixMotorTankDrive(dly, dry);
		
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
