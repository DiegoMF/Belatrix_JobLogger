package pe.com.belatrix.logger;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.logging.log4j.util.Strings;

import pe.com.belatrix.logger.config.BelatrixConfiguration;
import pe.com.belatrix.logger.exception.MessageException;
import pe.com.belatrix.logger.interfaces.LoggerInterface;
import pe.com.belatrix.logger.resources.LoggerFileManager;

public class LoggerFile implements LoggerInterface {
	
	public static final Logger LOGGER = Logger.getLogger("pe.com.belatrix.logger.LoggerFile");
	private LoggerFileManager manager;
	
	public LoggerFile(BelatrixConfiguration configuration) {
		this.manager = new LoggerFileManager(configuration);
		LOGGER.addHandler(this.manager.getFileHandler());
	}
	
	public void addMessage(String message) {
		if (Strings.isBlank(message)) {
			throw new MessageException("Message must be specified");
		}
		LOGGER.log(Level.INFO, message);
	}

	public void addWarning(String message) {
		if (Strings.isBlank(message)) {
			throw new MessageException("Warning must be specified");
		}
		LOGGER.log(Level.WARNING, message);
	}

	public void addError(String message) {
		if (Strings.isBlank(message)) {
			throw new MessageException("Error must be specified");
		}
		LOGGER.log(Level.SEVERE, message);
	}

}
