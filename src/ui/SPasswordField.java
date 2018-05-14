package ui;

import javafx.scene.control.PasswordField;

public class SPasswordField extends PasswordField {
	private static final String INVALID_CLASSNAME = "text-field-invalid";

	/**
	 * Default constructor
	 */
	public SPasswordField() {
		super();
	}

	/**
	 * Creates a text field with a default valid state
	 * 
	 * @param valid
	 *            True for valid, false for invalid
	 */
	public SPasswordField(boolean valid) {
		super();
		if (!valid) {
			this.getStyleClass().add(INVALID_CLASSNAME);
		}
	}

	/**
	 * Sets the text field validation state
	 * 
	 * @param valid
	 *            True for valid, false for invalid
	 */
	public void setValid(boolean valid) {
		if (valid) {
			this.getStyleClass().remove(INVALID_CLASSNAME);
		} else {
			this.getStyleClass().add(INVALID_CLASSNAME);
		}
	}
}
