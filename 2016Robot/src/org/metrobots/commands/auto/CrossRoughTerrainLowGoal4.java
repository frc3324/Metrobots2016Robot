package org.metrobots.commands.auto; //can be used for ramparts, moat, rockwall

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossRoughTerrainLowGoal4 extends CommandGroup {
	public CrossRoughTerrainLowGoal4() {
		this.addSequential(new DriveStraightForward(2, 0.25)); //robot drives up to defense
		this.addSequential(new Turn(30,0.6));
		this.addSequential(new DriveStraightForward(1,0.25)); //robot drives to side batter 
		this.addSequential(new Turn(-90,0.6));
		this.addSequential(new DriveStraightForward(2, 0.25)); //don't need to, but can (depends on shooter's range)
		this.addSequential(new IntakeLaunch(1.0, 1.5)); //(motor speed, driveTime) //changed Launch to IntakeLaunch
	}

}