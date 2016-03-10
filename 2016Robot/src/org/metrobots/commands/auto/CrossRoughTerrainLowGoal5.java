package org.metrobots.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossRoughTerrainLowGoal5 extends CommandGroup {
	public CrossRoughTerrainLowGoal5() {
		this.addSequential(new DriveStraightForward(2, 0.25));
		this.addSequential(new Turn(-60, 0.6));
		this.addSequential(new DriveStraightForward(2, 0.25)); //this line is optional (should we drive the robot up the ramparts to shoot?
		this.addSequential(new Launch(1.0, 1.5)); //(motor speed, time)
	}

}
