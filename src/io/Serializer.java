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

public class Serializer {
	private Auto modelObject;
	private boolean hasModel = false;
	private File saveLocation;

	public Serializer(Auto model) {
		this.hasModel = true;
		this.modelObject = model;
		this.saveLocation = model.getStorageLocation();
	}
	
	public Serializer(File storageLocation) {
		this.saveLocation = storageLocation;
	}
	
	public void Serialize(File Location) throws NotSerializableException, IOException {
		if (!this.modelObject.getClass().isAssignableFrom(Serializable.class)||this.hasModel==false) {
			throw new NotSerializableException();
		}
		if (!Location.exists()) {
			Location.getParentFile().mkdirs();
			Location.createNewFile();
		}
		ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(Location));
		output.writeObject(this.modelObject);
		output.close();
	}

	public void Serialize() throws NotSerializableException, IOException, NullPointerException {
		if (!this.saveLocation.exists()) {
			throw new NullPointerException();
		}
		Serialize(this.saveLocation);
	}

	public void deSerialize(File Location) throws IOException, ClassNotFoundException {
		ObjectInputStream input = new ObjectInputStream(new FileInputStream(Location));
		Auto temp = (Auto) input.readObject();
		input.close();
		this.modelObject = temp;
	}

	public void deSerialize() throws IOException, ClassNotFoundException, NullPointerException {
		if (!this.saveLocation.exists()) {
			throw new NullPointerException();
		}
		deSerialize(this.saveLocation);
	}
	public File getStorageLocation() {
		return this.saveLocation;
	}
	public Auto getDataObject() {
		return this.modelObject;
	}
}
