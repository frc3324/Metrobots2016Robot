package org.metrobots;

import org.metrobots.commands.auto.CollectandLaunch;
import org.metrobots.commands.auto.CrossDefenseSetUpLowBar;
import org.metrobots.commands.auto.DriveBackward;
import org.metrobots.commands.auto.LowerShooterGoBack;
import org.metrobots.commands.teleop.DriveGroup;
import org.metrobots.subsystems.Climber;
import org.metrobots.subsystems.DriveTrain;
import org.metrobots.subsystems.IntakeLauncher;
import java.io.IOException;

import org.metrobots.botcv.communication.CommInterface;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import net.sf.lipermi.handler.CallHandler;
import net.sf.lipermi.net.Client;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Robot extends IterativeRobot {
	/*
	 * Creation of variables should happen here. Do not set variable values here.
	 * 
	 * This essentially means that we create variables, or instantiate them, so creating them, but not setting them. We
	 * set, or initialize them, in the robotInit method
	 */

	public static DriveTrain chassis; // Instantiate the drive train, an object from the DriveTrain.java, a custom file
					  // created by us

	public static SpeedController fl, bl, ml, fr, br, mr, intakeLeftMotor,
						intakeRightMotor, actuationMotor, windowMotor; // Instantiate talons(motor controllers)

	public static Encoder leftEncoder, rightEncoder; // Instantiate encoders
	public static Gyro gyro;// Instantiate gyro(rotational acceleration sensor)
	public static DoubleSolenoid driveShift, shootArm; // Instantiate solenoid (piston controller)
	
	public static Timer timer; // Instantiate the FRC Timer

	public static MetroXboxController driver, secondary; // Instantiate the xbox controller

	public static DriverStation ds; // Instantiate driver station on the computer
	
	public static IntakeLauncher intakeLauncher;
	public static double actuateTime, startTime;
	public static AnalogInput anglePot;
	public static DigitalInput actuationLimit;
	
	public static Climber climber;
	public static CommInterface comms;
	
	public static CameraServer camera;

	public void robotInit() {
		
		/*
		 * 
		 * This is the initialization of the robot. This method will run one time at the moment that the roboRIO starts
		 * up. This is where you tell which ports are which components
		 */

		fl = new CANTalon(3);
		ml = new CANTalon(4);
		bl = new CANTalon(5);
		fr = new CANTalon(0);
		mr = new CANTalon(1);
		br = new CANTalon(2);

		intakeLeftMotor = new Talon(8);
		intakeRightMotor = new Talon(6);
		actuationMotor = new Talon(9);
		windowMotor = new Talon(7);
		
		anglePot = new AnalogInput(0);
		actuationLimit = new DigitalInput(2);

		leftEncoder = new Encoder(6, 7); // Left gearbox assembly encoder - Ports 6, 7
		rightEncoder = new Encoder(8, 9); // Right gearbox assembly encoder - Port 7, 8

		gyro = new AnalogGyro(1); // Robot turning gyro - Port 0

		//driveShift = new DoubleSolenoid(0, 1); // Shifting solenoid - Port 0, 1
		shootArm = new DoubleSolenoid(0, 1);

		driver = new MetroXboxController(0); // Primary driver controller - Port 0
		secondary = new MetroXboxController(1); // Secondary driver controller - Port 1

		chassis = new DriveTrain(fl, ml, bl, fr, mr, br, leftEncoder,
						rightEncoder, gyro, driveShift); // Create drive train object for future manipulation

		//chassis.setInvertedMotors(false, false, true, true); // Invert proper motors, relative to the situation on the
																// robot

		chassis.setDriveType(DriveTrain.SIX_MOTOR_TANK_DRIVE); // Set drive type to shifting

		intakeLauncher = new IntakeLauncher(intakeLeftMotor, intakeRightMotor, actuationMotor, shootArm, anglePot);
		
		timer = new Timer();
		timer.start(); // Start timer

		ds = m_ds; // Pretty much just renames driver station, so we can type ds instead of m_ds
		
		startTime = 0.0;
		actuateTime = 0.0;

		/*CallHandler callHandler = new CallHandler();
		try {
			Client client = new Client("127.0.0.1", 5800, callHandler);
			comms = (CommInterface) client.getGlobal(CommInterface.class);
		} catch (IOException e) {
			System.err.println("Could not establish communications with tablet!");
			e.printStackTrace();
		}*/
		
		camera = CameraServer.getInstance();
		camera.setQuality(50);
		camera.startAutomaticCapture("cam0");
	}

	/**
	 * This function is called once when autonomous is enabled
	 */
	public void autonomousInit() {
		timer.reset(); // Resets timer
		timer.start(); // Starts timer
		//gyro.reset(); // Reset gyro angle
		chassis.setDriveType(DriveTrain.SIX_MOTOR_TANK_DRIVE); // Set drive type to shifting tank
		chassis.setGyroHoldSensitivity(2); // Change sensitivity of gyro
		chassis.setHoldAngle(false); // Make the robot not keep its angle still
		chassis.setTargetAngle(0); // Make the robot not hold its angle
		chassis.setFieldOriented(false); // Make robot not field oriented
		Scheduler.getInstance().add(new CrossDefenseSetUpLowBar());

		//Auton.setAutonCount(0); // Set auton to begin
		//Scheduler.getInstance().add(new DriveBackward());
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		//Auton.run(); // Run autonomous
		Scheduler.getInstance().run();
		printValues(); // Print autonomous debug
	}

	/**
	 * This function is called once when the teleop is enabled
	 */
	public void teleopInit() {
		chassis.setHoldAngle(false); // Do not keep angle
		chassis.setFieldOriented(false); // Do not orient to field
		chassis.setGyroHoldSensitivity(20); // Hold angle sensitivity of 20
		//chassis.setDriveType(DriveTrain.SIX_MOTOR_TANK_DRIVE); // Set drive type to Shifting tank drive
		chassis.setTargetAngle(chassis.getGyro()); // Set target angle of hold angle to current gyro angle
		intakeLauncher.intake(0);
		intakeLauncher.actuateSpeed(0);
		Scheduler.getInstance().add(new DriveGroup());
	
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		printValues(); // Print debug values
	}

	public void disabledPeriodic() {

		if (driver.getButton(MetroXboxController.BUTTON_B))
			chassis.resetGyro(); // Reset gyro value once B button is pressed

		printValues(); // Print debug values

	}

	public void printValues() {
		System.out.println("Potentiometer: " + Double.toString(Robot.anglePot.getValue()));		
	}

}