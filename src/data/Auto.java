package data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import driver.Core;
import handler.AutoException;

public class Auto implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final int MAXIMUM_OPTIONSET_ARRAY_SIZE = 7; // Arbitrary default limit on the number OptionSets
																// supported
																// on a single vehicle object
	private data.OptionSet[] options = new OptionSet[MAXIMUM_OPTIONSET_ARRAY_SIZE];
	private double baseCost;
	private String name;
	private File saveLocation;
	private boolean exists = false;

	public data.OptionSet[] getOptions() {
		return options;
	}

	public Auto(OptionSet[] Options, double BaseCost, String Name) {
		this.options = Options;
		this.baseCost = BaseCost;
		this.name = Name;
		this.exists = true;
		this.assignDefaultFileLocation();
	}

	public Auto() {
		this.assignDefaultFileLocation();
	}

	public boolean exists() {
		return this.exists;
	}

	private void assignDefaultFileLocation() {
		if (this.name.equals("")) {
			this.saveLocation = new File(Core.baseInputFile.getParent() + "Auto" + new Date().getTime() + ".ser");
		} else {
			this.saveLocation = new File(
					Core.baseInputFile.getParent() + "Auto " + this.name + new Date().getTime() + ".ser");
		}
	}

	public File getStorageLocation() {
		return this.saveLocation;
	}

	public void LogVehicle() {
		System.out.printf("%s, With a Base Cost of: %7.2f \n", this.name, this.baseCost);
		for (OptionSet op : this.options) {
			System.out.printf("OptionSet titled %s ", op.getName());
			System.out.printf("with Options %s \n", op.formatOptionSetForFileOutput()); // logs the output from a helper
																						// method
			// written to ease the creation of new
			// input text files in future.
		}
	}

	public void LogVehicle(FileWriter o) {
		try {
			o.write(this.name + "With a Base Cost of: " + this.baseCost);
			for (OptionSet opset : this.options) {
				o.write(opset.getName() + '\n' + opset.formatOptionSetForFileOutput() + '\n');
			}
		} catch (IOException ioe) {
			System.err.printf("Failed to log vehicle to file, exception %s was thrown.", ioe.getMessage());
		}
	}

	public void setOptions(data.OptionSet[] options) {
		this.options = options;
	}

	public double getBaseCost() {
		return baseCost;
	}

	public void setBaseCost(double baseCost) {
		this.baseCost = baseCost;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOptionPrices(String name, float updatedPrice) {
		for (OptionSet s : this.options) {
			try {
				s.SetOptionByName(name, updatedPrice);
			} catch (AutoException e) {
				e.printStackTrace();
			}
		}
	}

	public void setOptionByName(String Optionset, String option, float update) {
		for (int i = 0; i < this.options.length; i++) {
			if (this.options[i].getName().equals(Optionset)) {
				try {
					this.options[i].SetOptionByName(option, update);
				} catch (AutoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public String toString() {
		StringBuffer c = new StringBuffer();
		c.append(this.name + "\n With a base cost of " + this.baseCost + "\n");
		for (OptionSet os : this.options) {
			c.append(os.formatOptionSetForFileOutput() + "\n");
		}
		return c.toString();
	}

	public void setOptionSetName(String existing, String update) {
		for (int i = 0; i < this.options.length; i++) {
			if (this.options[i].getName().equals(existing)) {
				this.options[i].setName(update);
			}
		}
	}
}
