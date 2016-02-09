package org.metrobots;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoCollectandLaunch extends CommandGroup {
	public AutoCollectandLaunch() {
		this.addParallel(new AutoDriveStraightForward(2, 0.25));
		this.addSequential(new AutoIntakeLaunch(0.45, 2));
		this.addSequential(new AutoRotation(0.5, true, .75));
		this.addSequential(new AutoIntakeLaunch(-1.0, 1.5));
	}

}
