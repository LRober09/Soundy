package view;

import java.awt.Label;

import javafx.scene.layout.GridPane;
import ui.STextField;

public class LoginScene extends SScene {
	public LoginScene() {
		super();
		
		GridPane root = (GridPane) this.getRoot();
		Label label = new Label("Login");
		
		STextField usernameTextField = new STextField();
	}
}
