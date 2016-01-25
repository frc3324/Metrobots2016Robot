package org.metrobots;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;

public class Robot extends IterativeRobot {
	/*
	 * Creation of variables should happen here. Do not set variable values here.
	 * 
	 * This essentially means that we create variables, or instantiate them, so creating them, but not setting them. We
	 * set, or initialize them, in the robotInit method
	 */

	public static DriveTrain chassis; // Instantiate the drive train, an object from the DriveTrain.java, a custom file
					  // created by us

	public static Talon fl, ml, bl, fr, mr, br; // Instantiate talons(motor controllers)

	public static Encoder leftEncoder, rightEncoder; // Instantiate encoders
	public static Gyro gyro; // Instantiate gyro(rotational acceleration sensor)
	public static DoubleSolenoid driveShift; // Instantiate solenoid (piston controller)

	public static Timer timer; // Instantiate the FRC Timer

	public static MetroXboxController driver; // Instantiate the xbox controller

	public static DriverStation ds; // Instantiate driver station on the computer

	public void robotInit() {
		/*
		 * This is the initialization of the robot. This method will run one time at the moment that the roboRIO starts
		 * up. This is where you tell which ports are which components
		 */

		fl = new Talon(0); // Front left - Port 0
		ml = new Talon(1); // Middle left - Port 1
		bl = new Talon(2); // Back left - Port 2
		fr = new Talon(3); // Front right - Port 3
		mr = new Talon(4); // Middle right - Port 4
		br = new Talon(5); // Back right - Port 5

		leftEncoder = new Encoder(6, 7); // Left gearbox assembly encoder - Ports 6, 7
		rightEncoder = new Encoder(8, 9); // Right gearbox assembly encoder - Port 7, 8

		gyro = new AnalogGyro(0); // Robot turning gyro - Port 0

		driveShift = new DoubleSolenoid(0, 1); // Shifting solenoid - Port 0, 1

		driver = new MetroXboxController(0); // Primary driver controller - Port 0

		chassis = new DriveTrain(fl, ml, bl, fr, mr, br, leftEncoder,
						rightEncoder, gyro, driveShift); // Create drive train object for future manipulation

		chassis.setInvertedMotors(false, false, true, false); // Invert proper motors, relative to the situation on the
																// robot

		chassis.setDriveType(DriveTrain.SIX_MOTOR_TANK_DRIVE); // Set drive type to shifting

		timer = new Timer();
		timer.start(); // Start timer

		ds = m_ds; // Pretty much just renames driver station, so we can type ds instead of m_ds

	}

	/**
	 * This function is called once when autonomous is enabled
	 */
	public void autonomousInit() {
		timer.reset(); // Resets timer
		timer.start(); // Starts timer
		gyro.reset(); // Reset gyro angle
		chassis.setDriveType(DriveTrain.SIX_MOTOR_TANK_DRIVE); // Set drive type to shifting tank

		chassis.setHoldAngle(false); // Make the robot not keep its angle still
		chassis.setTargetAngle(0); // Make the robot not hold its angle
		chassis.setFieldOriented(false); // Make robot not field oriented
		chassis.setGyroHoldSensitivity(2); // Change sensitivity of gyro

		Auton.setAutonCount(0); // Set auton to beginning

	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Auton.run(); // Run autonomous
		printValues(); // Print autonomous debug
	}

	/**
	 * This function is called once when the teleop is enabled
	 */
	public void teleopInit() {
		chassis.setHoldAngle(false); // Do not keep angle
		chassis.setFieldOriented(false); // Do not orient to field
		chassis.setGyroHoldSensitivity(20); // Hold angle sensitivity of 20
		chassis.setDriveType(DriveTrain.SIX_MOTOR_TANK_DRIVE); // Set drive type to Shifting tank drive
		chassis.setTargetAngle(chassis.getGyro()); // Set target angle of hold angle to current gyro angle
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		double ly = driver.getAxis(MetroXboxController.LEFT_Y); // Set left motors to left joystick
		double ry = driver.getAxis(MetroXboxController.RIGHT_Y); // Set right motors to right joystick

		if (driver.getButton(MetroXboxController.BUTTON_B))
			chassis.resetGyro(); // Reset gyro value once B button is pressed

		chassis.tankDrive(ly, ry); // Drive the robot

		printValues(); // Print debug values
	}

	public void disabledPeriodic() {

		if (driver.getButton(MetroXboxController.BUTTON_B))
			chassis.resetGyro(); // Reset gyro value once B button is pressed

		printValues(); // Print debug values

	}

	public void printValues() {

	}

}
