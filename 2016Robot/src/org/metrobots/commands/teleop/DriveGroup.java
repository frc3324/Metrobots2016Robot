package org.metrobots.commands.teleop;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveGroup extends CommandGroup {
	public DriveGroup() {
		this.addParallel(new DriveSixMotor());
		this.addParallel(new OperateIntakeLaunching());
	}
	

}
