package aleksanderbraksator.trajectory;

public class InputParameters {
	
	public static double TOPSPIN = -1.0d;
	public static double BACKSPIN = 1.0d;
	
	public double objectMass = 0.0d; //kg
	public double objectDiameter = 0.0d; //meters
	public double objectDragCoefficient = 0.55d; //brand new tennis ball
	public double timeInterval = 0.1d; //sec
	public double spin = 0.0d; //rmps
	public double spinDirection = TOPSPIN;
	public double initialHeight = 0.0d; //meters
	public double initialDistance = 0.0d;//meters
	public double initialVelocityX = 0.0d;//meters/sec
	public double initialVelocityY = 0.0d;//meters/sec
	public boolean bDebug = false;
	
	public double getK() {
		double k = CoefficientEvaluator.getK(objectDiameter,objectMass);
		return k;
	}
	
	/**
	 * Calculate spin velocity
	 * @return spin velocity in meters per second
	 */
	public double getSpinVelocity() {
		double spinVelocity = Conversions.toRadiansPerSecond(spin)*objectDiameter/2; //in meters per second	
		return spinVelocity;
	}

	@Override
	public String toString() {
		return "InputParameters [\nobjectMass=" + objectMass
				+ "\nobjectDiameter=" + objectDiameter
				+ "\nobjectDragCoefficient=" + objectDragCoefficient
				+ "\ntimeInterval=" + timeInterval + "\nspinDirection="
				+ spinDirection + "\ninitialHeight=" + initialHeight
				+ "\ninitialDistance=" + initialDistance
				+ "\nspin=" + spin
				+ "\ninitialVelocityX=" + initialVelocityX
				+ "\ninitialVelocityY=" + initialVelocityY + "\nbDebug="
				+ bDebug + "\ngetK()=" + getK() + "\n]";
	}
	
	
	
}
