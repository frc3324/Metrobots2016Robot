package org.metrobots.commands.auto; //remember that a ball is already placed in the shooter!

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossChevaldeFriseHighGoal3 extends CommandGroup {
	public CrossChevaldeFriseHighGoal3() {
		this.addSequential(new DriveStraight(1, 0.25)); //robot drives up to defense
		this.addSequential(new ExtendorRetractShooter(true)); //true = extends
		this.addSequential(new DriveStraight(1, 0.25)); //robot drive to single circle dot, light green, blue side
		this.addSequential(new Turn(-10, 0.6)); //(degree, speed)
		this.addSequential(new DriveStraight(2, 0.25)); //robot drives to 
		this.addSequential(new Turn(-170, 0.6)); //robot turns around in order to shoot high goal
		//vision code
	}
}
