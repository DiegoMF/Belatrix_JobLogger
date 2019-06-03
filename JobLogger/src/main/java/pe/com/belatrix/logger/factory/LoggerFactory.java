package pe.com.belatrix.logger.factory;

import pe.com.belatrix.logger.LoggerConsole;
import pe.com.belatrix.logger.LoggerDatabase;
import pe.com.belatrix.logger.LoggerFile;
import pe.com.belatrix.logger.config.BelatrixConfiguration;
import pe.com.belatrix.logger.config.DefaultConfiguration;
import pe.com.belatrix.logger.enums.LoggerType;
import pe.com.belatrix.logger.exception.LoggerTypeException;
import pe.com.belatrix.logger.interfaces.LoggerInterface;

public class LoggerFactory {

	private LoggerFactory() {
		super();
	}

	public static LoggerInterface getLogger(String type) {
		if (LoggerType.FILE.getType().equals(type)) {
			return new LoggerFile(new DefaultConfiguration());
		} else if (LoggerType.CONSOLE.getType().equals(type)) {
			return new LoggerConsole(new DefaultConfiguration());
		} else if (LoggerType.DATABASE.getType().equals(type)) {
			return new LoggerDatabase(new DefaultConfiguration());
		} else {
			throw new LoggerTypeException("Invalid configuration | Logger type not valid!");
		}
	}

	public static LoggerInterface getLogger(String type, BelatrixConfiguration configuration) {
		if (LoggerType.FILE.getType().equals(type)) {
			return new LoggerFile(configuration);
		} else if (LoggerType.CONSOLE.getType().equals(type)) {
			return new LoggerConsole(configuration);
		} else if (LoggerType.DATABASE.getType().equals(type)) {
			return new LoggerDatabase(configuration);
		} else {
			throw new LoggerTypeException("Logger type not valid!");
		}
	}

}
