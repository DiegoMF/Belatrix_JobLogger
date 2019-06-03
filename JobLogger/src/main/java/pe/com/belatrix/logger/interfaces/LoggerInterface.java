package pe.com.belatrix.logger.interfaces;

public interface LoggerInterface {
	
	public void addMessage(String message);
	public void addWarning(String message);
	public void addError(String message);

}
