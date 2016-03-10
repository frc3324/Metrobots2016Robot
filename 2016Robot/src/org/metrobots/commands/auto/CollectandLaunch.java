package org.metrobots.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CollectandLaunch extends CommandGroup {
	public CollectandLaunch() {
		this.addParallel(new DriveStraightForward(2, 0.25));
		this.addSequential(new IntakeLaunch(0.45, 2));
		this.addSequential(new Rotate(0.5, true, .75));
		this.addSequential(new IntakeLaunch(1.0, 1.5));
	}

}
