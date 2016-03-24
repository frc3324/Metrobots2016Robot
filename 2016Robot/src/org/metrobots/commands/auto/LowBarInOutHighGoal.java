package org.metrobots.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LowBarInOutHighGoal extends CommandGroup {
	public LowBarInOutHighGoal() {
		this.addSequential(new DriveStraight(2, 0.5)); //drive robot through low bar
		for (int i = 0; i < 2; i++) {
			this.addSequential(new DriveStraight(-2, 0.5)); //drive back out
			this.addSequential(new DriveStraight(2, 0.5));
		}
		this.addSequential(new Turn(-115, 0.6)); //(degree, speed)
		//vision code here
		//this.addSequential(new Launch(1.0, 1.5)); //(motor speed, time)
	}

}
