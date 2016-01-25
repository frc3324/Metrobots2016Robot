package org.metrobots;

public class Auton {

	public static String autonType = "Example";

	public static int autonCount = 0;

	public static void run() {
		if (autonType == "Example") {

			switch (autonCount) {
			case 0:
				Robot.chassis.tankDrive(1.0, 1.0);
				if (Robot.timer.get() > 1.0) {
					advanceStep();
				}
				break;
			case 1:
				Robot.chassis.tankDrive(0.0, 0.0);
				advanceStep();
				break;
			}

		} else {

		}
	}

	public static String getAutonType() {
		return autonType;
	}

	public static void setAutonType(String s) {
		autonType = s;
	}

	public static void setAutonCount(int num) {
		autonCount = num;
	}

	public static int getAutonCount() {
		return autonCount;
	}

	private static void advanceStep() {
		autonCount++;
		Robot.timer.reset();
		Robot.timer.start();
	}
}
