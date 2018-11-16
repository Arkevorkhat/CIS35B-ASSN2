package driver;

import java.io.File;
import java.net.URISyntaxException;

import handler.AutoException;
import scaling.EditAuto;

public class Core {

	public static boolean	DEBUG	= true;
	public static File		baseInputFile;
	static {
		try {
			baseInputFile = new File(Core.class.getResource("test.txt").toURI());
		}
		catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args){
		if (args.length > 0) {
			if (args[0].equals("-d")) {
				DEBUG = true;
			}
			else if (args[0].equals("-s")) {
				DEBUG = false;
			}
		}
		if (DEBUG) {
			EditAuto a = new EditAuto();
			try {
				a.BuildAutoSync(new File(Core.class.getResource("test.txt").toURI()).getAbsolutePath());
				Thread t1 = new Thread(() -> {
					synchronized (a) {
						System.out.println("Thread t1");
						a.UpdateOptionSetNameSync("test", "Color", "Colors");
						a.PrintAutoSync("test");
					}
				});
				Thread t2 = new Thread(() -> {
					synchronized (a) {
						System.out.println("Thread t2");
						a.UpdateOptionSetNameSync("test", "Color", "Colour");
						a.PrintAutoSync("test");
					}
				});
				Thread t1n = new Thread(() -> {
					try {
						synchronized (a) {
							System.out.println("Thread t1n");
							a.UpdateOptionName("test", "Color", "Colors");
							a.PrintAuto("test");
						}
					}
					catch (AutoException e) {
						System.out.println("Exception in test code for synchronization in thread t1n");
					}
				});
				Thread t2n = new Thread(() -> {
					try {
						synchronized (a) {
							System.out.println("Thread t2n");
							a.UpdateOptionSetName("test", "Color", "Colour");
							a.PrintAuto("test");
						}
					}
					catch (AutoException e) {
						System.out.println("Exception in test code for synchronization in thread t2n");
					}
				});
				Thread reset = new Thread(() -> {
					synchronized (a) {
						a.UpdateOptionSetNameSync("test", "Colour", "Color");
						a.UpdateOptionSetNameSync("test", "Colors", "Color");
						System.out.println("Reset complete");
					}
				});
				t1.start();
				t2.start();
				reset.start();
				t1n.start();
				t2n.start();
			}
			catch (URISyntaxException e) {}
		}
	}
}
