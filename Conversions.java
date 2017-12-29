package aleksanderbraksator.trajectory;

public class Conversions {

	public static double KPM_IN_MPH = 1.60934d;
	public static double RADIANT_PER_SECOND = 60/2/Math.PI;

	public static double toRadiansPerSecond(double rpms) {
		double radiansPerSecond = rpms/RADIANT_PER_SECOND;
		return radiansPerSecond;
	}
	
	public static double toMetersPerSecond(double mphs) {
		double kphs = mphs*KPM_IN_MPH;
		return kphs*1000d/3600d;
	}
	
}
