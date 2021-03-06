package org.metrobots.commands.auto; //can be used for ramparts, moat, rockwall 

import org.metrobots.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossRoughTerrainHighGoal2 extends CommandGroup {
	public CrossRoughTerrainHighGoal2() {
		this.addSequential(new DriveStraight(2, 0.25)); //drive to bottom goal (reference to map) 
		this.addSequential(new Turn(-120, 0.6)); //
		this.addSequential(new DriveStraight(1, 0.25)); //don't need to, but can (depends on shooter's range)
		this.addSequential(new DriveStraightForward(2, 0.25)); //drive to bottom goal (reference to map) 
		this.addSequential(new Turn(-120, 0.6)); //
		this.addSequential(new DriveStraightForward(1, 0.25)); //don't need to, but can (depends on shooter's range)
		
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

