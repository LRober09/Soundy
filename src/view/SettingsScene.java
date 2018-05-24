package view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
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

		BorderPane root = (BorderPane) this.getRoot();
		Common.addTopBar(root, "Settings", true);
	}
}

