package handler;

public class ExceptionEntry {
	private short ExceptionID;
	private String Name;
	private String DefaultMessage;
	private Class<?> registrar;
	private Fix definedFixCode;
	public Class<?> getRegistrar() {
		return registrar;
	}
	public void setRegistrar(Class<?> registrar) {
		this.registrar = registrar;
	}
	public String getDefaultMessage() {
		return DefaultMessage;
	}
	public void setDefaultMessage(String defaultMessage) {
		DefaultMessage = defaultMessage;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public short getExceptionID() {
		return ExceptionID;
	}
	public void setExceptionID(short exceptionID) {
		ExceptionID = exceptionID;
	}
	public Fix getDefinedFixCode() {
		return definedFixCode;
	}
	public void setDefinedFixCode(Fix definedFixCode) {
		this.definedFixCode = definedFixCode;
	}
}
