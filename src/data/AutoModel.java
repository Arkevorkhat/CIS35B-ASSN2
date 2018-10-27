package data;

import java.util.ArrayList;
import java.util.HashMap;
import driver.Core;

public class AutoModel extends Auto {

	private static final long		serialVersionUID	= 1L;
	private HashMap<String, Option>	Selections			= new HashMap<>();

	public ArrayList<Option> getSelections(){
		ArrayList<Option> interim = new ArrayList<>();
		interim.addAll(Selections.values());
		return interim;
	}

	public AutoModel(Auto a) {
		super(a);
	}
	
	public HashMap<String, Option> getSelectionsMap(){
		return this.Selections;
	}

	public AutoModel addSelection(String ParentOptionSetName, Option optionToSet){
		this.Selections.put(ParentOptionSetName, optionToSet);
		return this;
	}

	
	
	public AutoModel removeSelection(String ParentOptionSetName, String OptionName){
		if (Selections.containsKey(ParentOptionSetName)) {
			if (Selections.get(ParentOptionSetName).getTitle().equals(OptionName)) {
				Selections.remove(ParentOptionSetName);
				return this;
			}
			else {
				if (Core.DEBUG == true) System.out
						.println("DEBUG: Failed to Remove, Invalid OptionName Passed. OptionName = " + OptionName);
				return this;
			}
		}
		else {
			if (Core.DEBUG == true) {
				System.out.printf("DEBUG: Failed to Remove, Invalid OptionSetName passed, ParentOptionSetName = %s",
						ParentOptionSetName);
			}
			return this;
		}
	}
}
