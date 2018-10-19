package adapter;

import java.io.File;
import java.util.ArrayList;

import data.Auto;
import handler.AutoException;
import handler.ExceptionEntry;
import io.Parser;

public abstract class ProxyAuto {

	private static ArrayList<Auto> a1;

	public ArrayList<ExceptionEntry> RegisterErrorFixes(){
		return new ArrayList<>();
	};

	public void UpdateOptionSetName(String ModelName, String OptionSetName, String UpdatedName)
			throws AutoException, IllegalArgumentException{
		for (Auto a : a1) {
			if (a.getName().equals(ModelName)) {
				a.setOptionSetName(OptionSetName, UpdatedName);
				return;
			}
		}
		throw new AutoException((short) 0x2);
	}

	public void UpdateOptionPrice(String ModelName, String OptionName, float UpdatedPrice) throws AutoException{
		boolean success = false;
		for (Auto a : a1) {
			if (a.getName().equals(ModelName)) {
				a.setOptionPrices(OptionName, UpdatedPrice);
				success = true;
			}
		}
		if (!success) throw new AutoException((short) 0x2);
	}

	public void BuildAuto(String FilePath){
		Parser p = new Parser(new File(FilePath));
		a1.add(p.Parse());
	}

	public void PrintAuto(String ModelName) throws AutoException{
		boolean success = false;
		for (Auto a : a1) {
			if (a.getName().equals(ModelName)) {
				success = true;
				System.out.println(a.toString());
			}
		}
		if (!success) throw new AutoException((short) 0x2);
	}
}