package org.metrobots.commands.auto;

import org.metrobots.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class CrossDefenseSetUpPortcullis2 extends Command {
	public CrossDefenseSetUpPortcullis2() {
		requires((Subsystem) Robot.chassis);
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		Robot.chassis.tankDrive(0, 0);
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		
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
