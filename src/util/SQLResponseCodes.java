package util;

public enum SQLResponseCodes {
	SUCCESS (0),
	ENTRY_EXISTS (1),
	SQL_EXCEPTION (2),
	DB_LOCKED (3);
	
	private final int code;
	
	private SQLResponseCodes(int code) {
		this.code = code;
	}
	
	public int getValue() {
		return this.code;
	}
}
