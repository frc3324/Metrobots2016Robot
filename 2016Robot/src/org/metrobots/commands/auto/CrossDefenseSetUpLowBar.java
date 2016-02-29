package org.metrobots.commands.auto; //need to test for driveTime and Speed (DriveStraightForward) //need to test for angle and speed (TurnSomeDegreeRight)

import org.metrobots.commands.teleop.Turn;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossDefenseSetUpLowBar extends CommandGroup {
	public CrossDefenseSetUpLowBar() {
		this.addSequential(new DriveStraightForward(2, 0.25)); //drives the robot straight forward over the defense and then readjusts using gyro
		this.addSequential(new Turn(60, 0.6)); //add angle (degree, speed)
	}
	

}
