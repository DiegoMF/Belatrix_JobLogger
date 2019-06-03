package pe.com.belatrix.logger.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pe.com.belatrix.logger.LoggerFile;
import pe.com.belatrix.logger.config.DefaultConfiguration;
import pe.com.belatrix.logger.enums.LoggerType;
import pe.com.belatrix.logger.exception.LoggerTypeException;
import pe.com.belatrix.logger.factory.LoggerFactory;
import pe.com.belatrix.logger.interfaces.LoggerInterface;
import pe.com.belatrix.logger.resources.LoggerFileManager;

public class JobLoggerFileTest {

	@Before
	public final void baseSetUp() {}
	
	@After
	public final void baseTearDown() {}
	
	@Test
    public void testLoggerFactory_FileType() {
		LoggerInterface logger = LoggerFactory.getLogger(LoggerType.FILE.getType());
        assertEquals(true, logger instanceof LoggerFile);
    }
	
	@Test(expected = LoggerTypeException.class)
    public void testLoggerFactory_TypeNoValid() {
		LoggerInterface logger = LoggerFactory.getLogger("noValid");
		assertNull(logger);
    }
	
	@Test
    public void testFileManager_HandlerValid() {
		LoggerFileManager fileManager = new LoggerFileManager(new DefaultConfiguration());
		assertNotNull(fileManager.getFileHandler());
    }
	
	@Test
    public void testLoggerFile_addMessageInfo() {
		LoggerInterface logger = LoggerFactory.getLogger(LoggerType.FILE.getType());
		logger.addMessage("Test - Message Info ");
        assertEquals(true, logger instanceof LoggerFile);
    }
	
	@Test
    public void testLoggerFile_addMultipleMessage() {
		LoggerInterface logger = LoggerFactory.getLogger(LoggerType.FILE.getType());
		logger.addMessage("Test Multiple - Message Info!");
		logger.addWarning("Test Multiple - Message Warning!");
        assertEquals(true, logger instanceof LoggerFile);
    }

}
