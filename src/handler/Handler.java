package handler;

import java.util.HashMap;

public class Handler {
	private HashMap<Integer, Fix> exceptionFixes = new HashMap<Integer, Fix>();

	protected void addFix(int errorCode, Fix repairCode) {
		exceptionFixes.put(errorCode, repairCode);
	}

	public void runFix(int code) {
		if (this.exceptionFixes.containsKey(code)) {
			this.exceptionFixes.get(code).fix();
		} else {
			return;
		}
	}
}
