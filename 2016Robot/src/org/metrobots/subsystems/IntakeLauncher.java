package org.metrobots.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class IntakeLauncher extends Subsystem {
	public static SpeedController left, right, actuation;
	public static DoubleSolenoid piston;
	public static AnalogInput pot;
	private int target;
	
	
	public IntakeLauncher(SpeedController left_, SpeedController right_, SpeedController actuation_, DoubleSolenoid piston_, AnalogInput pot_) {
		
		left = left_;
		right = right_;
		actuation = actuation_;
		piston = piston_;
		pot = pot_;
		
	}
	
	public void intake(double speed) {
		left.set(-speed); 
		right.set(speed);
	}
	
	public void actuateSpeed(double speed) {
		//if ((speed > 0 && pot.getValue() > 1050) || (speed < 0 && pot.getValue() < 640)) {
		//	actuation.set(0);
		//} else {
			actuation.set(speed);
		//}
	}
	
	public void actuateAngle(int target) {
		actuation.set((pot.getValue() - target)/410);	
	}
	
	public void actuatePiston(double time) {
		/*if(time != 0){
			if((time/ 1000000) < 2)
				this.pistonForward();
			else if((time/ 1000000) < 4)
				this.pistonReverse();
			else
				this.pistonOff();
		}
		else{
			piston.set(DoubleSolenoid.Value.kOff);
		}*/
                              
	}

	public void pistonForward() {
		piston.set(DoubleSolenoid.Value.kForward);
	}
	
	public void pistonReverse() {
		piston.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void pistonOff() {
		piston.set(DoubleSolenoid.Value.kOff);
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
