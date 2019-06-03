package pe.com.belatrix.logger.config;

import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DefaultConfiguration extends BelatrixConfiguration {
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger("pe.com.belatrix.logger.config.DefaultConfiguration");
	private static final String PROPERTIES_FILE = "application.properties";

	@Override
	public String getProperty(final String property) {
		String message = null;
		Properties prop = new Properties();
		InputStream input= this.getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE);
        try {
			prop.load(input);
			message = prop.getProperty(property);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
		}
		
		return message; 
	}

}