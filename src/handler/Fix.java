package handler;
/**
 * Interface allowing for polymorphic definition of Exception Repair code.
 * 
 * @FunctionalInterface
 */
public interface Fix {

	public abstract Object repair();
}