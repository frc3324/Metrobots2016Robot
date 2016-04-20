package org.metrobots.commands.auto; //values need to be checked //remember that a ball is already placed in the shooter!

import org.metrobots.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossChevaldeFriseHighGoal2 extends CommandGroup {
	
	public CrossChevaldeFriseHighGoal2() {

		this.addSequential(new DriveStraight(1, 0.25)); //time, speed //robot drive up to defense
		this.addSequential(new ExtendorRetractShooter(true)); //true = extend 
		this.addSequential(new DriveStraight(1, 0.25)); //robot drive to "C" with dot on blue side

		this.addSequential(new DriveStraightForward(1, 0.25)); //time, speed //robot drive up to defense
		this.addSequential(new ExtendorRetractShooter(true)); //true = extend 
		this.addSequential(new DriveStraightForward(1, 0.25)); //robot drive to "C" with dot on blue side

		this.addSequential(new ExtendorRetractShooter(false)); //false = retract
		this.addSequential(new Turn(-120, 0.6)); //robot turn -120 degrees to line up the back of the shooter to the high goal
		
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
		this.addSequential(new IntakeLaunch(0.5,2)); //(speed, driveTime)
		}
		
	}
}
		

