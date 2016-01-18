package org.metrobots;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.SpeedController;

public class DriveTrain {

	public static SpeedController fl, ml, bl, fr, mr, br;
	public static Encoder leftEncoder, rightEncoder;
	public static Gyro gyro;
	public static DoubleSolenoid shift;

	public static double gyroHoldSensitivity;

	public static boolean isFieldOriented, isHoldAngle, isSlowDrive, isHighGear;

	public static double targetAngle, targetTilt;

	public static int flInv, mlInv, blInv, frInv, mrInv, brInv;

	public static int driveType;

	public static final int TANK_DRIVE = 0;
	public static final int MECANUM_DRIVE = 1;
	public static final int SHIFTING_TANK_DRIVE = 2;
	public static final int SIX_MOTOR_TANK_DRIVE = 3;

	public DriveTrain(SpeedController fl_, SpeedController bl_,
					SpeedController fr_, SpeedController br_,
					Encoder leftEncoder_, Encoder rightEncoder_, Gyro gyro_,
					DoubleSolenoid shift_) {
		fl = fl_;
		bl = bl_;
		fr = fr_;
		br = br_;

		leftEncoder = leftEncoder_;
		rightEncoder = rightEncoder_;

		gyro = gyro_;

		shift = shift_;

		isFieldOriented = false;
		isHoldAngle = false;
		gyroHoldSensitivity = 20;

		targetAngle = 0;

		frInv = 1;
		brInv = 1;
		flInv = 1;
		blInv = 1;

		driveType = 0;
	}

	public DriveTrain(SpeedController fl_, SpeedController ml_,
					SpeedController bl_, SpeedController fr_,
					SpeedController mr_, SpeedController br_,
					Encoder leftEncoder_, Encoder rightEncoder_, Gyro gyro_,
					DoubleSolenoid shift_) {
		fl = fl_;
		ml = ml_;
		bl = bl_;
		fr = fr_;
		mr = mr_;
		br = br_;

		leftEncoder = leftEncoder_;
		rightEncoder = rightEncoder_;

		gyro = gyro_;

		shift = shift_;

		isFieldOriented = false;
		isHoldAngle = false;
		gyroHoldSensitivity = 20;

		targetAngle = 0;

		frInv = 1;
		mrInv = 1;
		brInv = 1;
		flInv = 1;
		mlInv = 1;
		blInv = 1;

		driveType = 0;
	}

	public void tankDrive(double left, double right) {
		fl.set(left * frInv);
		bl.set(left * brInv);
		fr.set(right * flInv);
		br.set(right * blInv);
	}

	public void sixMotorTankDrive(double left, double right) {
		if (Math.abs(left - right) < 0.075) {
			double lr = left + right / 2;
			left = lr;
			right = lr;
		}

		if (isHoldAngle) {
			left -= (targetAngle - gyro.getAngle()) / gyroHoldSensitivity;
			right += (targetAngle - gyro.getAngle()) / gyroHoldSensitivity;
		}

		fl.set(left * flInv);
		ml.set(left * mlInv);
		bl.set(left * blInv);
		fr.set(right * frInv);
		mr.set(right * mrInv);
		br.set(right * brInv);
	}

	public void mecanumDrive(double y, double x, double turn) {

		if (isFieldOriented) {

			double gAngle = gyro.getAngle();
			double cosA = Math.cos(gAngle * 3.1415926535 / 180);
			double sinA = Math.sin(gAngle * 3.1415926535 / 180);

			x = (x * cosA) - (y * sinA);
			y = (x * sinA) + (y * cosA);
		}

		if (isHoldAngle) {
			turn = turn + ((targetAngle - gyro.getAngle())
							/ gyroHoldSensitivity);
		}

		turn = turn / -3;
		if (isSlowDrive) {
			fl.set((y + x + turn) * flInv * 0.6);
			bl.set((y - x + turn) * blInv * 0.6);
			fr.set((y - x - turn) * frInv * 0.6);
			br.set((y + x - turn) * brInv * 0.6);
		} else {
			fl.set((y + x + turn) * flInv);
			bl.set((y - x + turn) * blInv);
			fr.set((y - x - turn) * frInv);
			br.set((y + x - turn) * brInv);
		}

	}

	public void shiftingTankDrive(double left, double right) {
		if (Math.abs(left - right) < 0.075) {
			double lr = left + right / 2;
			left = lr;
			right = lr;
		}

		if (isHoldAngle) {
			left -= (targetAngle - gyro.getAngle()) / gyroHoldSensitivity;
			right += (targetAngle - gyro.getAngle()) / gyroHoldSensitivity;
		}

		fl.set(left * flInv);
		bl.set(left * blInv);
		fr.set(right * frInv);
		br.set(right * brInv);

	}

	public void setHighGear() {
		isHighGear = true;
	}

	public void setLowGear() {
		isHighGear = false;
	}

	public void setInvertedMotors(boolean fl, boolean bl, boolean fr,
					boolean br) {
		flInv = fl ? -1 : 1;
		blInv = bl ? -1 : 1;
		frInv = fr ? -1 : 1;
		brInv = br ? -1 : 1;
	}

	public void setSlowDrive(boolean val) {
		isSlowDrive = val;
	}

	public boolean isSlowDrive() {
		return isSlowDrive;
	}

	public void setHoldAngle(boolean val) {
		isHoldAngle = val;
	}

	public boolean isHoldAngle() {
		return isHoldAngle;
	}

	public void setFieldOriented(boolean val) {
		isFieldOriented = val;
	}

	public boolean isFieldOriented() {
		return isFieldOriented;
	}

	public int getEncoder(Encoder e) {
		return e.get();
	}

	public void resetEncoders() {
		leftEncoder.reset();
		rightEncoder.reset();

	}

	public double getGyro() {
		return gyro.getAngle();
	}

	public void resetGyro() {
		gyro.reset();
	}

	public void setGyroHoldSensitivity(double value) {
		gyroHoldSensitivity = value;
	}

	public void setTargetAngle(double angle) {
		targetAngle = angle;
	}

	public void stop() {
		fl.set(0);
		bl.set(0);
		fr.set(0);
		br.set(0);
	}

	public void setDriveType(int type) {
		driveType = type;
	}

	public String getDriveType() {
		if (driveType == 0) {
			return "Tank Drive";
		} else if (driveType == 1) {
			return "Mecanum Drive";
		} else if (driveType == 2) {
			return "Shifting Tank Drive";
		} else if (driveType == 3) {
			return "Six Motor Tank Drive";
		} else {
			return "Stopped";
		}
	}

}
