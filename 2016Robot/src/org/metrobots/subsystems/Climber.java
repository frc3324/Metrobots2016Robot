package org.metrobots.subsystems;
import org.metrobots.Robot;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem {
	
	public static Climber climber;
	
	public Climber(Climber climber_) {
		climber = climber_;
		Robot.eServo = new Servo(1);
		Robot.eServo.setAngle(90);
	}
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
