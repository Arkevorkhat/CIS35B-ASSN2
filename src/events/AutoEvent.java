package events;

import java.util.EventObject;

/**
 * @author Arkev
 * Do not use until Assn. 4.
 */
public class AutoEvent extends EventObject {
	private static final long serialVersionUID = 1L;

	public AutoEvent (Class<?> klass) {
		super(klass);
	}
}
