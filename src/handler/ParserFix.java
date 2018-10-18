package handler;

import java.util.Scanner;

public class ParserFix {
	public static int fixInt(String name) {
		System.out.printf("Missing parameter for value %s. Please input the Correct Value.", name);
		Scanner S = new Scanner(System.in);
		int input = S.nextInt();
		S.close();
		return input;
	}
	public static double fixDouble(String name) {
		System.out.printf("Missing parameter for value %s. Please input the Correct Value.", name);
		Scanner S = new Scanner(System.in);
		double input = S.nextDouble();
		S.close();
		return input;
	}
}
