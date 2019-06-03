package pe.com.belatrix.logger.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pe.com.belatrix.logger.LoggerDatabase;
import pe.com.belatrix.logger.config.DefaultConfiguration;
import pe.com.belatrix.logger.enums.LoggerType;
import pe.com.belatrix.logger.enums.MessageType;
import pe.com.belatrix.logger.factory.LoggerFactory;
import pe.com.belatrix.logger.interfaces.LoggerInterface;
import pe.com.belatrix.logger.resources.LoggerDatabaseManager;

public class JobLoggerDatabaseTest {

	@Before
	public final void baseSetUp() {}
	
	@After
	public final void baseTearDown() {}
	
	@Test
    public void testLoggerFactory_DatabaseType() {
		LoggerInterface logger = LoggerFactory.getLogger(LoggerType.DATABASE.getType());
        assertEquals(true, logger instanceof LoggerDatabase);
    }
	
	@Test
    public void testDatabaseManager_BDConnectionValid() {
		LoggerDatabaseManager bdManager = LoggerDatabaseManager.getInstance(new DefaultConfiguration());
		try {
			assertTrue(bdManager.getConnection().isValid(0));
		} catch (SQLException e) {
			assertTrue(false);
		}
    }
	
	@Test
    public void testDatabaseManager_insertMessage() {
		try {
			LoggerDatabaseManager bdManager = LoggerDatabaseManager.getInstance(new DefaultConfiguration());
			bdManager.createLogTable();
			bdManager.insertMessageBD("Test insert message DB", MessageType.MESSAGE.getId());
			assertTrue(true);
		} catch (Exception e) {
			assertTrue(false);
		}
    }
	
	@Test
    public void testLoggerDatabase_addLog() {
		try {
			LoggerInterface logger = LoggerFactory.getLogger(LoggerType.DATABASE.getType());
			logger.addMessage("Test Multiple - Message Info!");
			logger.addWarning("Test Multiple - Message Warning!");
	        assertTrue(true);
		} catch (Exception e) {
			assertTrue(false);
		}
    }

}
