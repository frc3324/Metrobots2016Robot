package org.metrobots.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossPortcullisLowGoal4 extends CommandGroup {
	public CrossPortcullisLowGoal4() {
		this.addSequential(new ExtendorRetractShooter(true)); //true = extend
		this.addSequential(new DriveStraightForward(1, 0.25)); //robot drives up to defense with shooter down/extended
		this.addParallel(new ExtendorRetractShooter(false));//false = retract 
		this.addParallel(new DriveStraightForward(1, 0.25)); //while the shooter is retracting, robot goes forward under defense
		this.addSequential(new Turn(30,0.6));
		this.addSequential(new DriveStraightForward(1,0.25)); //robot drives to side batter 
		this.addSequential(new Turn(-90,0.6));
		this.addSequential(new DriveStraightForward(1, 0.25)); //don't need to, but can (depends on shooter's range)
		this.addSequential(new Launch(1.0, 1.5)); //(motor speed, time)
	}

}
