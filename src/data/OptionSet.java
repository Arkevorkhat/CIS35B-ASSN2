package data;

import java.io.Serializable;
import java.util.ArrayList;

import handler.AutoException;

public class OptionSet implements Serializable {
	private static final long serialVersionUID = 1L;
	// gamut will be larger than this.
	private ArrayList<Option> options;
	private String name;

	public OptionSet(String name, ArrayList<Option> options) {
		this.name = name;
		this.options = options;
	}

	public OptionSet(String name) {
		this.setName(name);
		this.options = new ArrayList<Option>();
	}

	public OptionSet() {
		this.name = "";
		this.options = new ArrayList<Option>();
	}

	/**
	 * @category Data Output Prints information about a valid OptionSet object's
	 *           Options array.
	 */
	protected void printOptionSet() {
		for (Option o : options) {
			System.out.printf("%s, costing %7.2f\n", o.getTitle(), o.getCost()); // prints option title, costing option
																					// cost.\n
		}
	}

	/**
	 * @category Data Output
	 * @return A string containing newline separated values representing the
	 *         name:cost pairs of all options stored in the OptionSet.
	 */
	protected String formatOptionSetForFileOutput() {
		StringBuffer storage = new StringBuffer();
		for (Option O : this.options) {
			storage.append(O.getTitle() + ":" + O.getCost() + "\n"); // Append data Title:Cost\n to stringbuffer
		}
		return storage.toString(); // builds the StringBuffer object into a string and returns it.
	}

	/**
	 * @param name,
	 *            the name of the option to find.
	 * @return the option, if found, or NULL if no such option exists.
	 */

	protected Option findOptionByName(String name) {
		for (Option o : this.options) {
			if (o.getTitle().equals(name)) {
				return o;
			} else
				continue;
		}
		return null;
	}

	/**
	 * @param cost,
	 *            the cost value of the option that needs to be found.
	 * @return the array of Option objects with the chosen cost.
	 */

	protected Option[] findOptionByCost(double cost) {
		ArrayList<Option> options = new ArrayList<Option>();
		for (Option o : this.options) {
			if (o.getCost() == cost) {
				options.add(o);
			}
		}
		return (Option[]) options.toArray();
	}

	/**
	 * @param name,
	 *            the name of the option to change
	 * @param Cost,
	 *            the cost value to set on the chosen option
	 * @throws ArrayIndexOutOfBoundsException
	 *             if this method is called on an improperly initialized optionSet
	 *             object
	 */
	protected void SetOptionByName(String name, float Cost) throws AutoException {
		checkArray();
		for(Option op : this.options) {
			if(op.getTitle().equals(name)) {
				op.setCost(Cost);
			}
		}
	}

	/**
	 * @param Cost,
	 *            the cost value that needs to be updated universally.
	 * @param Set,
	 *            the final cost value that should exist after the set operation.
	 * @throws ArrayIndexOutOfBoundsException,
	 *             when called on an improperly or non initialized optionset object.
	 */
	protected void setOptionByCost(float Cost, float Set) throws AutoException {
		checkArray();
		for(Option op : this.options) {
			if (op.getCost()==Cost) {
				op.setCost(Set);
			}
		}
	}

	/**
	 * @category internal utilities
	 * @throws AutoException
	 *             if called from an improperly initialized optionset object
	 */
	protected void checkArray() throws AutoException {
		if(this.options.isEmpty()) {
			throw new AutoException((short) 0x003);
		}
	}

	protected String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	protected ArrayList<Option> getOptions() {
		return options;
	}

	protected void setOptions(ArrayList<Option> options) {
		this.options = options;
	}
}
