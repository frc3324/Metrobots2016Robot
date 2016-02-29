package org.metrobots.commands.teleop;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.SpeedController;

public class EjectorTask extends Thread {
	private double speed = 0;
	private SpeedController left;
	private SpeedController right;
	private DigitalOutput output;
	
	public EjectorTask(SpeedController left, SpeedController right, DigitalOutput output) {
		this.left = left;
		this.right = right;
		this.output = output;
	}
	
	public void run() {
		while (true) {
			if (speed != 0) {
				try {
					left.set(speed);
					right.set(speed);
					sleep(1000);
					output.set(true);
					sleep(250);
					output.set(false);
					left.set(0);
					right.set(0);
				}
				catch (Exception e) { }
				speed = 0;
			}
		}
	}
	
	public void fire (double speed) {
		this.speed = speed;
	}
}
