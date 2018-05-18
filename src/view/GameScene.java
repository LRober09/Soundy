package view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import main.Main;
import model.SettingsModel;
import ui.SButton;

public class GameScene extends SScene {
	
	/**
	 * Creates a new game scene with the specified type
	 * @param type The scene type (must be one of the GameTypes in SceneType)
	 */
	public GameScene(SceneType type) {
		super();
		
		GridPane grid = (GridPane) this.getRoot();
		
		Label title = new Label();
		title.setText("Game: " + type.getValue());
		grid.add(title, 0, 1);
		
		SButton backButton = new SButton();
		backButton.setAlignment(Pos.TOP_LEFT);
		backButton.setText("<--");
		backButton.setOnAction(event -> Main.changeScene(SceneType.MAIN_MENU));
		grid.add(backButton, 0, 0);
		grid.add(SettingsModel.soundboard.getView(), 1, 1);
	}

}
