package pe.com.belatrix.logger;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.logging.log4j.util.Strings;

import pe.com.belatrix.logger.config.BelatrixConfiguration;
import pe.com.belatrix.logger.exception.MessageException;
import pe.com.belatrix.logger.interfaces.LoggerInterface;
import pe.com.belatrix.logger.resources.LoggerConsoleManager;

public class LoggerConsole implements LoggerInterface {
	
	public static final Logger LOGGER = Logger.getLogger("pe.com.belatrix.logger.LoggerConsole");
	private LoggerConsoleManager manager;
	
	public LoggerConsole(BelatrixConfiguration configuration) {
		this.manager = LoggerConsoleManager.getInstance(configuration);
		LOGGER.addHandler(this.manager.getConsoleHandler());
	}
	
	public void addMessage(String message) {
		if (Strings.isBlank(message)) {
			throw new MessageException("Message must be specified");
		}
		LOGGER.log(Level.INFO, message);
	}

	public void addWarning(String message) {
		if (Strings.isBlank(message)) {
			throw new MessageException("Message must be specified");
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