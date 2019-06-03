package pe.com.belatrix.logger;

import java.text.DateFormat;
import java.util.Date;

import org.apache.logging.log4j.util.Strings;

import pe.com.belatrix.logger.config.BelatrixConfiguration;
import pe.com.belatrix.logger.enums.MessageType;
import pe.com.belatrix.logger.exception.MessageException;
import pe.com.belatrix.logger.interfaces.LoggerInterface;
import pe.com.belatrix.logger.resources.LoggerDatabaseManager;

public class LoggerDatabase implements LoggerInterface {
	
	private LoggerDatabaseManager manager;
	
	public LoggerDatabase(BelatrixConfiguration configuration) {
		this.manager = LoggerDatabaseManager.getInstance(configuration);
	}
	
	public void addMessage(String message) {
		if (Strings.isBlank(message)) {
			throw new MessageException("Message must be specified");
		}
		String errorMessage = "message " + DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) + message;
		this.manager.insertMessageBD(errorMessage, MessageType.MESSAGE.getId());
	}

	public void addWarning(String message) {
		if (Strings.isBlank(message)) {
			throw new MessageException("Warning must be specified");
		}
		String errorMessage = "warning " + DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) + message;
		this.manager.insertMessageBD(errorMessage, MessageType.WARNING.getId());
	}

	public void addError(String message) {
		if (Strings.isBlank(message)) {
			throw new MessageException("Error must be specified");
		}
		String errorMessage = "error " + DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) + message;
		this.manager.insertMessageBD(errorMessage, MessageType.ERROR.getId());
	}

}