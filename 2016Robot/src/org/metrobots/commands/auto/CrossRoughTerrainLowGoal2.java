package org.metrobots.commands.auto; //can be used for ramparts, moat, rockwall 

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossRoughTerrainLowGoal2 extends CommandGroup {
	public CrossRoughTerrainLowGoal2() {
		this.addSequential(new DriveStraightForward(2, 0.25)); //robot over defense to dot on "C", blue side
		this.addSequential(new Turn(60, 0.6));
		this.addSequential(new DriveStraightForward(2, 0.25)); //don't need to, but can (depends on shooter's range)
		this.addSequential(new IntakeLaunch(1.0, 1.5)); //launch ball (positive is outward) for launch class//(motor speed, driveTime) //changed Launch to IntakeLaunch
	}

}