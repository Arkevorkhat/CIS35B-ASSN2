package io;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import data.*;
import handler.ParserFix;

public class Parser {
	private File storageLocation;

	public Parser(File Input) {
		this.storageLocation = Input;
	}

	protected File getStorageLocation() {
		return this.storageLocation;
	}

	/**
	 * @return a vehicle object defined by the input text file.
	 */

	public Auto Parse() {
		Auto parserInterimVehicle = new Auto();
		ArrayList<OptionSet> optionSetList = new ArrayList<OptionSet>();
		try {
			Scanner storageInput = new Scanner(new FileReader(storageLocation));
			if (storageInput.hasNextLine()) { // make sure that the input file is not empty.
				// parserLogger.log(Level.INFO, "Began reading data from file: " +
				// storageLocation.getAbsolutePath());

				String[] tempFirstLine = storageInput.nextLine().split(":"); // grab the first line of the file, which
																				// should be an ordered pair of
																				// name:cost
				parserInterimVehicle.setName(tempFirstLine[0]);
				// parserLogger.log(Level.INFO, "parserVehicle.name == " +
				// parserInterimVehicle.getName());
				try {
					parserInterimVehicle.setBaseCost(Double.parseDouble(tempFirstLine[1].trim()));
				} catch (NumberFormatException E) {
					parserInterimVehicle.setBaseCost(ParserFix.fixDouble("Vehicle Base Cost"));
				}
				// parserLogger.log(Level.INFO, "parserVehicle.cost == " +
				// parserInterimVehicle.getBaseCost());
				int optionSetLoopLimit;
				try {
					optionSetLoopLimit = Integer.parseInt(storageInput.nextLine());
				} catch (NumberFormatException e) {
					optionSetLoopLimit = ParserFix.fixInt("Number of OptionSets");
				}
				// grab the second line of the file,
				// which should be an integer number
				// of OptionSets
				// parserLogger.log(Level.INFO, "Number of OptionSets = " + optionSetLoopLimit);

				for (int iterator0 = 0; iterator0 < optionSetLoopLimit; iterator0++) { // loop across the OptionSets
					String optionSetName = storageInput.nextLine(); // grab the first line of the OptionSet block, and
																	// store it as the name of the set.
					// parserLogger.log(Level.INFO, optionSetName);
					ArrayList<Option> options = new ArrayList<Option>(); // create an arrayList to
																								// handle temporary
																								// storage of Option
																								// objects.
					int optionsLoopLimit;
					try {
						optionsLoopLimit = Integer.parseInt(storageInput.nextLine());
					} catch (NumberFormatException E) {
						optionsLoopLimit = ParserFix.fixInt("Number of Options in OptionSet " + optionSetName);
					}
					for (int iterator1 = 0; iterator1 < optionsLoopLimit; iterator1++) { // loop across the set of
																							// Options
						String optionIn = storageInput.nextLine(); // read the Option as an ordered pair
						// parserLogger.log(Level.INFO, optionIn + " ");
						String[] option = optionIn.split(":"); // split across the pair delimiter :
						// parserLogger.log(Level.INFO, option.toString());
						options.add(new Option(option[0], Float.parseFloat(option[1]))); // add the
																											// parsed
																											// Option to
																											// the
																											// options
																											// arrayList
						// parserLogger.log(Level.INFO, "Added an entry to this.options");
					}
					optionSetList.add(new OptionSet(optionSetName, options));
					// add the parsed optionSet to the OptionSet ArrayList
				}
				parserInterimVehicle.setOptions(optionSetList.toArray(new OptionSet[0]));
				// set the values of the
			}
			storageInput.close();
		} catch (Exception e) {
			System.err.println(e.getMessage() + e.getClass());
		}
		return parserInterimVehicle;
	}
}
