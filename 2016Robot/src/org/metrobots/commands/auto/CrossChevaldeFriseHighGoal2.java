package org.metrobots.commands.auto; //values need to be checked //remember that a ball is already placed in the shooter!

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossChevaldeFriseHighGoal2 extends CommandGroup {
	public CrossChevaldeFriseHighGoal2() {
		this.addSequential(new DriveStraightForward(1, 0.25)); //time, speed //robot drive up to defense
		this.addSequential(new ExtendorRetractShooter(true)); //true = extend 
		this.addSequential(new DriveStraightForward(1, 0.25)); //robot drive to "C" with dot on blue side
		this.addSequential(new ExtendorRetractShooter(false)); //false = retract
		this.addSequential(new Turn(-120, 0.6)); //robot turn -120 degrees to line up the back of the shooter to the high goal
		//vision code here
	}

}
