package handler;

import java.util.ArrayList;


/**
 * This interface exists so that implementers can define
 * anonymous inner classes to implement the functionality required to
 * register fixes to errors that their code might fix.
 * FixAuto may also be used if the desired implementation involves named classes.
 * 
 * @since 18OCT18
 */
@FunctionalInterface
public interface ExceptionRegistrar {
/**
 * This method can be implemented using a lambda expression, or a class, if desired.
 * @return an ArrayList containing all of the desired {@link ExceptionEntry} objects.
 */
	public abstract ArrayList<ExceptionEntry> registerExceptionFixes();
}
