package org.metrobots.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossChevaldeFriseLowGoal4 extends CommandGroup {
	public CrossChevaldeFriseLowGoal4() {
		this.addSequential(new DriveStraight(1,0.25)); //robot drives up to defense
		this.addSequential(new ExtendorRetractShooter(true)); //true = extend
		this.addSequential(new DriveStraight(1, 0.25));
		this.addSequential(new ExtendorRetractShooter(false)); //false = retract
		this.addSequential(new Turn(30,0.6));
		this.addSequential(new DriveStraight(1,0.25)); //robot drives to side batter 
		this.addSequential(new Turn(-90,0.6));
		this.addSequential(new DriveStraight(2, 0.25)); //don't need to, but can (depends on shooter's range)
		this.addSequential(new Launch(1.0, 1.5)); //(motor speed, time)
	}

}
