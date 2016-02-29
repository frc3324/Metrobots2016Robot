package org.metrobots.commands.auto;

import org.metrobots.commands.teleop.Turn;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossDefenseSetUpRoughTerrain2 extends CommandGroup {
	public CrossDefenseSetUpRoughTerrain2() {
		this.addSequential(new DriveStraightForward(2, 0.25)); 
		this.addSequential(new Turn(48, 0.6));
		
	}

}
