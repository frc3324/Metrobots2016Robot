package org.metrobots.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossPortcullisLowGoal3 extends CommandGroup {
	public CrossPortcullisLowGoal3() {
		this.addSequential(new ExtendorRetractShooter(true)); //true = extend
		this.addSequential(new DriveStraight(1, 0.25)); //robot drives up to defense with shooter down/extended 
		this.addParallel(new ExtendorRetractShooter(false)); //false = retract
		this.addParallel(new DriveStraight(2, 0.25)); //while the shooter is retracting, robot goes forward under defense
		this.addSequential(new Turn(-23,0.6)); 
		this.addSequential(new DriveStraight(1, 0.25));
		this.addSequential(new Turn(90,0.6));
		this.addSequential(new DriveStraight(2, 0.25)); //don't need to, but can (depends on shooter's range)
		this.addSequential(new DriveStraightForward(1, 0.25)); //robot drives up to defense with shooter down/extended 
		this.addParallel(new ExtendorRetractShooter(false)); //false = retract
		this.addParallel(new DriveStraightForward(2, 0.25)); //while the shooter is retracting, robot goes forward under defense
		this.addSequential(new Turn(-23,0.6)); 
		this.addSequential(new DriveStraightForward(1, 0.25));
		this.addSequential(new Turn(90,0.6));
		this.addSequential(new DriveStraightForward(2, 0.25)); //don't need to, but can (depends on shooter's range)
		this.addSequential(new IntakeLaunch(1.0, 1.5)); //(motor speed, driveTime) //changed Launch to IntakeLaunch
	}

}
