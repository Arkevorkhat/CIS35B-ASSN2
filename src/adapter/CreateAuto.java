package adapter;

import java.io.IOException;

import handler.AutoException;

public interface CreateAuto {
	public void BuildAuto(String FilePath) throws IOException, AutoException;

	public void PrintAuto(String ModelName) throws AutoException, NullPointerException;
}
