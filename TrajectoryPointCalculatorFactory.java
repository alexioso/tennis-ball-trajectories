package aleksanderbraksator.trajectory;



public class TrajectoryPointCalculatorFactory {
	
	public enum CalculationMode {
		TRAJECTORY,
		VERTICAL_DROP
	}
	
	private static TrajectoryPointCalculatorFactory theInstance;
	
	
	public static TrajectoryPointCalculatorFactory getInstance() {
		if(theInstance == null) {
			theInstance = new TrajectoryPointCalculatorFactory();
		}
		return theInstance;
	}
	
	public ITrajectoryPointCalculator getCalculator(CalculationMode mode) {
		if(mode == CalculationMode.TRAJECTORY) {
			return new TrajectoryPointCalculator();
		}
		return new VerticalDropPointCalculator();
	}
	
}
