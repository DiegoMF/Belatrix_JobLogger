package pe.com.belatrix.logger.resources;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import pe.com.belatrix.logger.config.BelatrixConfiguration;
import pe.com.belatrix.logger.exception.MyRuntimeException;

public class LoggerDatabaseManager implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger("pe.com.belatrix.logger.session.resources.LoggerDatabaseManager");
	private static LoggerDatabaseManager instance = null;
	private BelatrixConfiguration configuration;

	private LoggerDatabaseManager(BelatrixConfiguration configuration) {
		this.configuration = configuration;
	}

	public static synchronized LoggerDatabaseManager getInstance(BelatrixConfiguration configuration) {
		if (instance == null) {
			instance = new LoggerDatabaseManager(configuration);
		}
		return instance;
	}

	public Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName(configuration.getProperty("logger.bd.jdbcDriver"));
			String userName = configuration.getProperty("logger.bd.userName");
			String password = configuration.getProperty("logger.bd.password");
			connection = DriverManager.getConnection(configuration.getProperty("logger.bd.dbUrl"), userName, password);
		} catch (SQLException | ClassNotFoundException e) {
			throw new MyRuntimeException("Connection DB error.", e);
		}
		return connection;
	}

	public Statement getStatment(Connection connection) {
		try {
			return connection.createStatement();
		} catch (SQLException e) {
			throw new MyRuntimeException("Get Statment Connection error.", e);
		}
	}

	public void createLogTable() {
		Connection connection = null;
		Statement statement = null;
		try {
			connection = getConnection();
			statement = getStatment(connection);
			statement.executeUpdate("create table IF NOT EXISTS tbLogger(message varchar(255), type int)");
		} catch (SQLException e) {
			throw new MyRuntimeException("Create log table BD error.", e);
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					LOGGER.log(Level.WARNING, "statement null: {0}", e);
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					LOGGER.log(Level.WARNING, "connection null: {0}", e);
				}
			}
		}
	}

	public void insertMessageBD(String message, int messageType) {
		Connection connection = null;
		Statement statement = null;
		try {
			connection = getConnection();
			statement = getStatment(connection);
			statement.execute("INSERT INTO tbLogger(message, type) VALUES('" + message + "', " + messageType + ")");
		} catch (SQLException e) {
			throw new MyRuntimeException("Insert message BD error.", e);
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					LOGGER.log(Level.WARNING, "statement null: {0}", e);
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					LOGGER.log(Level.WARNING, "connection null: {0}", e);
				}
			}
		}
	}

}
