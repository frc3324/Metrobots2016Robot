package org.metrobots.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveandRotate extends CommandGroup {
	public DriveandRotate() {
		this.addSequential(new DriveStraightForward(2, 0.25));
		this.addSequential(new Rotate(0.25, true, 2));
	}

}
