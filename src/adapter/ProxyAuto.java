package adapter;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Random;

import data.Auto;
import data.Option;
import data.OptionSet;
import handler.AutoException;
import handler.ExceptionEntry;
import io.Parser;

public abstract class ProxyAuto {

	private static LinkedHashMap<Integer, Auto> a1;

	/**
	 * @category U - Change
	 * @param ModelName
	 *            Name of the AutoModel object to work on.
	 * @param OptionSetName
	 *            OptionSet to Update
	 * @param UpdatedName
	 *            Name to Set
	 * @throws AutoException
	 *             If the AutoModel object is not found.
	 */
	public void UpdateOptionSetName(String ModelName, String OptionSetName, String UpdatedName) throws AutoException{
		synchronized (a1) {
			for (Auto a : a1.values()) {
				if (a.getName().equals(ModelName)) {
					a.setOptionSetName(OptionSetName, UpdatedName);
					return;
				}
			}
			throw new AutoException((short) 0x2);
		}
	}

	/**
	 * @category U - Change
	 * @param ModelName
	 *            AutoModel Object to change
	 * @param OptionName
	 *            Option to change.
	 * @param UpdatedPrice
	 *            final Option Price
	 * @throws AutoException
	 *             if the AutoModel is not found.
	 */
	public void UpdateOptionPrice(String ModelName, String OptionName, float UpdatedPrice){
		synchronized (a1) {
			for (Auto a : a1.values()) {
				if (a.getName().equals(ModelName)) {}
			}
		}
	}

	public void MakeSelection(String ModelName, String OptionSetName, String OptionName){
		synchronized (a1) {
			for (Auto a : a1.values()) {
				if (a.getName().equals(ModelName)) {
					try {
						OptionSet ops = a.getOptionSetByName(OptionSetName);
						Option op = a.getOptionInSetByName(OptionSetName, OptionName);
						a.addSelection(ops, op);
					}
					catch (AutoException e) {
						e.fix();
					}
				}
			}
		}
	}

	/**
	 * @category C - Input
	 * @param FilePath
	 *            The full path to the .txt file containing a structured representation of the
	 *            Auto to be created. Must fit the format shown in src/driver/Ford ZTW.txt
	 */
	public void BuildAuto(String FilePath){
		Parser p = new Parser(new File(FilePath));
		Auto A = new Auto(p.Parse());
		A.setUUID(new Random().nextInt());
		synchronized (a1) {
			a1.put(A.getUUID(), A);
		}
	}

	/**
	 * @category R - User Output
	 * @param ModelName
	 *            The ModelName of the Auto object to be printed
	 *            will only show the first instance of that named auto in the map.
	 * @throws AutoException
	 *             if the ModelName does not exist.
	 */
	public void PrintAuto(String ModelName) throws AutoException{
		boolean success = false;
		synchronized (a1) {
			for (Auto a : a1.values()) {
				if (a.getName().equals(ModelName)) {
					success = true;
					System.out.println(a.toString());
				}
			}
		}
		if (!success) throw new AutoException((short) 0x2);
	}

	/**
	 * @return
	 */
	public ArrayList<ExceptionEntry> registerExceptionFixes(){
		ArrayList<ExceptionEntry> entries = new ArrayList<>();
		ExceptionEntry entry0x2 = new ExceptionEntry().setExceptionID((short) 0x2).setName("Auto Not Found")
				.setDefaultMessage(
						"Auto object not found, it is possible that either the object you wish to use has been removed,"
								+ " or you passed a bad reference.");
		entry0x2.setDefinedFixCode(() -> {
			System.out.println("Failed to find Auto.");
			return new Object();
		});
		entries.add(entry0x2);
		return entries;
	}
}