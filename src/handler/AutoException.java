package handler;

import java.util.ArrayList;

public class AutoException extends Exception {

	private static final long	serialVersionUID	= 1L;
	private short				ExceptionID			= 0x000;
	private String				ErrorMessage;

	public AutoException (short ExceptionID) {
		this.ExceptionID = ExceptionID;
		this.registerDefaultExceptionFixes();
	}

	public AutoException (short ExceptionID, String Message) {
		this.ExceptionID = ExceptionID;
		this.ErrorMessage = Message;
		this.registerDefaultExceptionFixes();
	}
	/* Exceptions 0x000 - 0x280 : General Exceptions
	 * Exceptions 0x281 - 0x500 : Adapter package Exceptions
	 * Exceptions 0x501 - 0x780 : Data package Exceptions
	 * Exceptions 0x781 - 0xA00 : Driver package exceptions
	 * Exceptions 0xA01 - 0xC80 : Handler package exceptions
	 * Exceptions 0xC81 - 0xF00 : IO package Exceptions */

	private void registerDefaultExceptionFixes(){
		ExceptionRegistry.Register(() -> {
			ArrayList<ExceptionEntry> entries = new ArrayList<>();
			entries.add(new ExceptionEntry().setRegistrar(this.getClass()).setExceptionID((short) 0x0)
					.setDefaultMessage("NA").setDefinedFixCode(() -> {
						System.out.println("An AutoException was thrown. Want a cookie?");
						return new Object();
					}));
			return entries;
		});
		ExceptionRegistry.finalizeAllPendingRegistrars();
	}

	public short getExceptionID(){
		return ExceptionID;
	}

	public void fix(){
		// Handler.runFix(this.ExceptionID);
		this.exceptionRepair(this.ExceptionID);
	}

	/**
	 * @param exceptionID
	 *            the exceptionID to set
	 */
	public void setExceptionID(short exceptionID){
		ExceptionID = exceptionID;
	}

	public void exceptionRepair(int exceptionID){
		// this will only really work for exceptions that cannot be programmatically repaired, and
		// as such must simply be reported to the end user.
		// Handler.runFix(exceptionID);
		ExceptionRegistry.getFix((short) exceptionID).getDefinedFixCode().repair();
	}

	public String getErrorMessage(){
		return ErrorMessage;
	}

	public void setErrorMessage(String errorMessage){
		ErrorMessage = errorMessage;
	}
}
