package org.metrobots;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveandRotate extends CommandGroup {
	public DriveandRotate() {
		this.addSequential(new AutoDriveStraightForward(2, 0.25));
		this.addSequential(new AutoRotation(0.25, true, 2));
	}

}
