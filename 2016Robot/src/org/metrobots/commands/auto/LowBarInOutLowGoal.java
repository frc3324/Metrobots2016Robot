package org.metrobots.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LowBarInOutLowGoal extends CommandGroup {
	public LowBarInOutLowGoal() {
		this.addSequential(new DriveStraight(2, 0.5)); //drive robot through low bar
		this.addSequential(new DriveStraight(-2, 0.5)); //drive back out
		this.addSequential(new DriveStraight(2, 0.5));
		this.addSequential(new DriveStraight(-2, 0.5)); //repeat; now the defense is destroyed
		this.addSequential(new DriveStraight(2, 0.5)); //continue through the low goal to prepare for low goal
		this.addSequential(new Turn(65, 0.6)); //(degree, speed)
		this.addSequential(new DriveStraight(2, 0.25)); //don't need to, but can (depends on shooter's range)
		this.addSequential(new IntakeLaunch(1.0, 1.5)); //(motor speed, driveTime) //changed Launch to IntakeLaunch
	}

}
