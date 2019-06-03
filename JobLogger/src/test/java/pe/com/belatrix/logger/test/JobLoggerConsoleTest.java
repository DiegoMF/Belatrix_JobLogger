package pe.com.belatrix.logger.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import pe.com.belatrix.logger.LoggerConsole;
import pe.com.belatrix.logger.config.DefaultConfiguration;
import pe.com.belatrix.logger.enums.LoggerType;
import pe.com.belatrix.logger.factory.LoggerFactory;
import pe.com.belatrix.logger.interfaces.LoggerInterface;
import pe.com.belatrix.logger.resources.LoggerConsoleManager;

@RunWith(PowerMockRunner.class)
public class JobLoggerConsoleTest {

	@Before
	public final void baseSetUp() {}
	
	@After
	public final void baseTearDown() {}

	@Test
    public void testLoggerFactory_ConsoleType() {
		LoggerInterface logger = LoggerFactory.getLogger(LoggerType.CONSOLE.getType());
        assertEquals(true, logger instanceof LoggerConsole);
    }
	
	@Test
    public void testConsoleManager_HandlerValid() {
		LoggerConsoleManager consoleManager = LoggerConsoleManager.getInstance(new DefaultConfiguration());
		assertNotNull(consoleManager.getConsoleHandler());
    }
	
	@Test
    public void testLoggerConsole_addMessageInfo() {
		LoggerInterface logger = LoggerFactory.getLogger(LoggerType.CONSOLE.getType());
		logger.addMessage("Test Console - Message Info ");
        assertEquals(true, logger instanceof LoggerConsole);
    }
	
	@Test
    public void testLoggerConsole_addMultipleMessage() {
		LoggerInterface logger = LoggerFactory.getLogger(LoggerType.CONSOLE.getType());
		logger.addMessage("Test Multiple Console - Message Info!");
		logger.addWarning("Test Multiple Console - Message Warning!");
		logger.addError("Test Multiple Console - Message Error!");
        assertEquals(true, logger instanceof LoggerConsole);
    }
}
