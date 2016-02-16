package org.metrobots.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveForward extends CommandGroup {
	public DriveForward() {
		this.addSequential(new DriveStraightForward(5, 0.5));
	}
}
