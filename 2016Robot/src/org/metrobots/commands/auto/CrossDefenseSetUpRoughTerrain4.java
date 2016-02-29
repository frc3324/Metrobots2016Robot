package org.metrobots.commands.auto;

import org.metrobots.commands.teleop.Turn;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossDefenseSetUpRoughTerrain4 extends CommandGroup {
	public CrossDefenseSetUpRoughTerrain4() {
		this.addSequential(new DriveStraightForward(2, 0.25));
		this.addSequential(new Turn(-10, 0.6));
	}

}
