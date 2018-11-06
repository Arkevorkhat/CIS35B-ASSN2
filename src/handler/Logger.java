package handler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Debug logging class to allow other classes to efficiently put data out to a logfile.
 *
 */
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

	/**
	 * Defines a short list of Log levels that other classes can use to define what level of
	 * verbosity logging should occur with.
	 */
	public static enum LogLevel {
		Silent, Quiet, Verbose, Debug;
	}

	/**
	 * @param input
	 *            {@link java.lang.String} data to be appended to the log file.
	 * @param logLevel
	 *            log level of the message, if it is lower than the logger's log level, the
	 *            message won't be shown.
	 */
	public static synchronized void Log(String input, LogLevel logLevel){
		if (logLevel.ordinal() >= level.ordinal()) {
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
