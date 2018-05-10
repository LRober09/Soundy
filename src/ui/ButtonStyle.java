package ui;

public enum ButtonStyle {
	PRIMARY ("button-primary"),
	SUCCESS ("button-success"),
	WARNING ("button-warning"),
	DANGER ("button-danger");
	
	private final String value;
	
	private ButtonStyle(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
