package org.metrobots.commands.auto;

import org.metrobots.commands.teleop.Turn;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossDefenseSetUpRoughTerrain5 extends CommandGroup {
	public CrossDefenseSetUpRoughTerrain5() {
		this.addSequential(new DriveStraightForward(2, 0.25));
		this.addSequential(new Turn(-33, 0.6));
	}

}
