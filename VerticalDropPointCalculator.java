package aleksanderbraksator.trajectory;

public class VerticalDropPointCalculator implements ITrajectoryPointCalculator {

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
		TrajectoryPoint velocity = new TrajectoryPoint();
		velocity.velocityX = velocityX;
		velocity.velocityY = velocityY;
		return velocity;
	}

	private double calculateVelocityX(
			TrajectoryPointCalculatorInputParameters varyingParams) {
		return 0.0d;
	}

	private double calculateVelocityY(
			TrajectoryPointCalculatorInputParameters varyingParams) {
			double initialVelocity = varyingParams.getVelocity();
			double initialVelocityY = varyingParams.velocityY;
			double Cd = varyingParams.getDragCoefficient(constantParams.objectDragCoefficient);
			double dVy = ((k*Cd*Math.pow(initialVelocity, 2))- Constants.GRAVITY)*constantParams.timeInterval;
			double velocityY = initialVelocityY + dVy;
			return velocityY;
	}
	
	



}
