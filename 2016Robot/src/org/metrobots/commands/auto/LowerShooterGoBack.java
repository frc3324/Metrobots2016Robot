package org.metrobots.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LowerShooterGoBack extends CommandGroup {
	public LowerShooterGoBack() {
		this.addSequential(new ExtendorRetractShooter(true)); //true = extend
		this.addSequential(new DriveStraight(2, -0.5));
	}

}
