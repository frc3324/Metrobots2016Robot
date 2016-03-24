package org.metrobots.commands.auto; //remember that a ball is already placed in the shooter!

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossChevaldeFriseHighGoal4 extends CommandGroup {
	public CrossChevaldeFriseHighGoal4() {
		this.addSequential(new DriveStraight(1, 0.25)); //robot drive up to defense
		this.addSequential(new ExtendorRetractShooter(true)); //true = extends
		this.addSequential(new DriveStraight(2, 0.25)); //robot drives to double circled point on dark green, blue side
		this.addSequential(new ExtendorRetractShooter(false)); //false = retracts
		this.addSequential(new Turn(-20, 0.6)); //turn -20 degrees from single circled dot, blue side
		this.addSequential(new DriveStraight(1, 0.25));
		this.addSequential(new Turn(-162, 0.6)); //robot turns around in order to shoot high goal
		//vision code?
	}

}
