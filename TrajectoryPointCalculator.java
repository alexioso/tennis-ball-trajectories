package aleksanderbraksator.trajectory;

public class TrajectoryPointCalculator implements ITrajectoryPointCalculator {

	private InputParameters constantParams = null;
	private double k = 0;

	
	@Override
	public void init(InputParameters constantParams) {
		this.constantParams = constantParams;
		//precalculate k coefficient
		this.k = constantParams.getK();
	}

	@Override
	public TrajectoryPoint calculatePoint(
			TrajectoryPointCalculatorInputParameters varyingParams) {
		double velocityX = calculateVelocityX(varyingParams);
		double velocityY = calculateVelocityY(varyingParams);
		TrajectoryPoint trajectoryPoint = new TrajectoryPoint();
		trajectoryPoint.velocityX = velocityX;
		trajectoryPoint.velocityY = velocityY;
		return trajectoryPoint;
	}

	private double calculateVelocityX(
			TrajectoryPointCalculatorInputParameters varyingParams) {
		double initialVelocity = varyingParams.getVelocity();
		double initialVelocityX = varyingParams.velocityX;
		double initialVelocityY = varyingParams.velocityY;
		double Cd = varyingParams.getDragCoefficient(constantParams.objectDragCoefficient);
		double Cl = CoefficientEvaluator.getLiftCoefficient(initialVelocity, varyingParams.spinVelocity)*constantParams.spinDirection;
		double dVx = (-k*initialVelocity*((Cd*initialVelocityX) + (Cl*initialVelocityY)))*constantParams.timeInterval;
		double velocityX = initialVelocityX + dVx;
		return velocityX;
	}

	private double calculateVelocityY(
			TrajectoryPointCalculatorInputParameters varyingParams) {
			double initialVelocity = varyingParams.getVelocity();
			double initialVelocityX = varyingParams.velocityX;
			double initialVelocityY = varyingParams.velocityY;
			double Cd = varyingParams.getDragCoefficient(constantParams.objectDragCoefficient);
			double Cl = CoefficientEvaluator.getLiftCoefficient(initialVelocity, varyingParams.spinVelocity)*constantParams.spinDirection;
			double dVy = ((k*initialVelocity*((Cl*initialVelocityX) - (Cd*initialVelocityY))) - Constants.GRAVITY)*constantParams.timeInterval;
			double velocityY = initialVelocityY + dVy;
			return velocityY;
	}
	
}
