package aleksanderbraksator.trajectory;

import java.util.ArrayList;
import java.util.List;

import aleksanderbraksator.trajectory.TrajectoryPointCalculatorFactory.CalculationMode;

public class TrajectoryCalculator {


	private InputParameters inputParams = null;
	private CalculationMode calcMode = null;
	
	/**
	 * Create an instance of trajectory calculator 
	 * @param inputParams - set of initial Input parameters staying constant throughout the entire calculation 
	 * @param calcMode - One of the 2 modes : CalculationMode.MODE_TRAJECTORY or CalculationMode.MODE_VERTICAL_DROP
	 */
	public TrajectoryCalculator(InputParameters inputParams, CalculationMode calcMode) {
		this.inputParams = inputParams;
		this.calcMode = calcMode;
	}

	/**
	 * 
	 * @return
	 */
	public List<TrajectoryPoint> calculateTrajectory() {
		ITrajectoryPointCalculator calculator = TrajectoryPointCalculatorFactory.getInstance().getCalculator(calcMode); 
		calculator.init(inputParams);
		double elapsedTime = 0.0d;
		double posY = inputParams.initialHeight;
		double posX = inputParams.initialDistance;
		//create a single mutating object to keep the varying input parameters
		TrajectoryPointCalculatorInputParameters varyingParams = new TrajectoryPointCalculatorInputParameters();
		varyingParams.spinVelocity = inputParams.getSpinVelocity();
		varyingParams.velocityX = inputParams.initialVelocityX;
		varyingParams.velocityY = inputParams.initialVelocityY;
		List<TrajectoryPoint> trajectoryPoints = new ArrayList<TrajectoryPoint>();
		//create initial point by using the input param values
		TrajectoryPoint initialPoint = new TrajectoryPoint();
		initialPoint.timeInterval = inputParams.timeInterval;
		initialPoint.positionX = inputParams.initialDistance;
		initialPoint.positionY = inputParams.initialHeight;
		initialPoint.velocityX = inputParams.initialVelocityX;
		initialPoint.velocityY = inputParams.initialVelocityY;
		//add initial point to be the frist in our list
		trajectoryPoints.add(initialPoint);
		while(true) {
			TrajectoryPoint trajectoryPoint = calculator.calculatePoint(varyingParams);
			//add newly calculated trajectory point 
			trajectoryPoints.add(trajectoryPoint);
			elapsedTime += inputParams.timeInterval;
			trajectoryPoint.elapsedTime = elapsedTime;
			trajectoryPoint.timeInterval = inputParams.timeInterval;
			posY += trajectoryPoint.getDistanceYInterval();
			posX += trajectoryPoint.getDistanceXInterval();
			trajectoryPoint.positionY += posY;
			trajectoryPoint.positionX += posX;
			trajectoryPoint.timeInterval = inputParams.timeInterval;
			varyingParams.velocityX = trajectoryPoint.velocityX;
			varyingParams.velocityY = trajectoryPoint.velocityY;
			//stop when the object hits the ground at posY = 0
			if(posY <= 0) {
				break;
			}
		}
		return trajectoryPoints;
		
	}
	

}
