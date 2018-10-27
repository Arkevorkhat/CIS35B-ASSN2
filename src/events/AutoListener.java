package events;

import java.lang.annotation.Annotation;

/**
 * 
 * @author Arkev
 * Do not use until Assn. 4.
 */
public class AutoListener implements Annotation {

	@Override
	public Class<? extends Annotation> annotationType(){
		return this.getClass();
	}
}
