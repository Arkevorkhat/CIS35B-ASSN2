package driver;
public class Util {
	/**
	 * @param S a {@link java.lang.String} that will be checked to see if it represents an int.
	 * @return whether or not S represents an int.
	 */
	public static boolean TryParseInt(String S){
		try {
			Integer.parseInt(S);
			return true;
		}
		catch (NumberFormatException E) {
			return false;
		}
	}
}
