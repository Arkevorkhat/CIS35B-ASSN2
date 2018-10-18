package handler;

import java.util.HashMap;

public class Handler {
	private static HashMap<Integer, Fix> exceptionFixes = new HashMap<Integer, Fix>();

	protected static void addFix(int errorCode, Fix repairCode) {
		exceptionFixes.put(errorCode, repairCode);
	}

	public static void runFix(int code) {
		if (exceptionFixes.containsKey(code)) {
			exceptionFixes.get(code).fix();
		} else {
			return;
		}
	}
}
