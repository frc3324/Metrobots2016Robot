package org.metrobots.commands.auto; //values and angles need to be tested

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossPortcullisHighGoal2 extends CommandGroup {
	public CrossPortcullisHighGoal2() {
		this.addSequential(new ExtendorRetractShooter(true)); //true = extend
		this.addSequential(new DriveStraight(1, 0.25)); //robot drives up to defense with shooter down/extended
		this.addParallel(new ExtendorRetractShooter(false));//false = retract 
		this.addParallel(new DriveStraight(1, 0.25)); //while the shooter is retracting, robot goes forward under defense
		this.addSequential(new Turn(-120, 0.6)); //turn at in line with side batter
		this.addSequential(new DriveStraight(1.5, 0.25)); //drive straight toward the middle on the batter to shoot 
		//vision
	}

}
