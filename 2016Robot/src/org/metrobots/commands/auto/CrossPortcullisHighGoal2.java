package org.metrobots.commands.auto; //values and angles need to be tested

import org.metrobots.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossPortcullisHighGoal2 extends CommandGroup {
	public CrossPortcullisHighGoal2() {
		this.addSequential(new ExtendorRetractShooter(true)); //true = extend
		this.addSequential(new DriveStraight(1, 0.25)); //robot drives up to defense with shooter down/extended
		this.addParallel(new ExtendorRetractShooter(false));//false = retract 
		this.addParallel(new DriveStraight(1, 0.25)); //while the shooter is retracting, robot goes forward under defense
		this.addSequential(new Turn(-120, 0.6)); //turn at in line with side batter
		this.addSequential(new DriveStraight(1.5, 0.25)); //drive straight toward the middle on the batter to shoot 
		this.addSequential(new DriveStraightForward(1, 0.25)); //robot drives up to defense with shooter down/extended
		this.addParallel(new ExtendorRetractShooter(false));//false = retract 
		this.addParallel(new DriveStraightForward(1, 0.25)); //while the shooter is retracting, robot goes forward under defense
		this.addSequential(new Turn(-120, 0.6)); //turn at in line with side batter
		this.addSequential(new DriveStraightForward(1.5, 0.25)); //drive straight toward the middle on the batter to shoot 
		
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


