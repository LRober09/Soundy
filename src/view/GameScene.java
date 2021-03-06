package view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import model.GameDriver;
import model.SettingsModel;
import model.User;
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
		String title = "Casual";
		if (type == SceneType.GUESSING) {
			title = "Guessing";
		} else if (type == SceneType.MEMORY) {
			title = "Memory";
		}
		BorderPane root = (BorderPane) this.getRoot();
		Common.addTopBar(root, title, true);
		root.setCenter(SettingsModel.getSoundboard().getView());
		Label scoreboard = new Label("Current Score: " + User.getCurrentUser().getScore());
		Label status = new Label("");
		scoreboard.setBackground(new Background(new BackgroundFill(Color.web("#2D9CDB"), new CornerRadii(25), Insets.EMPTY)));
		status.setBackground(new Background(new BackgroundFill(Color.web("#2D9CDB"), new CornerRadii(25), Insets.EMPTY)));
		scoreboard.setTextFill(Color.WHITE);
		status.setTextFill(Color.WHITE);
		GridPane g = new GridPane();
		g.add(scoreboard, 0, 1);
		g.add(status, 0, 2);
		root.setBottom(g);
		GameDriver d = new GameDriver(type, scoreboard, status);
		if (type == SceneType.GUESSING || type == SceneType.MEMORY) {
			SButton start = new SButton("Start Game");
			start.setOnAction(e -> {
				d.genNextItem();
				g.getChildren().remove(g.getChildren().indexOf(start));
			});
			g.add(start, 0, 0);
		}
	}

}
