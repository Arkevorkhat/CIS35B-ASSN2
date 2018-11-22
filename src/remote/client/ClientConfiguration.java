package remote.client;

import java.io.Serializable;

public class ClientConfiguration implements Serializable {

	//members
	private static final long serialVersionUID = 112118l;
	public Class<?> parent = Client.class;
	public int Version;
}
