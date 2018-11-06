package handler;

import java.util.Scanner;

public class ParserFix {

	/**
	 * User input method to have the end user fix any issues with a supplied text file.
	 * 
	 * @param name
	 *            a {@link java.lang.String} to be shown to the user as the name for the
	 *            variable to fill.
	 * @return a User supplied integer
	 */
	public static int fixInt(String name){
		System.out.printf("Missing parameter for value %s. Please input the Correct Value.", name);
		Scanner S = new Scanner(System.in);
		int input = S.nextInt();
		S.close();
		return input;
	}

	/**
	 * User input method to have the end user fix any issues with a supplied text file.
	 * 
	 * @param name
	 *            a {@link java.lang.String} to be shown to the user as the name for the
	 *            variable to fill.
	 * @return a User supplied double.
	 */
	public static double fixDouble(String name){
		System.out.printf("Missing parameter for value %s. Please input the Correct Value.", name);
		Scanner S = new Scanner(System.in);
		double input = S.nextDouble();
		S.close();
		return input;
	}
}
