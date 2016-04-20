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
		int prev_state= 0;
		if (Robot.driver.getButton(MetroXboxController.BUTTON_A) == true){
			prev_state = 1;
		}
		if (Robot.driver.getButton(MetroXboxController.BUTTON_A) == false){
			prev_state = 0;
		}
		if (prev_state == 1){
			try {
				if (Robot.comms.getFiringStatus() == -1){
					Robot.chassis.sixMotorTankDrive(-0.5, -0.5);
					prev_state = 1;
				}
				else if (Robot.comms.getFiringStatus() == 1){
					Robot.chassis.sixMotorTankDrive(0.5, 0.5);
					prev_state = 1;
				}
				else if (Robot.comms.getFiringStatus() == 0){
					Robot.chassis.sixMotorTankDrive(0, 0);
					prev_state = 1;
				}
				else{
					//you can't see me
				}
			}
			catch (NullPointerException e) {
				e.printStackTrace();
			}
			//System.out.println("Hello, you are pressing ayyyyyyyyyy!");
		}
		else{
			boolean lb = Robot.driver.getButton(MetroXboxController.LB);
			boolean rb = Robot.driver.getButton(MetroXboxController.RB);
			double dly = Robot.driver.getAxis(MetroXboxController.LEFT_Y);
			double dry = Robot.driver.getAxis(MetroXboxController.RIGHT_Y);
			
			// Even the values if they are close
			if (Math.abs(dly-dry) < 0.1) {
				if (Math.abs(dly) > Math.abs(dry))
					dry = dly;
				if (Math.abs(dry) > Math.abs(dly))
					dly = dry;
				/*double mid = (dly+dry)/2;
				dly = mid;
				dry = mid;*/
			}
			
			// Halve the difference in turning
			if (Math.abs(dly) + Math.abs(dry) > 0.75) {
				dly = dly * 0.66;
				dry = dry * 0.66;
			}
			
			if (lb || rb) {
				dly = dly / 2;
				dry = dry / 2;
			}
			
			
			Robot.chassis.sixMotorTankDrive(dly, dry);
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
