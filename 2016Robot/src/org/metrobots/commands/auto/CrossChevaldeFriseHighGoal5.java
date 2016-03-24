package org.metrobots.commands.auto; //remember that a ball is already placed in the shooter!

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossChevaldeFriseHighGoal5 extends CommandGroup {
	public CrossChevaldeFriseHighGoal5() {
		this.addSequential(new DriveStraight(1, 0.25)); //robot drives up to defense
		this.addSequential(new ExtendorRetractShooter(true)); //true = extend
		this.addSequential(new DriveStraight(1, 0.25)); //robot drives over defense to line up with side batter
		this.addSequential(new ExtendorRetractShooter(false)); //false = retract
		this.addSequential(new Turn(120, 0.6)); 
		this.addSequential(new DriveStraight(1, 0.25)); //don't need to, but can (depends on shooter's range)
		//vision code?
	}

}
