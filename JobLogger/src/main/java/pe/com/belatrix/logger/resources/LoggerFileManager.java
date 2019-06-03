package pe.com.belatrix.logger.resources;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import pe.com.belatrix.logger.config.BelatrixConfiguration;
import pe.com.belatrix.logger.exception.HandlerException;
import pe.com.belatrix.logger.exception.LogFileException;

public class LoggerFileManager implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger("pe.com.belatrix.logger.session.resources.LoggerFileManager");
	private BelatrixConfiguration config;

	public LoggerFileManager(BelatrixConfiguration config) {
		super();
		this.config = config;
	}

	public File getLogFile() {
		boolean boolFile = false;
		File logFile = new File(this.config.getProperty("logger.logFilePath"));
		if (!logFile.exists()) {
			try {
				boolFile = logFile.createNewFile();
				LOGGER.log(Level.INFO, "File created: {0}", boolFile);
			} catch (IOException e) {
				throw new LogFileException("Error create new log file.", e);
			}
		}
		return logFile;
	}

	public FileHandler getFileHandler() {
		try {
			this.getLogFile();
			return new FileHandler(this.config.getProperty("logger.logFilePath"));
		} catch (SecurityException | IOException e) {
			throw new HandlerException("Error get file handler.", e);
		}
	}
}
