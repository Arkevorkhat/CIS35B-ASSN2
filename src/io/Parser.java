package io;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import data.*;
import handler.ParserFix;

/**
 * Defines object-oriented text file parsing utilities capable of handling a
 * well-structured input file that follows the standard set by src/driver/Ford ZTW.txt
 */
public class Parser {

	private File	storageLocation;
	private String	ModelName;

	public Parser (File Input) {
		this.storageLocation = Input;
	}

	protected File getStorageLocation(){
		return this.storageLocation;
	}

	public String getName(){
		return this.ModelName;
	}

	/**
	 * If this Parser has a File location specified, this method will open that file and read
	 * all lines from it, arranging them into a complete {@link data.Auto} object.
	 * 
	 * @return the parsed {@link data.Auto} object.
	 */
	public Auto Parse(){
		Auto parserInterimVehicle = new Auto();
		ArrayList<OptionSet> optionSetList = new ArrayList<OptionSet>();
		try {
			int optionSetLoopLimit = 0;
			Scanner storageInput = new Scanner(new FileReader(storageLocation));
			if (storageInput.hasNextLine()) { // make sure that the input file is not empty.
				String[] tempFirstLine = storageInput.nextLine().split(":"); // grab the first line of the file, which
																				// should be an ordered pair of
																				// name:cost
				parserInterimVehicle.setName(tempFirstLine[0]);
				this.ModelName = parserInterimVehicle.getName();
				try {
					parserInterimVehicle.setBaseCost(Double.parseDouble(tempFirstLine[1].trim()));
				}
				catch (NumberFormatException E) {
					parserInterimVehicle.setBaseCost(ParserFix.fixDouble("Vehicle Base Cost"));
				}
				try {
					if (optionSetLoopLimit == 0) {
						optionSetLoopLimit = Integer.parseInt(storageInput.nextLine());
					}
				}
				catch (NumberFormatException e) {
					optionSetLoopLimit = ParserFix.fixInt("Number of OptionSets");
				}
				// grab the second line of the file,
				// which should be an integer number
				// of OptionSets
				for (int iterator0 = 0; iterator0 < optionSetLoopLimit; iterator0++) { // loop across the OptionSets
					String optionSetName = storageInput.nextLine();
					// grab the first line of the OptionSet block, and store it as the name of the set.
					ArrayList<Option> options = new ArrayList<Option>();
					// create an arrayList to handle temporary storage of Option objects.
					int optionsLoopLimit;
					try {
						optionsLoopLimit = Integer.parseInt(storageInput.nextLine());
					}
					catch (NumberFormatException E) {
						optionsLoopLimit = ParserFix.fixInt("Number of Options in OptionSet " + optionSetName);
					}
					for (int iterator1 = 0; iterator1 < optionsLoopLimit; iterator1++) {
						// loop across the set of Options
						String optionIn = storageInput.nextLine(); // read the Option as an ordered pair
						String[] option = optionIn.split(":"); // split across the pair delimiter :
						options.add(new Option(option[0], Float.parseFloat(option[1])));
						// add the parsed Option to the options arrayList
						// parserLogger.log(Level.INFO, "Added an entry to this.options");
					}
					optionSetList.add(new OptionSet(optionSetName, options));
					// add the parsed optionSet to the OptionSet ArrayList
				}
				parserInterimVehicle.setOptions(optionSetList.toArray(new OptionSet[0]));
			}
			storageInput.close();
		}
		catch (Exception e) {
			System.err.println(e.getMessage() + e.getClass());
		}
		return parserInterimVehicle;
	}
}
