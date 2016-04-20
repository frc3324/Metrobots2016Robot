package org.metrobots;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MetroSpeedController extends Talon {
	private int pdpChannel;
	private PowerDistributionPanel pdp = new PowerDistributionPanel();

	public MetroSpeedController(int pwm, int pdpChannel_) {
		super(pwm);
		// TODO Auto-generated constructor stub
		pdpChannel = pdpChannel_;
		
	}
	
	@Override
	public void set(double value) {
		double current = pdp.getCurrent(pdpChannel);
		//SmartDashboard.putNumber("Current Amperage", current);
		
		if (pdpChannel >= 35) {
			value = value * (1-((current-37.5)/2.5));
			//set
			super.set(value);
		} else {
			super.set(value);
		}
	}
	
	public double getPower() {
		return pdp.getCurrent(pdpChannel) * Math.abs(super.get()) * pdp.getVoltage();
	}
	
}
