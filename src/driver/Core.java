package driver;

import java.io.File;

import adapter.BuildAuto;

public class Core {

	public static boolean	DEBUG;
	public static File		baseInputFile;

	public static void main(String[] args){
		try {
			if (args.length > 0) {
				if (args[0].equals("-d")) {
					DEBUG = true;
				}
				else if (args[0].equals("-s")) {
					DEBUG = false;
				}
			}
			baseInputFile = new File(Core.class.getResource("Ford ZTW.txt").toURI());
			if (baseInputFile.exists()) {
				BuildAuto b = new BuildAuto();
				b.BuildAuto(baseInputFile.getAbsolutePath());
			}
			else {
				System.out.printf("Failed to open file %s", baseInputFile.getAbsolutePath());
			}
		}
		catch (Exception e) {}
	}
}
