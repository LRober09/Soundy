package view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import main.Main;
import model.SettingsModel;
import model.SoundBoard;
import ui.SButton;
import ui.STextField;

public class SettingsScene extends SScene {
	/**
	 * Creates a new Settings scene
	 */
	public SettingsScene() {
		super();

		GridPane root = (GridPane) this.getRoot();

		Label title = new Label("Settings");

		SButton backButton = new SButton();
		backButton.setAlignment(Pos.TOP_LEFT);
		backButton.setText("<--");
		backButton.setOnAction(event -> Main.changeScene(SceneType.MAIN_MENU));
		root.add(title, 0, 1);
		root.add(backButton, 0, 0);
	}
}

