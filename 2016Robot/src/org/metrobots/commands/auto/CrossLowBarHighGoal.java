package org.metrobots.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossLowBarHighGoal extends CommandGroup {
	public CrossLowBarHighGoal() {
		this.addSequential(new DriveStraight(2, 0.25));
		this.addSequential(new Turn(-115, 0.6)); //(degrees, speed) //turns the robot to line up with high goal
		//vision code
	}

}
