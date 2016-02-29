package org.metrobots.commands.auto;

import org.metrobots.commands.teleop.Turn;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossDefenseSetUpRoughTerrain3 extends CommandGroup {
	public CrossDefenseSetUpRoughTerrain3() {
		this.addSequential(new DriveStraightForward(2, 0.25));
		this.addSequential(new Turn(25, 0.6));
	}

}
