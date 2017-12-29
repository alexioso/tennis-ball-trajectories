package aleksanderbraksator.trajectory.dataexport;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataExporterFactory {

	public enum ExportType {
		CSV,
		Text
	}

	public static SimpleDateFormat format = new SimpleDateFormat(
			"MM-dd-yyyy_HH-mm-ss");

	private static DataExporterFactory theInstance;
	
	
	public static DataExporterFactory getInstance() {
		if(theInstance == null) {
			theInstance = new DataExporterFactory();
		}
		return theInstance;
	}
	
	public IDataExporter getExporter(ExportType exporterType) {
		if(exporterType == ExportType.CSV) {
			return new CSVExporter();
		} else{
			return new TextExporter();
		}
	}
	
	public static void writeToFile(String str) {
		FileWriter fw = null;
		try {
			fw = new FileWriter("trajectory_" + format.format(new Date())
					+ ".csv");
			fw.write(str);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fw != null) {
					fw.flush();
					fw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	
	
}
