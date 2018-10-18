package handler;

import java.awt.Frame;

public class AutoException extends Exception {
	private static final long serialVersionUID = 1L;
	private short ExceptionID = 0x000;
	private String ErrorMessage;
	static {
		Handler.addFix(0x0, ()->{
			Frame test = new Frame();
			test.setVisible(true);
			javax.swing.JOptionPane.showMessageDialog(test,"exception handler reached.");
			return null;
		});
		
	}
	@Deprecated
	public AutoException(ExceptionIDs ExceptionID) {
		this.setExceptionID(ExceptionID.GetExceptionID());
	}
	public AutoException(short ExceptionID) {
		this.ExceptionID = ExceptionID;
	}
	public AutoException(short ExceptionID, String Message) {
		this.ExceptionID=ExceptionID;
		this.ErrorMessage=Message; 
	}
	/*
	 * Exceptions 0x000 - 0x280 : General Exceptions
	 * Exceptions 0x281 - 0x500 : Adapter package Exceptions 
	 * Exceptions 0x501 - 0x780 : Data package Exceptions
	 * Exceptions 0x781 - 0xA00 : Driver package exceptions 
	 * Exceptions 0xA01 - 0xC80 : Handler package exceptions 
	 * Exceptions 0xC81 - 0xF00 : IO package Exceptions
	 */

	/**
	 * @return the exceptionID
	 */
	public long getExceptionID() {
		return ExceptionID;
	}
	public void fix() {
		Handler.runFix(this.ExceptionID);
	}
	/**
	 * @param exceptionID the exceptionID to set
	 */
	public void setExceptionID(short exceptionID) {
		ExceptionID = exceptionID;
	}
	public void exceptionRepair(int exceptionID) {
		Handler.runFix(exceptionID);
	}

	public String getErrorMessage() {
		return ErrorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		ErrorMessage = errorMessage;
	}
}
