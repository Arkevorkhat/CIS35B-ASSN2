package data;

import java.io.Serializable;
import java.util.ArrayList;

import handler.AutoException;
import handler.ExceptionIDs;

public class OptionSet implements Serializable {
	private static final long serialVersionUID = 1L;
	private static int MAXIMUM_OPTION_ARRAY_SIZE = 15; // expected maximum number of options, increase if the option
														// gamut will be larger than this.
	private Option options[];
	private String name;

	public OptionSet(String name, Option[] options) {
		this.name = name;
		this.options = options;
	}

	public OptionSet(String name) {
		this.setName(name);
		this.options = new Option[MAXIMUM_OPTION_ARRAY_SIZE];
		for (int i = 0; i < this.options.length; i++) {
			try {
				this.options[i] = new Option();
			} catch (ArrayIndexOutOfBoundsException e) { // should theoretically be unreachable code, but you can never
															// be too safe.
				System.err.printf("An Exception Occurred, Message: %s", e.getMessage());
				break;
			}
		}
	}

	public OptionSet() {
		this.name = "";
		this.options = null;
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
			storage.append(O.title + ":" + O.cost + "\n"); // Append data Title:Cost\n to stringbuffer
		}
		return storage.toString(); // builds the StringBuffer object into a string and returns it.
	}

	/**
	 * @param name, the name of the option to find.
	 * @return the option, if found, or NULL if no such option exists.
	 */

	protected Option findOptionByName(String name) {
		for (Option o : this.options) {
			if (o.title.equals(name)) {
				return o;
			} else
				continue;
		}
		return null;
	}

	/**
	 * @param cost, the cost value of the option that needs to be found.
	 * @return the array of Option objects with the chosen cost.
	 */

	protected Option[] findOptionByCost(double cost) {
		ArrayList<Option> options = new ArrayList<Option>();
		for (Option o : this.options) {
			if (o.cost == cost) {
				options.add(o);
			}
		}
		return (Option[]) options.toArray();
	}

	/**
	 * @param name, the name of the option to change
	 * @param Cost, the cost value to set on the chosen option
	 * @throws ArrayIndexOutOfBoundsException if this method is called on an
	 *                                        improperly initialized optionSet
	 *                                        object
	 */
	protected void SetOptionByName(String name, float Cost) throws AutoException {
		checkArray();
		for (int i = 0; i < this.options.length; i++) {
			if (this.options[i].title.equals(name)) { // whenever you find an option with the chosen name
				this.options[i].setCost(Cost); // set its' cost to the float input.
			}
		}
	}

	/**
	 * @param Cost, the cost value that needs to be updated universally.
	 * @param Set, the final cost value that should exist after the set operation.
	 * @throws ArrayIndexOutOfBoundsException, when called on an improperly or non
	 *                                         initialized optionset object.
	 */
	protected void setOptionByCost(float Cost, float Set) throws AutoException {
		checkArray();
		for (int i = 0; i < this.options.length; i++) {
			if (this.options[i].getCost() == Cost) {
				this.options[i].setCost(Set);
			}
		}
	}

	/**
	 * @category internal utilities
	 * @throws AutoException if called from an improperly initialized optionset
	 *                       object
	 */
	protected void checkArray() throws AutoException {
		if (this.options.length == 0) {
			throw new AutoException(ExceptionIDs.INVALIDARRAY);
		}
	}

	protected String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	protected Option[] getOptions() {
		return options;
	}

	protected void setOptions(Option[] options) {
		this.options = options;
	}

	public class Option implements Serializable {
		private static final long serialVersionUID = 1L;
		private String title;
		private float cost;

		protected float getCost() { // getter
			return cost;
		}

		protected void setCost(float cost) { // setter
			this.cost = cost;
		}

		public Option() {
			this.cost = 0f;
		}

		public Option(float cost) {
			this.cost = cost;
		}

		public Option(String name, float cost) {
			this.title = name;
			this.cost = cost;
		}

		protected String getTitle() {
			return title;
		}

		protected void setTitle(String title) {
			this.title = title;
		}
	}
}
