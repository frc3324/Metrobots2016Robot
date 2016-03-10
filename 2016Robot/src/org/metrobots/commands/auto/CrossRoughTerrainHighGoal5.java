package org.metrobots.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossRoughTerrainHighGoal5 extends CommandGroup {
	public CrossRoughTerrainHighGoal5() {
		this.addSequential(new DriveStraightForward(2, 0.25));
		this.addSequential(new Turn(-60, 0.6));
		this.addSequential(new DriveStraightForward(2, 0.25)); //this line is optional (should we drive the robot up the ramparts to shoot?
		//vision
	}

}