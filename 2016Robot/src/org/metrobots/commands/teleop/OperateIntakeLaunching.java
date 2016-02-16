package org.metrobots.commands.teleop;

import org.metrobots.MetroXboxController;
import org.metrobots.Robot;

import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class OperateIntakeLaunching extends Command {
	public double startTime;
	public double actuateTime;

	public OperateIntakeLaunching() {
		requires((Subsystem) Robot.intakeLauncher);
		
	}
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		boolean a_button = Robot.secondary.getButton(MetroXboxController.BUTTON_A);
		boolean y_button = Robot.secondary.getButton(MetroXboxController.BUTTON_Y);
		boolean rb_button = Robot.secondary.getButton(MetroXboxController.RB);
		
		if(a_button){
			Robot.intakeLauncher.intake(0.6);
		}
		else if(y_button){
			Robot.intakeLauncher.intake(-1.0);
		}
		else{
			Robot.intakeLauncher.intake(0);
		}
		
		if(rb_button){
			if(startTime == 0){
				startTime = Utility.getFPGATime();
			}
				
			actuateTime = Utility.getFPGATime() - startTime;
		}
		else{
			startTime = 0;
			actuateTime = 0;
		}
		
		Robot.intakeLauncher.actuatePiston(actuateTime);
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