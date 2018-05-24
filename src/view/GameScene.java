package view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import main.Main;
import model.SettingsModel;
import ui.SButton;

public class GameScene extends SScene {

	/**
	 * Creates a new game scene with the specified type
	 * 
	 * @param type
	 *            The scene type (must be one of the GameTypes in SceneType)
	 */
	public GameScene(SceneType type) {
		super();

		BorderPane root = (BorderPane) this.getRoot();
		Common.addTopBar(root, "Game: " + type.getValue(), true);
		root.setCenter(SettingsModel.soundboard.getView());
	}

}
