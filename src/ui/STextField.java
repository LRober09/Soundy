package ui;

import javafx.scene.control.TextField;

/**
 * An extension of the JavaFX TextField class that allows invalid state styling
 * 
 * @author Lucas Robertson
 *
 */
public class STextField extends TextField {

	private static final String INVALID_CLASSNAME = "text-field-invalid";

	/**
	 * Default constructor
	 */
	public STextField() {
		super();
	}

	/**
	 * Creates a text field with a default valid state
	 * 
	 * @param valid
	 *            True for valid, false for invalid
	 */
	public STextField(boolean valid) {
		super();
		if (!valid) {
			this.getStyleClass().add(INVALID_CLASSNAME);
		}
	}

	/**
	 * Creates a text field with initial text content
	 * 
	 * @param text
	 *            Initial text
	 */
	public STextField(String text) {
		super(text);
	}

	/**
	 * Creates a text field with initial text content and valid state
	 * 
	 * @param text
	 *            Initial text
	 * @param valid
	 *            True for valid, false for invalid
	 */
	public STextField(String text, boolean valid) {
		super(text);

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
