package org.metrobots;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;

public class Robot extends IterativeRobot {
	/*
	 * Creation of variables should happen here. Do not set variable values
	 * here.
	 */

	public static DriveTrain chassis;
	public static CANTalon fl, bl, fr, br;
	public static Encoder flEncoder, blEncoder, frEncoder, brEncoder;
	public static Gyro gyro;
	public static DoubleSolenoid lDriveShift, rDriveShift;

	public static Timer timer;

	public static MetroDriverController driver;
	public static DriverStation ds;

	public void robotInit() {
		/*
		 * Variables should be initialized here
		 */

		fl = new CANTalon(1);
		bl = new CANTalon(2);
		fr = new CANTalon(3);
		br = new CANTalon(4);

		flEncoder = new Encoder(0, 1);
		frEncoder = new Encoder(2, 3);
		blEncoder = new Encoder(4, 5);
		brEncoder = new Encoder(6, 7);

		gyro = new Gyro(0);

		lDriveShift = new DoubleSolenoid(0, 1);
		rDriveShift = new DoubleSolenoid(2, 3);

		driver = new MetroDriverController(0);

		chassis = new DriveTrain(fl, bl, fr, br, flEncoder, blEncoder,
				frEncoder, brEncoder, gyro, lDriveShift, rDriveShift);
		chassis.setInvertedMotors(false, false, true, true);
		chassis.setDriveType(DriveTrain.SHIFTING_TANK_DRIVE);

		timer = new Timer();
		timer.start();

		ds = m_ds;

	}

	/**
	 * This function is called once when autonomous is enabled
	 */
	public void autonomousInit() {
		timer.reset();
		timer.start();
		gyro.reset();
		chassis.setDriveType(DriveTrain.SHIFTING_TANK_DRIVE);

		chassis.setHoldAngle(false);
		chassis.setTargetAngle(0);
		chassis.setFieldOriented(false);
		chassis.setGyroHoldSensitivity(2);

		Auton.setAutonCount(0);

	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Auton.run();
		printValues();
	}

	/**
	 * This function is called once when the teleop is enabled
	 */
	public void teleopInit() {
		chassis.setHoldAngle(false);
		chassis.setFieldOriented(false);
		chassis.setGyroHoldSensitivity(20);
		chassis.setDriveType(DriveTrain.SHIFTING_TANK_DRIVE);
		chassis.setTargetAngle(chassis.getGyro());

		driver.prevA = false;
		driver.toggleA = false;
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		double ly = driver.getAxis(MetroDriverController.LEFT_Y);
		double ry = driver.getAxis(MetroDriverController.RIGHT_Y);

		if (driver.getButton(MetroDriverController.RB))
			chassis.setLowGear();
		if (driver.getButton(MetroDriverController.LB))
			chassis.setHighGear();

		if (driver.getButton(MetroDriverController.BUTTON_B)) {
			chassis.resetGyro();
		}

		chassis.tankDrive(ly, ry);

		printValues();
	}

	public void disabledPeriodic() {

		if (driver.getButton(MetroDriverController.BUTTON_B)) {
			chassis.resetGyro();
		}

		printValues();

	}

	public void printValues() {

	}

}
