package org.metrobots.commands.auto; //can be used for ramparts, moat, rockwall 

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossRoughTerrainHighGoal2 extends CommandGroup {
	public CrossRoughTerrainHighGoal2() {
		this.addSequential(new DriveStraight(2, 0.25)); //drive to bottom goal (reference to map) 
		this.addSequential(new Turn(-120, 0.6)); //
		this.addSequential(new DriveStraight(1, 0.25)); //don't need to, but can (depends on shooter's range)
		//vision shoot
	}

}
