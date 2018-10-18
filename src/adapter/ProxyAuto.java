package adapter;

import java.io.File;
import java.io.IOException;

import data.Auto;
import handler.AutoException;
import handler.ExceptionIDs;
import io.Parser;

public abstract class ProxyAuto {
	private static Auto a1;

	public void UpdateOptionSetName(String ModelName, String OptionSetName, String UpdatedName)
			throws AutoException, IllegalArgumentException {
		if (!a1.exists()) {
			throw new AutoException(ExceptionIDs.OBJECTNOTFOUND);
		} else if (a1.getName().equals(ModelName) == false) {
			throw new IllegalArgumentException();
		} else {
			a1.setOptionSetName(OptionSetName, UpdatedName);
		}
	}

	public void UpdateOptionPrice(String ModelName, String OptionName, float UpdatedPrice)
			throws AutoException, IllegalArgumentException {
		if (!a1.getName().equals(ModelName)) {
			throw new IllegalArgumentException();
		} else {
			a1.setOptionPrices(OptionName, UpdatedPrice);
		}
	}

	public void BuildAuto(String FilePath) throws IOException, AutoException {
		Parser p = new Parser(new File(FilePath));
		a1 = p.Parse();
		}

	public void PrintAuto(String ModelName) throws AutoException, NullPointerException {
		System.out.println(a1.toString());
	}
}