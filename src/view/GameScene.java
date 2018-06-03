package view;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import model.GameDriver;
import model.SettingsModel;
import model.User;

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
		root.setCenter(SettingsModel.getSoundboard().getView());
		Label scoreboard = new Label(""+User.getCurrentUser().getScore());
		root.setBottom(scoreboard);
		GameDriver d = new GameDriver(type, scoreboard);
		d.genNextItem();
	}

}
