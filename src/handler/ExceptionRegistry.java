package handler;

import java.util.ArrayList;

public class ExceptionRegistry {

	private static ArrayList<ExceptionEntry>		registeredExceptions;
	private static ArrayList<ExceptionRegistrar>	PendingRegistrars;

	public static ArrayList<ExceptionEntry> getRegisteredExceptions(){
		return registeredExceptions;
	}

	private static void addRegisteredException(ExceptionEntry input){
		registeredExceptions.add(input);
	}

	public static void Register(ExceptionRegistrar registrar){
		PendingRegistrars.add(registrar);
	}

	public static void finalizeAllPendingRegistrars(){
		for (ExceptionRegistrar registrar : PendingRegistrars) {
			for (ExceptionEntry entry : registrar.RegisterErrorFixes()) {
				addRegisteredException(entry);
			}
			PendingRegistrars.remove(registrar);
		}
	}
}
