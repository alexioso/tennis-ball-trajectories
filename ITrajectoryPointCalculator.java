package aleksanderbraksator.trajectory;

public interface ITrajectoryPointCalculator {
	
	
	public void init(InputParameters constantParams);
	public TrajectoryPoint calculatePoint(TrajectoryPointCalculatorInputParameters varryingParams);

}
