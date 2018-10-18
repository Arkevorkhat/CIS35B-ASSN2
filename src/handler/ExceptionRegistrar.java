package handler;

@FunctionalInterface
public interface ExceptionRegistrar {
	/** @since 18OCT18 
	 *  This interface exists so that implementers can define
	 *        anonymous inner classes to implement the functionality required to
	 *        register fixes to errors that their code might fix.
	 */
	public abstract void RegisterErrorFixes();
}
