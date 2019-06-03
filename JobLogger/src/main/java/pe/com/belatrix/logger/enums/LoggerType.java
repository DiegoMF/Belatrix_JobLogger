package pe.com.belatrix.logger.enums;

public enum LoggerType {
	
	CONSOLE ("console"), 
	DATABASE ("database"), 
	FILE ("file");
	
	private String type;
	
	private LoggerType(final String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
	

}
