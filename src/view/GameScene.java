package view;

import javafx.scene.layout.BorderPane;
import model.GameDriver;
import model.SettingsModel;

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
		GameDriver d = new GameDriver(type);
		d.genNextItem();
	}

}
