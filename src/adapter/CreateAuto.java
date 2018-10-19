package adapter;

import handler.AutoException;

public interface CreateAuto {

	public void BuildAuto(String FilePath) throws AutoException;

	public void PrintAuto(String ModelName) throws AutoException;
}
