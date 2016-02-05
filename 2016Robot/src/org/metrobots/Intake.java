package org.metrobots;

import edu.wpi.first.wpilibj.SpeedController;

public class Intake {
	public SpeedController left, right;

	public Intake(SpeedController left_, SpeedController right_) {
		left = left_;
		right = right_;
	}

	public void set(double val) {
		left.set(val);
		right.set(-val);
	}
}