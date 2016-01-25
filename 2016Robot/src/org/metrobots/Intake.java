package org.metrobots;

import edu.wpi.first.wpilibj.SpeedController;

public class Intake {
	public SpeedController motor, right;

	public Intake(SpeedController motor_) {
		motor = motor_;
	}

	public void intakeSpeed(double val) {
		motor.set(val);
	}
}