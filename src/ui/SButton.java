package ui;

import javafx.scene.control.Button;

public class SButton extends Button {
	public SButton() {
		super();
		this.getStyleClass().add("button-primary");
	}
	
	public SButton(String text) {
		super(text);
		this.getStyleClass().add("button-primary");
	}

	public SButton(ButtonStyle style) {
		super();
		this.getStyleClass().add(style.getValue());
	}
	
	public SButton(String text, ButtonStyle style) {
		super(text);
		this.getStyleClass().add(style.getValue());
	}
}
