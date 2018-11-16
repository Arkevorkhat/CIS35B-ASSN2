package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import data.Auto;

/**
 * Abstracts away Serialization tasks for a given {@link data.Auto} model such that the
 * end user
 * doesn't need to know how Serialization works.
 * 
 */
public class Serializer {

	private Auto	modelObject;
	private boolean	hasModel	= false;
	private File	saveLocation;

	/**
	 * Output Constructor
	 * 
	 * @param model
	 *            The {@link data.Auto} object to be serialized.
	 */
	public Serializer (Auto model) {
		this.hasModel = true;
		this.modelObject = model;
		this.saveLocation = model.getStorageLocation();
	}

	/**
	 * Input Constructor
	 * 
	 * @param storageLocation
	 *            The {@link java.io.File} representation of the location of a .ser file
	 *            containing an auto object.
	 */
	public Serializer (File storageLocation) {
		this.saveLocation = storageLocation;
	}

	/**
	 * Dumps the Serializer's {@link data.Auto} object to file, with the .ser extension.
	 * 
	 * @param Location
	 *            File Location to save the object at.
	 * @throws NotSerializableException
	 *             if a subclass of {@link data.Auto} is created and passed, or if no
	 *             {@link data.Auto} exists.
	 * @throws IOException
	 */
	public void Serialize(File Location) throws NotSerializableException, IOException{
		if (!this.modelObject.getClass().isAssignableFrom(Serializable.class) // reflection to ensure serializability.
				|| this.hasModel == false) { throw new NotSerializableException(); }
		if (!Location.exists()) {
			Location.getParentFile().mkdirs();
			Location.createNewFile();
		}
		ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(Location));
		output.writeObject(this.modelObject);
		output.close();
	}

	/**
	 * @throws NotSerializableException
	 *             if Serialize(File) does.
	 * @throws IOException
	 *             if Serialize(File) does.
	 */
	public void Serialize() throws NotSerializableException, IOException{
		if (!this.saveLocation.exists()) {
			this.saveLocation.getParentFile().mkdirs();
			this.saveLocation.createNewFile();
		}
		Serialize(this.saveLocation);
	}

	/**
	 * @param Location
	 *            Location of the .ser file that contains the object to be read.
	 * @throws IOException
	 *             for various reasons, primarily if the file doesn't exist.
	 * @throws ClassNotFoundException
	 *             if the .ser file is corrupted, or if it contains something other than an
	 *             {@link data.Auto}.
	 */
	public void deSerialize(File Location) throws IOException, ClassNotFoundException{
		ObjectInputStream input = new ObjectInputStream(new FileInputStream(Location));
		Auto temp = (Auto) input.readObject();
		input.close();
		this.modelObject = temp;
	}

	public void deSerialize() throws IOException, ClassNotFoundException, NullPointerException{
		if (!this.saveLocation.exists()) { throw new NullPointerException(); }
		deSerialize(this.saveLocation);
	}

	public File getStorageLocation(){
		return this.saveLocation;
	}

	public Auto getDataObject(){
		return this.modelObject;
	}
}
