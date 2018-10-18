package handler;

import java.util.ArrayList;

public class ExceptionRegistry {
	private ArrayList<ExceptionEntry> registeredExceptions;

	public ArrayList<ExceptionEntry> getRegisteredExceptions() {
		return this.registeredExceptions;
	}

	public void addRegisteredExceptions(ExceptionEntry input) {
		this.registeredExceptions.add(input);
	}
}
