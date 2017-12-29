package aleksanderbraksator.trajectory.dataexport;

import java.util.List;

import aleksanderbraksator.trajectory.TrajectoryPoint;


public interface IDataExporter {

	public String export(List<TrajectoryPoint> trajectoryPoints);
	
}
