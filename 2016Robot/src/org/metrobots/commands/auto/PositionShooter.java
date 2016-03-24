package org.metrobots.commands.auto;

import org.metrobots.Robot;

import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.command.Command;

public class PositionShooter extends Command {
	private double startTime, passedTime = 0;
	private boolean pos;
	
	public PositionShooter(boolean pos) {
		this.pos = pos;
	}
	
	@Override
	protected void initialize() {
		Robot.intakeLauncher.actuateSpeed(0);
		Robot.chassis.resetGyro();
		startTime = Utility.getFPGATime();
	}

	@Override
	protected void execute() {
		Robot.intakeLauncher.actuateSpeed(pos ? -.75 : .75); //(pos ? true : false)
	}

	@Override
	protected boolean isFinished() {
		passedTime = Utility.getFPGATime() - startTime;
		if (passedTime > 500000)
			return true;
		else
			return false;
	}

	@Override
	protected void end() {
		Robot.intakeLauncher.actuateSpeed(0);
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
