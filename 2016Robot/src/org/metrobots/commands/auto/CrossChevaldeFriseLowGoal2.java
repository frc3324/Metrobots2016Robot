package org.metrobots.commands.auto; //needs to be tested for values and degrees

import org.metrobots.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossChevaldeFriseLowGoal2 extends CommandGroup {
	public CrossChevaldeFriseLowGoal2() {
		this.addSequential(new DriveStraight(1, 0.25)); //robot drives up to defense
		this.addSequential(new ExtendorRetractShooter(true)); //true = extend
		this.addSequential(new DriveStraight(2, 0.25));
		this.addSequential(new ExtendorRetractShooter(false)); //false = retract
		this.addSequential(new Turn(60,0.6)); //robot drives to dot on "C", blue side
		this.addSequential(new DriveStraight(1,0.25)); //don't need to, but can (depends on shooter's range)
		this.addSequential(new DriveStraightForward(1, 0.25)); //robot drives up to defense
		this.addSequential(new ExtendorRetractShooter(true)); //true = extend
		this.addSequential(new DriveStraightForward(2, 0.25));
		this.addSequential(new ExtendorRetractShooter(false)); //false = retract
		this.addSequential(new Turn(60,0.6)); //robot drives to dot on "C", blue side
		this.addSequential(new DriveStraightForward(1,0.25)); //don't need to, but can (depends on shooter's range)
		this.addSequential(new IntakeLaunch(1.0, 1.5)); //Launch changed to IntakeLaunch
	}
}
