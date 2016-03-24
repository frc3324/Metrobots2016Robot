package org.metrobots.commands.auto;

import org.metrobots.Robot;

import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class UpDownShooter extends Command {
	public double startTime, passedTime;
	public double actuateSpeed;
	private boolean pos;
	
public UpDownShooter(double actuateSpeed, double passedTime) {
	//requires((Subsystem) Robot.);
}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		//Robot.actuationMotor.;
		startTime = Utility.getFPGATime();
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub 
		Robot.intakeLauncher.actuateSpeed(pos ? .5 : -.5);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		passedTime = Utility.getFPGATime() - startTime;
		
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
