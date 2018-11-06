package handler;
public class ExceptionEntry {

	private short		ExceptionID;
	private String		Name;
	private String		DefaultMessage;
	private Class<?>	registrar;
	private Fix			definedFixCode;

	public Class<?> getRegistrar(){
		return registrar;
	}

	public ExceptionEntry setRegistrar(Class<?> registrar){
		this.registrar = registrar;
		return this;
	}

	public String getDefaultMessage(){
		return DefaultMessage;
	}

	public ExceptionEntry setDefaultMessage(String defaultMessage){
		DefaultMessage = defaultMessage;
		return this;
	}

	public String getName(){
		return Name;
	}

	public ExceptionEntry setName(String name){
		Name = name;
		return this;
	}

	public short getExceptionID(){
		return ExceptionID;
	}

	public ExceptionEntry setExceptionID(short exceptionID){
		ExceptionID = exceptionID;
		return this;
	}

	public Fix getDefinedFixCode(){
		return definedFixCode;
	}

	public ExceptionEntry setDefinedFixCode(Fix definedFixCode){
		this.definedFixCode = definedFixCode;
		return this;
	}
}
