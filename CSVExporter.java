package aleksanderbraksator.trajectory.dataexport;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import aleksanderbraksator.trajectory.TrajectoryPoint;
import au.com.bytecode.opencsv.CSVWriter;

public class CSVExporter implements IDataExporter {

	@Override
	public String export(List<TrajectoryPoint> trajectoryPoints) {

		StringWriter sw = new StringWriter();
		CSVWriter writer = new CSVWriter(sw, '\t');
		try {
			String[] entries = new String[8];
			entries[0] = "elapsedTime";
			entries[1] = "velocityX";
			entries[2] = "velocityY";
			entries[3] = "velocityZ";
			entries[4] = "angle";
			entries[5] = "positionX";
			entries[6] = "positionY";
			entries[7] = "positionZ";
			writer.writeNext(entries);
			for (TrajectoryPoint trajectoryPoint : trajectoryPoints) {
				entries = new String[8];
				entries[0] = String.format("%f", trajectoryPoint.getElapsedTime());
				entries[1] = String.format("%f", trajectoryPoint.getVelocityX());
				entries[2] = String.format("%f", trajectoryPoint.getVelocityY());
				entries[3] = String.format("%f", trajectoryPoint.getVelocityZ());
				entries[4] = String.format("%f", trajectoryPoint.getAngleInDegrees());
				entries[5] = String.format("%f", trajectoryPoint.getPositionX());
				entries[6] = String.format("%f", trajectoryPoint.getPositionY());
				entries[7] = String.format("%f", trajectoryPoint.getPositionZ());
				writer.writeNext(entries);
			}
		} finally {
			try {
				writer.flush();
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return sw.toString();
	}

}
