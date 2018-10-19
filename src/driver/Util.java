package driver;
public class Util {

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
