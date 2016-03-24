package org.metrobots.commands.auto; //can be used for ramparts, moat, rockwall

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossRoughTerrainHighGoal4 extends CommandGroup {
	public CrossRoughTerrainHighGoal4() {
		this.addSequential(new DriveStraight(2, 0.25)); //robot drives up to defense
		this.addSequential(new Turn(30,0.6));
		this.addSequential(new DriveStraight(1,0.25)); //robot drives to side batter 
		this.addSequential(new Turn(-90,0.6));
		this.addSequential(new DriveStraight(2, 0.25)); //don't need to, but can (depends on shooter's range)
		//vision
	}

}
