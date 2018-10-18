package driver;

import java.io.File;
import io.Parser;
import io.Serializer;
import data.*;
import handler.AutoException;
import handler.ExceptionIDs;

public class Core {
	private static Parser fileInputParser;
	public static File baseInputFile;

	public static void main(String[] args) {
		try {
			baseInputFile = new File(Core.class.getResource("Ford ZTW.txt").toURI());
			if (baseInputFile.exists()) {
				fileInputParser = new Parser(baseInputFile); // My parser class is not static, and as such, an instance
																// is required for any parsing tasks to occur.
				Auto vehicleFromParser = fileInputParser.Parse();
				vehicleFromParser.LogVehicle(); // LogVehicle() dumps the contents of all instance variables of the
												// parent object, as well as all instance variables of its' class
												// objects.
				Serializer ser = new Serializer(vehicleFromParser);
				ser.Serialize();
				ser = new Serializer(ser.getStorageLocation());
				ser.deSerialize();
				new AutoException(ExceptionIDs.NOEXCEPTION).fix();
				} else {
				System.out.printf("Failed to open file %s", baseInputFile.getAbsolutePath());
			}
		} catch (Exception e) {
		}
	}
}
