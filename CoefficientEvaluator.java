package aleksanderbraksator.trajectory;

public class CoefficientEvaluator {

	
	
	
	/**
	 * Cd = initialDragCoefficient + 1/[22.5 + 4.2(v/vspin)^2.5]^0.4
	 * @param initialDragCoefficient
	 * @param ballVelocity
	 * @param ballSpin
	 * @return
	 */
	public static double getDragCoefficient(double initialDragCoefficient, double ballVelocity, double ballSpin) {

		double dragCoefficient = initialDragCoefficient + 1d/(22.5d + 4.2d*Math.pow(Math.pow((ballVelocity/ballSpin), 2.5d), 0.4d));
		return dragCoefficient;
	}
	
	
	/**
	 * k = d.π.R2/(2m)
	 * 
	 * @param ballDiameter [m]
	 * @param ballMass [kg]
	 * @return
	 */
	public static double getK(double ballDiameter, double ballMass) {
		double k = Constants.AIR_DENSITY*Math.PI*Math.pow((ballDiameter/2),2)/(2*ballMass);
		return k;
	}
	
	
	/**
	 * Cl = Rω (ball radius[kg] all * bangular speed[radiants/sec])
	 * @param velocityBall [m/s]
	 * @param velocityBallSpin [m/s]
	 * @return lift coefficient
	 */
	public static double getLiftCoefficient(double velocityBall, double velocityBallSpin) {
		double liftCoefficient = 1/(2 + (velocityBall/velocityBallSpin));
		return liftCoefficient;
	}
	
	
	
	
}
