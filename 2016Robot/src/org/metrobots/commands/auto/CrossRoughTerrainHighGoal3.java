package org.metrobots.commands.auto; //can be used for ramparts, moat, rockwall 

import org.metrobots.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossRoughTerrainHighGoal3 extends CommandGroup {
	public CrossRoughTerrainHighGoal3() {
		this.addSequential(new DriveStraight(2, 0.25)); //robot drives over defense to double circled dot, blue side
		this.addSequential(new Turn(-28, 0.6));
		this.addSequential(new DriveStraight(2, 0.25)); //robot drives until in line with side batter
		this.addSequential(new Turn (-90, 0.6)); 
		this.addSequential(new DriveStraight(2, 0.25));
		this.addSequential(new DriveStraightForward(2, 0.25)); //robot drives over defense to double circled dot, blue side
		this.addSequential(new Turn(-28, 0.6));
		this.addSequential(new DriveStraightForward(2, 0.25)); //robot drives until in line with side batter
		this.addSequential(new Turn (-90, 0.6)); 
		this.addSequential(new DriveStraightForward(2, 0.25));
		//this.addSequential(new Launch(1.0, 1.5)); //(motor speed, time)
	
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
