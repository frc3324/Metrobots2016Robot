package org.metrobots.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossLowBarHighGoal extends CommandGroup {
	public CrossLowBarHighGoal() {
		this.addSequential(new DriveStraightForward(2, 0.25));
		this.addSequential(new Turn(65, 0.6)); //(degrees, speed)
		//vision code
	}

}
