package ui;

import javafx.scene.control.Button;
/**
 * SButton wraps and inherits the JavaFX button control, but allows it to be
 * initialized with styling
 * 
 * @author Lucas Robertson
 *
 */
public class SButton extends Button {
	/**
	 * Default Constructor (uses primary style)
	 */
	public SButton() {
		super();
		this.getStyleClass().add("button-primary");
	}

	/**
	 * Creates a button with the specified text as its label.
	 * 
	 * @param text
	 *            The button label
	 */
	public SButton(String text) {
		super(text);
		this.getStyleClass().add("button-primary");
	}

	/**
	 * Creates a button with the specified style
	 * 
	 * @param style
	 *            The button's style
	 */
	public SButton(ButtonStyle style) {
		super();
		this.getStyleClass().add(style.getValue());
	}

	/**
	 * Creates a button with the specified text as its label and style
	 * 
	 * @param text
	 *            The button label
	 * @param style
	 *            The button's style
	 */
	public SButton(String text, ButtonStyle style) {
		super(text);
		this.getStyleClass().add(style.getValue());
	}

	/**
	 * Replaces the button's current CSS style with the specified one
	 * @param style The button's style
	 */
	public void setCSSStyle(ButtonStyle style) {
		this.getStyleClass().remove(1);
		this.getStyleClass().add(style.getValue());
	}

	/**
	 * Removes ALL CSS classes from the button
	 */
	public void clearStyle() {
		if (this.getStyleClass().size() > 0) {
			this.getStyleClass().remove(0, this.getStyleClass().size());
		}
	}
}
