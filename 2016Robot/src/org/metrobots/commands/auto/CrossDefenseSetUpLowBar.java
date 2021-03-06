package org.metrobots.commands.auto; //need to test for driveTime and Speed (DriveStraightForward) //need to test for angle and speed (TurnSomeDegreeRight)

//import org.metrobots.commands.teleop.Turn;
//what is this code for; we already have code for low bar low goal
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossDefenseSetUpLowBar extends CommandGroup {
	
	public CrossDefenseSetUpLowBar() {
		this.addSequential(new DriveStraightForward(0.1, -0.65));
		this.addParallel(new IntakeLaunch(-0.6, 3));
		this.addParallel(new PositionShooter(false)); //false = down  //true = up
		this.addSequential(new DriveStraightForward(1.25, -0.65));
		this.addParallel(new IntakeLaunch(0.0, 0));
		this.addSequential(new DriveStraightForward(0.1, 0.0));
		//this.addSequential(new DriveIncreaseGradually(4, 0, -0.0035)); //drives the robot straight forward over the defense and then readjusts using gyro
		//this.addSequential(new Turn(60, 0.6)); //add angle (degree, speed)
	}
}
