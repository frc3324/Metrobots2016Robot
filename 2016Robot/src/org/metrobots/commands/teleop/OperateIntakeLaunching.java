package org.metrobots.commands.teleop; //use controller without red tape

import org.metrobots.MetroXboxController;
import org.metrobots.Robot;

import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class OperateIntakeLaunching extends Command {
	public long startTime;
	private int target;
	public boolean hasPressed;

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
		boolean lb_button = Robot.secondary.getButton(MetroXboxController.LB);
		//boolean rb_button = Robot.secondary.getButton(MetroXboxController.RB); //works automatically; needs fixed
		
		if(a_button){
			Robot.intakeLauncher.intake(-0.6); //was 0.6 //inward //negative is inward
		}
		else if(y_button){
			Robot.intakeLauncher.intake(1.0); //was  -1.0 //outward //positive is outward
		}
		else{
			Robot.intakeLauncher.intake(0);
		}
		
		/*if(rb_button){
			if(startTime == 0){
				startTime = Utility.getFPGATime();
			}
				
			actuateTime = Utility.getFPGATime() - startTime;
		}
		else{
			startTime = 0;
			actuateTime = 0;
		}*/
		
		//if (!(Robot.anglePot.getValue() > 640 && actuationSpeed < 0) && !(Robot.anglePot.getValue() < )) {
		
		//}
		//Robot.intakeLauncher.actuateAngle(speed); //speed or angle needs to be defined
		
		//Robot.intakeLauncher.actuatePiston(actuateTime);
		//double actuationSpeed = -(Robot.secondary.getAxis(MetroXboxController.LEFT_Y));
		//Robot.intakeLauncher.actuateSpeed(actuationSpeed);
		
		if (lb_button && !hasPressed) {
			hasPressed = true;
			startTime = Utility.getFPGATime();
		}
		useWindowMotor();
		
		int actuationState =(int) Robot.secondary.getDPadX();
		if (actuationState == -1)
			target = 1050;
		else if (actuationState == 1)
			target = 640;
		
		Robot.intakeLauncher.actuateAngle(target);
		
		/*double windowSpeed = -(Robot.secondary.getAxis(MetroXboxController.RIGHT_X));
		Robot.windowMotor.set(windowSpeed);
		System.out.print("X1 button");*/
		
		
	}

	public void useWindowMotor() {
		if (hasPressed) {
			long runningTime = Utility.getFPGATime() - startTime;
			if (runningTime < 500000) {
				Robot.windowMotor.set(-1);
			}
			else if (runningTime < 1000000) {
				Robot.windowMotor.set(1);	
			}
			else {
				Robot.windowMotor.set(0);
				hasPressed = false;
			}
		}
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
