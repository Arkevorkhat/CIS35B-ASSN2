package handler;
@Deprecated
public enum ExceptionIDs {
	NOEXCEPTION(0X000), FILENOTFOUND(0x001), INVALIDSEARCHPARAMETER(0x002), INVALIDARRAY(0X003), FILEFORMAT(0xC81), HEADERMISSING(0xC82), OPTIONSETNAMEMISSING(0xC83),
	OBJECTNOTFOUND(0x501), INVALIDOPTIONSET(0X502),MISSINGVALUEINT(0xC84);

	/*
	 * Exceptions 0x000 - 0x280 : General Exceptions
	 * Exceptions 0x281 - 0x500 : Adapter package Exceptions 
	 * Exceptions 0x501 - 0x780 : Data package Exceptions
	 * Exceptions 0x781 - 0xA00 : Driver package exceptions 
	 * Exceptions 0xA01 - 0xC80 : Handler package exceptions 
	 * Exceptions 0xC81 - 0xF00 : IO package Exceptions
	 */
	
	private short ExceptionCode;

	private ExceptionIDs(short Code) {
		this.ExceptionCode = Code;
	}

	private ExceptionIDs(int Code) {
		if (Code > Short.MAX_VALUE) {
			this.ExceptionCode = Short.MAX_VALUE;
		} else
			this.ExceptionCode = (short) Code;
	}
	public short GetExceptionID() {
		return this.ExceptionCode;
	}
}
