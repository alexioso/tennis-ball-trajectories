package aleksanderbraksator.trajectory.dataexport;

import java.io.StringWriter;
import java.util.List;

import aleksanderbraksator.trajectory.TrajectoryPoint;


public class TextExporter implements IDataExporter {

	@Override
	public String export(List<TrajectoryPoint> trajectoryPoints) {
		StringWriter sw = new StringWriter();
		for(TrajectoryPoint trajectoryPoint : trajectoryPoints) {
			sw.write(String.format("time=%f secs, Vx=%f mps, Vy=%f mps, angle=%f degrees, posX=%f meters, posY=%f meters\n", trajectoryPoint.getElapsedTime() , trajectoryPoint.getVelocityX(), trajectoryPoint.getVelocityY(), trajectoryPoint.getAngleInDegrees(), trajectoryPoint.getPositionX(), trajectoryPoint.getPositionY()));
		}
		return sw.toString();
	}

}
