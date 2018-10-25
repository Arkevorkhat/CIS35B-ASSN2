package data;

import java.util.ArrayList;
import java.util.HashMap;

public class AutoModel {
	private HashMap<String, Option> Selections = new HashMap<>();
	public ArrayList<Option> getSelections(){
		ArrayList<Option> interim = new ArrayList<>();
		interim.addAll(Selections.values());
		return interim;
	}
}
