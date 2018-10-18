package adapter;

import handler.AutoException;

public interface UpdateAuto {
	public void UpdateOptionSetName(String ModelName, String OptionSetName, String UpdatedName) throws AutoException;
	public void UpdateOptionPrice(String ModelName, String OptionName, float UpdatedPrice) throws AutoException, IllegalArgumentException;
}