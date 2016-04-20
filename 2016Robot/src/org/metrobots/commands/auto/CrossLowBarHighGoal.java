package org.metrobots.commands.auto; //model code for other high goal autonomous codes

import org.metrobots.Robot;
import org.metrobots.commands.auto.IntakeLaunch;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossLowBarHighGoal extends CommandGroup {
	public CrossLowBarHighGoal() {
		this.addSequential(new DriveStraight(2, 0.25));
		//this.addSequential(new Turn(-115, 0.6)); //(degrees, speed) //turns the robot to line up with high goal
		//this.addSequential(new DriveStraightForward(2, 0.25));
		//this.addSequential(new Turn(65, 0.6)); //(degrees, speed)
		this.addSequential(new Turn(-90,0.6));
		this.addSequential(new DriveStraightForward(2,0.25));
		this.addSequential(new Turn(90,0.6));
		
		int prev_state = 0;
		if (prev_state == 1){
			try {
				if (Robot.comms.getXStatus() == -1){ //-1 = move //getXStatus() is moving on the x axis
					Robot.chassis.sixMotorTankDrive(-0.5, -0.5);
					prev_state = 1;
				}
				if (Robot.comms.getXStatus() == 1){
					Robot.chassis.sixMotorTankDrive(0.5, 0);
					prev_state = 1;
				}
				if (Robot.comms.getXStatus() == 0){
					Robot.chassis.sixMotorTankDrive(0, 0);
					prev_state = 1;
				}
			}
			catch(Exception e){
				//
			}
			try {
				if (Robot.comms.getFiringStatus() == -1){ //-1 = move //getFiringStatus() is moving on the y axis
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
			 
			catch(Exception e){
				//
			}
		this.addSequential(new IntakeLaunch(0.5,2));
		}
		
	}
}



