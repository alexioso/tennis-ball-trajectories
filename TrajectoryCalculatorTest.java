package aleksanderbraksator.trajectory;

import java.util.List;

import junit.framework.TestCase;
import aleksanderbraksator.trajectory.TrajectoryPointCalculatorFactory.CalculationMode;
import aleksanderbraksator.trajectory.dataexport.DataExporterFactory;
import aleksanderbraksator.trajectory.dataexport.IDataExporter;

public class TrajectoryCalculatorTest extends TestCase {

	public static void main(String[] args) {
		TrajectoryCalculatorTest test = new TrajectoryCalculatorTest();
		test.testTopSpinTrajectory();
		test.testVerticalDropTrajectory();
		test.testVerticalDrop();
	}
	
	/**
	 * This test is for testing a two dimensional trajectory of a tennis ball
	 */
	public void testTopSpinTrajectory() {
		InputParameters inputParams = new InputParameters();
		inputParams.objectDiameter = 65.0d/1000.0d; //tennis ball diameter in meters
		inputParams.objectMass = 57.0d/1000.0d; //tennis ball mass in kilograms;
		inputParams.timeInterval = 0.05d; //seconds
		inputParams.spinDirection = InputParameters.TOPSPIN;
		inputParams.objectDragCoefficient = 0.55d;  //brand new tennis ball;
		//inputParams.dragCoefficient = 0.5d;  //used tennis ball;
		inputParams.initialHeight =  0.3d; //meters;
		inputParams.spin = 3000.0d; //ball spin in rpms

		inputParams.initialVelocityX = 25.0d; //meters per second forward
		inputParams.initialVelocityY = 7.0d; //meters per second upward
		inputParams.bDebug = true;

		System.out.println("\nTrajectory Points:"); 
		TrajectoryCalculator calculator = new TrajectoryCalculator(inputParams, CalculationMode.TRAJECTORY);
		List<TrajectoryPoint> trajectoryPoints = calculator.calculateTrajectory();
		IDataExporter exporter = DataExporterFactory.getInstance().getExporter(DataExporterFactory.ExportType.Text);
		System.out.println(exporter.export(trajectoryPoints)); 
	}
	
	/**
	 * Sanity test to match the results from the book by Rod Cross  Chapter: Ball Trajectories, eq. (42.a3)
	 */
	public void testVerticalDropTrajectory() {
		InputParameters inputParams = new InputParameters();
		inputParams.objectDiameter = 65.0d/1000.0d; //tennis ball diameter in meters
		inputParams.objectMass = 57.0d/1000.0d; //tennis ball mass in kilograms;
		inputParams.timeInterval = 0.01d; //seconds
		inputParams.objectDragCoefficient = 0.5d;  //used tennis ball;
		inputParams.initialHeight =  30.0d; //meters;
		inputParams.initialVelocityY = -15.0d; //meters per second downward
		inputParams.bDebug = true;
		
		System.out.println("\nVertical Drop Trajectory Points:"); 
		TrajectoryCalculator calculator = new TrajectoryCalculator(inputParams, CalculationMode.TRAJECTORY);
		List<TrajectoryPoint> trajectoryPoints = calculator.calculateTrajectory();
		IDataExporter exporter = DataExporterFactory.getInstance().getExporter(DataExporterFactory.ExportType.Text);
		System.out.println(exporter.export(trajectoryPoints)); 
	}
	
	/**
	 * This test should produce results identical to testVerticalDropTrajectory
	 * using a special case equations for vertical drop trajectory calculation
	 */
	public void testVerticalDrop() {
		InputParameters inputParams = new InputParameters();
		inputParams.objectDiameter = 65.0d/1000.0d; //tennis ball diameter in meters
		inputParams.objectMass = 57.0d/1000.0d; //tennis ball mass in kilograms;
		inputParams.timeInterval = 0.01d; //seconds
		inputParams.objectDragCoefficient = 0.5d;  //used tennis ball;
		inputParams.initialHeight =  30.0d; //m;
		inputParams.initialVelocityY = -15.0d; //meters per second downward
		inputParams.bDebug = true;
		
		System.out.println("\nVertical Drop Points:"); 
		TrajectoryCalculator calculator = new TrajectoryCalculator(inputParams,  CalculationMode.VERTICAL_DROP);
		List<TrajectoryPoint> trajectoryPoints = calculator.calculateTrajectory();
		IDataExporter exporter = DataExporterFactory.getInstance().getExporter(DataExporterFactory.ExportType.Text);
		System.out.println(exporter.export(trajectoryPoints)); 
	}

	
	public void testCoefficients() {
		
		double velocityBall_mph = 90.0d;
		double velocitySpin_RPMs = 500.0d;
		double ballDiameter_m = 65.0d/1000.0d;
		double ballMass_kg = 57.0d/1000.0d;

		double k = CoefficientEvaluator.getK(ballDiameter_m, ballMass_kg);
		System.out.println("k: " + k);
		
		double dragCoefficient = CoefficientEvaluator.getDragCoefficient(0.55d, Conversions.toMetersPerSecond(velocityBall_mph), Conversions.toRadiansPerSecond(velocitySpin_RPMs));
		System.out.println("dragCoefficient: " + dragCoefficient);
		
		double liftCoefficient = CoefficientEvaluator.getLiftCoefficient(Conversions.toMetersPerSecond(velocityBall_mph), Conversions.toRadiansPerSecond(velocitySpin_RPMs));
		System.out.println("liftCoefficient: " + liftCoefficient);
	}
	
}
