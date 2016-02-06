package org.metrobots;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class IntakeLauncher extends Subsystem {
	
	public static SpeedController left, right, actuation;
	public static DoubleSolenoid piston;
	
	public IntakeLauncher(SpeedController left_, SpeedController right_, SpeedController actuation_, DoubleSolenoid piston_) {
		
		left = left_;
		right = right_;
		actuation = actuation_;
		piston = piston_;
		
	}
	
	public void intake(double speed) {
		left.set(speed);
		right.set(-speed);
	}
	
	public void actuateAngle(double speed) {
		actuation.set(speed);
	}
	
	public void actuatePiston(double time) {
		if(time != 0){
			if((time/ 1000000) < 2)
				piston.set(DoubleSolenoid.Value.kForward);
			else if((time/ 1000000) < 4)
				piston.set(DoubleSolenoid.Value.kReverse);
			else
				piston.set(DoubleSolenoid.Value.kOff);
		}
		else{
			piston.set(DoubleSolenoid.Value.kOff);
		}

	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}