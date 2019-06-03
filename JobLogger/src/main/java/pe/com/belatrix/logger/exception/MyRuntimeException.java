package pe.com.belatrix.logger.exception;

public class MyRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public MyRuntimeException(String message) {
        super(message);
    }
	
	public MyRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

}
