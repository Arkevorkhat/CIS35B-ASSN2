package handler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;

public class Logger {

	public static final LogLevel	level	= LogLevel.Debug;
	public static File				logFile;
	static {
		try {
			logFile = new File(Logger.class.getResource("Log.txt").toURI());
		}
		catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	public static enum LogLevel {
		Quiet, Verbose, Debug;
	}

	public static synchronized void Log(String input, LogLevel ll){
		if (ll.ordinal() >= level.ordinal()) {
			try {
				BufferedWriter logWriter = new BufferedWriter(new FileWriter(logFile));
				logWriter.append(input);
				logWriter.close();
			}
			catch (IOException e) {
				System.err.println("Logger initialization failed.");
			}
		}
	}
}
