package org.metrobots.commands.auto; //can be used for ramparts, moat, rockwall 

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossRoughTerrainHighGoal3 extends CommandGroup {
	public CrossRoughTerrainHighGoal3() {
		this.addSequential(new DriveStraight(2, 0.25)); //robot drives over defense to double circled dot, blue side
		this.addSequential(new Turn(-28, 0.6));
		this.addSequential(new DriveStraight(2, 0.25)); //robot drives until in line with side batter
		this.addSequential(new Turn (-90, 0.6)); 
		this.addSequential(new DriveStraight(2, 0.25));
		this.addSequential(new Launch(1.0, 1.5)); //(motor speed, time)
	}

}
