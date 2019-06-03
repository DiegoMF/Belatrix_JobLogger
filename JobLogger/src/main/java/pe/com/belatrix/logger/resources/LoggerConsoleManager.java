package pe.com.belatrix.logger.resources;

import java.io.Serializable;
import java.util.logging.ConsoleHandler;

import pe.com.belatrix.logger.config.BelatrixConfiguration;

public class LoggerConsoleManager implements Serializable {

	private static final long serialVersionUID = 1L;
	private static LoggerConsoleManager instance = null;

	private LoggerConsoleManager(BelatrixConfiguration configuration) {
		super();
	}

	public static synchronized LoggerConsoleManager getInstance(BelatrixConfiguration configuration) {
		if (instance == null) {
			instance = new LoggerConsoleManager(configuration);
		}
		return instance;
	}

	public ConsoleHandler getConsoleHandler() {
		return new ConsoleHandler();
	}

}
