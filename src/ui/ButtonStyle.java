package ui;

/**
 * ButtonStyle enum for initializing and setting button styles
 * @author Lucas Robertson
 *
 */
public enum ButtonStyle {
	PRIMARY ("button-primary"),
	SUCCESS ("button-success"),
	WARNING ("button-warning"),
	DANGER ("button-danger");
	
	private final String value;
	
	private ButtonStyle(String value) {
		this.value = value;
	}
	
	/**
	 * Returns the text value of the button style
	 * @return A CSS class name
	 */
	public String getValue() {
		return this.value;
	}
}
