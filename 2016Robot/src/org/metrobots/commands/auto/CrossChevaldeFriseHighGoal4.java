package org.metrobots.commands.auto; //remember that a ball is already placed in the shooter!

import org.metrobots.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossChevaldeFriseHighGoal4 extends CommandGroup {
	public CrossChevaldeFriseHighGoal4() {

		this.addSequential(new DriveStraight(1, 0.25)); //robot drive up to defense
		this.addSequential(new ExtendorRetractShooter(true)); //true = extends
		this.addSequential(new DriveStraight(2, 0.25)); //robot drives to double circled point on dark green, blue side
		this.addSequential(new ExtendorRetractShooter(false)); //false = retracts
		this.addSequential(new Turn(-20, 0.6)); //turn -20 degrees from single circled dot, blue side
		this.addSequential(new DriveStraight(1, 0.25));
		this.addSequential(new DriveStraightForward(1, 0.25)); //robot drive up to defense
		this.addSequential(new ExtendorRetractShooter(true)); //true = extends
		this.addSequential(new DriveStraightForward(2, 0.25)); //robot drives to double circled point on dark green, blue side
		this.addSequential(new ExtendorRetractShooter(false)); //false = retracts
		this.addSequential(new Turn(-20, 0.6)); //turn -20 degrees from single circled dot, blue side
		this.addSequential(new DriveStraightForward(1, 0.25));
		this.addSequential(new Turn(-162, 0.6)); //robot turns around in order to shoot high goal
		
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