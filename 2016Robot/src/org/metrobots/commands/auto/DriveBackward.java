package org.metrobots.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveBackward extends CommandGroup {
	public DriveBackward() {
		this.addSequential(new DriveStraightForward(5, -0.5));
	}
}
