package aleksanderbraksator.trajectory;

public class TrajectoryPointCalculatorInputParameters {

	double spinVelocity = 0.0d;
	double velocityX = 0.0d;
	double velocityY = 0.0d;
	
	
	public double getVelocity() {
		return Math.sqrt(Math.pow(velocityX, 2.0d) + Math.pow(velocityY, 2.0d));
	}
	
	public double getDragCoefficient(double initialDragCoeficient) {
		double Cd = CoefficientEvaluator.getDragCoefficient(initialDragCoeficient, getVelocity(), spinVelocity);
		return Cd;
	}

	@Override
	public String toString() {
		return "TrajectoryPointCalculationInputParameters [spinVelocity=" + spinVelocity
				+ ", velocityX=" + velocityX + ", velocityY=" + velocityY
				+ ", getVelocity()=" + getVelocity() + "]";
	}
	
	
	
	
}
