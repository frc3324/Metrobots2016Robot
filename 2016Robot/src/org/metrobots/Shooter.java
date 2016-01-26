package org.metrobots;

import edu.wpi.first.wpilibj.SpeedController;

public class Shooter {
	public SpeedController left, right;

	public Shooter(SpeedController left_, SpeedController right_) {
		left = left_;
		right = right_;
	}

	public void shootSpeed(double val) {
		left.set(val * -1);
		right.set(val);
	}
}