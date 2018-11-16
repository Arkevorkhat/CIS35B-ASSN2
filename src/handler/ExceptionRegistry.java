package handler;

import java.util.ArrayList;

/**
 * static Registry class that handles storage of programmer defined exception repair code.
 */
public class ExceptionRegistry {

	private static ArrayList<ExceptionEntry>		registeredExceptions;
	private static ArrayList<ExceptionRegistrar>	PendingRegistrars;

	/**
	 * @deprecated
	 * @since 3-NOV-2018
	 * @see getFix(short ExceptionID)
	 */
	public static ArrayList<ExceptionEntry> getRegisteredExceptions(){
		return registeredExceptions;
	}

	public static ExceptionEntry getFix(short ExceptionID){
		finalizeAllPendingRegistrars();
		synchronized (registeredExceptions) {
			for (ExceptionEntry e : registeredExceptions) {
				if (e.getExceptionID() == ExceptionID) {
					return e;
				}
				else
					continue;
			}
		}
		return new ExceptionEntry().setExceptionID((short) 0x0)
				.setDefaultMessage("Exception Fix failed. Original Exception ID that was attempted was:" + ExceptionID);
	}

	private static void addRegisteredException(ExceptionEntry input){
		registeredExceptions.add(input);
	}

	/**
	 * adds an {@link handler.ExceptionRegistrar} to the pending list of registrars
	 * 
	 * @param registrar
	 *            {@link handlerExceptionRegistrar} to add.
	 */
	public static void Register(ExceptionRegistrar registrar){
		PendingRegistrars.add(registrar);
	}

	/**
	 * iterates across all pending {@link handler.ExceptionRegistrar}s and calls their
	 * register method. Then iterates across those {@link handler.ExceptionEntry} objects and
	 * adds them to the active exception handler map.
	 */
	public static void finalizeAllPendingRegistrars(){
		for (ExceptionRegistrar registrar : PendingRegistrars) {
			for (ExceptionEntry entry : registrar.registerExceptionFixes()) {
				addRegisteredException(entry);
			}
			PendingRegistrars.remove(registrar);
		}
	}
}
