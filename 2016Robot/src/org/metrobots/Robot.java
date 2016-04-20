package org.metrobots;

import org.metrobots.commands.auto.CollectandLaunch;
import org.metrobots.commands.auto.CrossDefenseSetUpLowBar;
import org.metrobots.commands.auto.DriveBackward;
import org.metrobots.commands.auto.LowerShooterGoBack;
import org.metrobots.commands.teleop.DriveGroup;
import org.metrobots.subsystems.Climber;
import org.metrobots.subsystems.DriveTrain;
import org.metrobots.subsystems.IntakeLauncher;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Servo;
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
	private static PowerDistributionPanel pdp = new PowerDistributionPanel();
	public static DriveTrain chassis; // Instantiate the drive train, an object from the DriveTrain.java, a custom file
					  // created by us

	public static MetroSpeedController fl, bl, ml, fr, br, mr, intakeLeftMotor,
						intakeRightMotor, actuationMotor, windowMotor; // Instantiate talons(motor controllers)

	public static Encoder leftEncoder, rightEncoder; // Instantiate encoders
	public static Gyro gyro; //Instantiate gyro (rotational acceleration sensor)
	public static DoubleSolenoid driveShift, shootArm; // Instantiate solenoid (piston controller)
	
	public static Timer timer; // Instantiate the FRC Timer

	public static MetroXboxController driver, secondary; // Instantiate the xbox controller

	public static DriverStation ds; // Instantiate driver station on the computer
	
	public static IntakeLauncher intakeLauncher;
	public static double actuateTime, startTime;
	public static AnalogInput anglePot;
	public static DigitalInput windowLimit, actuationTopLimit, actuationBottomLimit;
	
	public static Climber climber;
	public static CommInterface comms;
	
	public static CameraServer camera;

	public static Servo eServo;

	public void robotInit() {
		
	//Servo eServo;
		//eServo.set(.5);
		
		/*
		 * 
		 * This is the initialization of the robot. This method will run one time at the moment that the roboRIO starts
		 * up. This is where you tell which ports are which components
		 */
		
		/*fl = new Talon(0);
		ml = new Talon(9);
		bl = new Talon(1);
		fr = new Talon(2);
		mr = new Talon(8);
		br = new Talon(3);
		
		intakeLeftMotor = new Talon(4);
		intakeRightMotor = new Talon(5);
		actuationMotor = new Talon(6);
		windowMotor = new Talon(7);*/
		
		fl = new MetroSpeedController(0, 13);
		ml = new MetroSpeedController(9, 12);
		bl = new MetroSpeedController(1, 14);
		fr = new MetroSpeedController(2, 15);
		mr = new MetroSpeedController(8, 11);
		br = new MetroSpeedController(3, 0);
		

		intakeLeftMotor = new MetroSpeedController(4, 1);
		intakeRightMotor = new MetroSpeedController(5, 2);
		actuationMotor = new MetroSpeedController(6, 3);
		windowMotor = new MetroSpeedController(7, 10);

		anglePot = new AnalogInput(1);
		windowLimit = new DigitalInput(2);
		actuationBottomLimit = new DigitalInput(3);
		actuationTopLimit = new DigitalInput(4);
		
		
		leftEncoder = new Encoder(6, 7); // Left gearbox assembly encoder - Ports 6, 7
		rightEncoder = new Encoder(8, 9); // Right gearbox assembly encoder - Port 7, 8

		gyro = new AnalogGyro(0); // Robot turning gyro - Port 0

		//driveShift = new DoubleSolenoid(0, 1); // Shifting solenoid - Port 0, 1
		//shootArm = new DoubleSolenoid(0, 1);

		driver = new MetroXboxController(0); // Primary driver controller - Port 0
		secondary = new MetroXboxController(1); // Secondary driver controller - Port 1

		chassis = new DriveTrain(fl, ml, bl, fr, mr, br, leftEncoder,
						rightEncoder, gyro, null); // Create drive train object for future manipulation

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
		writelogfile();
		//writelogfile("hello");
	}

	public void disabledPeriodic() {

		/*if (driver.getButton(MetroXboxController.BUTTON_B))
			chassis.resetGyro(); // Reset gyro value once B button is pressed*/

		printValues(); // Print debug values
		writelogfile();

	}

	public void printValues() {
		//System.out.println("Potentiometer: " + Double.toString(Robot.anglePot.getValue()));
		//System.out.println("Potentiometer: " + Double.toString(Robot.anglePot.getValue()));
		//System.out.println("Actuation limit: " + Boolean.toString(Robot.windowLimit.get()));
		
		
		/*SmartDashboard.putNumber("pdpChannel0", pdp.getCurrent(0));
		SmartDashboard.putNumber("pdpChannel1", pdp.getCurrent(1));
		SmartDashboard.putNumber("pdpChannel2", pdp.getCurrent(2));
		SmartDashboard.putNumber("pdpChannel3", pdp.getCurrent(3));
		SmartDashboard.putNumber("pdpChannel4", pdp.getCurrent(4));
		SmartDashboard.putNumber("pdpChannel5", pdp.getCurrent(5));
		SmartDashboard.putNumber("pdpChannel6", pdp.getCurrent(6));
		SmartDashboard.putNumber("pdpChannel7", pdp.getCurrent(7));
		SmartDashboard.putNumber("pdpChannel8", pdp.getCurrent(8));
		SmartDashboard.putNumber("pdpChannel9", pdp.getCurrent(9));
		SmartDashboard.putNumber("pdpChannel10", pdp.getCurrent(10));
		SmartDashboard.putNumber("pdpChannel11", pdp.getCurrent(11));
		SmartDashboard.putNumber("pdpChannel12", pdp.getCurrent(12));
		SmartDashboard.putNumber("pdpChannel13", pdp.getCurrent(13));
		SmartDashboard.putNumber("pdpChannel14", pdp.getCurrent(14));
		SmartDashboard.putNumber("pdpChannel15", pdp.getCurrent(15));*/
		
		SmartDashboard.putNumber("FLPower", fl.getPower());
		SmartDashboard.putNumber("MLPower", ml.getPower());
		SmartDashboard.putNumber("BLPower", bl.getPower());
		SmartDashboard.putNumber("FRPower", fr.getPower());
		SmartDashboard.putNumber("MRPower", mr.getPower());
		SmartDashboard.putNumber("BRPower", br.getPower());
		SmartDashboard.putNumber("IntakeLeftPower", intakeLeftMotor.getPower());
		SmartDashboard.putNumber("IntakeRightPower", intakeRightMotor.getPower());
		SmartDashboard.putNumber("ActuationPower", actuationMotor.getPower());
		SmartDashboard.putNumber("IntakeLeftPower", intakeLeftMotor.getPower());
		SmartDashboard.putNumber("WindowPower", windowMotor.getPower());
		
		
		/*for (int i = 0; i < 16; i++) {
			SmartDashboard.putNumber("pdpChannel" + Integer.toString(i), (pdp.getCurrent(i) / pdp.getVoltage()));
		}*/
		SmartDashboard.putNumber("Total", pdp.getTotalPower());
		
	}
	
	public static void writelogfile(){
		try {
			PrintWriter writer = new PrintWriter("PDP_Info.txt", "UTF-8");
			
			writer.println("pdpChannel0: " + pdp.getCurrent(0) + "/npdpChannel1: " + pdp.getCurrent(1) + "/npdpChannel2: " + pdp.getCurrent(2)
            + "/npdpChannel3: " + pdp.getCurrent(3) + "/npdpChannel4: " + pdp.getCurrent(4) + "/npdpChannel5: " + pdp.getCurrent(5)
            + "/npdpChannel6: " + pdp.getCurrent(6) + "/npdpChannel7: " + pdp.getCurrent(7) + "/npdpChannel8: " + pdp.getCurrent(8)
            + "/npdpChannel9: " + pdp.getCurrent(9) + "/npdpChannel10: " + pdp.getCurrent(10) + "/npdpChannel11: " + pdp.getCurrent(11)
            + "/npdpChannel12: " + pdp.getCurrent(12) + "/npdpChannel13: " + pdp.getCurrent(13) + "/npdpChannel14: " + pdp.getCurrent(14)
            + "/npdpChannel15: " + pdp.getCurrent(15) + "/npdpTotal: " + pdp.getTotalCurrent());
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 /*public static void writelogfile() {
	        BufferedWriter writer = null;
	        try {
	            //create a temporary file
	            String timeLog = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
	            File logFile = new File(timeLog);

	            // This will output the full path where the file will be written to...
	            System.out.println(logFile.getCanonicalPath());

	            writer = new BufferedWriter(new FileWriter(logFile));
	            writer.write("pdpChannel0: " + pdp.getCurrent(0) + "/npdpChannel1: " + pdp.getCurrent(1) + "/npdpChannel2: " + pdp.getCurrent(2)
	            + "/npdpChannel3: " + pdp.getCurrent(3) + "/npdpChannel4: " + pdp.getCurrent(4) + "/npdpChannel5: " + pdp.getCurrent(5)
	            + "/npdpChannel6: " + pdp.getCurrent(6) + "/npdpChannel7: " + pdp.getCurrent(7) + "/npdpChannel8: " + pdp.getCurrent(8)
	            + "/npdpChannel9: " + pdp.getCurrent(9) + "/npdpChannel10: " + pdp.getCurrent(10) + "/npdpChannel11: " + pdp.getCurrent(11)
	            + "/npdpChannel12: " + pdp.getCurrent(12) + "/npdpChannel13: " + pdp.getCurrent(13) + "/npdpChannel14: " + pdp.getCurrent(14)
	            + "/npdpChannel15: " + pdp.getCurrent(15) + "/npdpTotal: " + pdp.getTotalCurrent());
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                // Close the writer regardless of what happens...
	                writer.close();
	            } catch (Exception e) {
	            }
	        }
	    }*/

}