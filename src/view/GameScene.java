package view;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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
		String title = type == SceneType.GUESSING ? "Guessing" :
					   type == SceneType.MEMORY   ? "Memory"   :
						   							"Casual";
		BorderPane root = (BorderPane) this.getRoot();
		Common.addTopBar(root, title, true);
		root.setCenter(SettingsModel.getSoundboard().getView());
		Label scoreboard = new Label(""+User.getCurrentUser().getScore());
		Label status = new Label("");
		GridPane g = new GridPane();
		g.add(scoreboard,0,0);
		g.add(status,0,1);
		root.setBottom(g);
		GameDriver d = new GameDriver(type, scoreboard, status);
		d.genNextItem();
	}

}
