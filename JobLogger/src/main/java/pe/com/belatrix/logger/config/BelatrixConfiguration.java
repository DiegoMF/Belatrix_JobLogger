package pe.com.belatrix.logger.config;

import java.io.Serializable;

public abstract class BelatrixConfiguration implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public abstract String getProperty(final String property);

}
