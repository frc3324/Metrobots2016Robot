package org.metrobots.commands.auto; //need to test for driveTime and Speed (DriveStraightForward) //need to test for angle and speed (TurnSomeDegreeRight)

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossLowBarLowGoal extends CommandGroup { //Low bar defense stays in position one throughout the match
	public CrossLowBarLowGoal() {
		this.addSequential(new DriveStraightForward(2, 0.25)); //drives the robot straight forward over the defense and then readjusts using gyro
		this.addSequential(new Turn(65, 0.6)); //(degree, speed)
		this.addSequential(new DriveStraightForward(2, 0.25)); //don't need to, but can (depends on shooter's range)
		this.addSequential(new Launch(1.0, 1.5)); //(motor speed, time)
	}
	

}
