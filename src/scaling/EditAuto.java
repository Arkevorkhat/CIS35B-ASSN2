package scaling;

import adapter.BuildAuto;
import adapter.CreateAuto;
import adapter.UpdateAuto;
import handler.AutoException;

/**
 * Contains all asynchronous code required to handle CRUD operations for
 * {@link adapter.ProxyAuto}'s a1 {@link data.Auto} LinkedHashMap
 * 
 * @since 03-NOV-2018
 * @version 1
 */
public class EditAuto {

	/**
	 * @param FilePath
	 *            the full path to the txt file containing a textual representation of an
	 *            {@link data.Auto} object, usable by {@link io.Parser}
	 * 
	 */
	public static void BuildAutoAsync(String FilePath){
		new Thread(() -> { // Lambda expression creating an anonymous inner class implementing runnable.
			CreateAuto a = new BuildAuto();
			try {
				a.BuildAuto(FilePath);
			}
			catch (AutoException e) {
				e.fix();
			}
		}).start();
	}

	/**
	 * Takes a ModelName and prints it off to System.out.
	 * 
	 * @param ModelName
	 *            Name of the {@link data.Auto} to be found.
	 */
	public static void PrintAutoAsync(String ModelName){
		new Thread(() -> {
			CreateAuto a = new BuildAuto();
			try {
				a.PrintAuto(ModelName);
			}
			catch (AutoException e) {
				e.fix();
			}
		}).start();
	}

	/**
	 * @param ModelName
	 *            Name of the {@link data.Auto} object that will contain the {@link data.OptionSet}
	 * @param OptionSetName
	 *            Name of the {@link data.OptionSet} to be updated.
	 * @param UpdatedName
	 *            The name to be set.
	 */
	public static void UpdateOptionSetNameAsync(String ModelName, String OptionSetName, String UpdatedName){
		new Thread(() -> {
			UpdateAuto a = new BuildAuto();
			try {
				a.UpdateOptionSetName(ModelName, OptionSetName, UpdatedName);
			}
			catch (AutoException e) {
				e.fix();
			}
		}).start();
	}

	
	/**
	 * @param ModelName Name of the {@link data.Auto} object that will contain the {@link data.OptionSet}
	 * @param OptionName Name of the {@link data.Option} to be updated.
	 * @param UpdatedPrice The price to set.
	 */
	public static void UpdateOptionPriceAsync(String ModelName, String OptionName, float UpdatedPrice){
		new Thread(() -> {
			UpdateAuto a = new BuildAuto();
			try {
				a.UpdateOptionPrice(ModelName, OptionName, UpdatedPrice);
			}
			catch (AutoException e) {
				e.fix();
			}
		}).start();
	}
 /**
  * copies a {@link data.Option} to an {@link data.Auto}'s Selections map.
  * @param ModelName Name of the {@link data.Auto} object that will be updated.
  * @param OptionSetName Name of the {@link data.OptionSet} to be found.
  * @param OptionName Name of the {@link data.Option} object to be found.
  */
	public static void MakeSelectionAsync(String ModelName, String OptionSetName, String OptionName){
		new Thread(() -> {
			BuildAuto a = new BuildAuto();
			a.MakeSelection(ModelName, OptionSetName, OptionName);
		}).start();
	}
}
