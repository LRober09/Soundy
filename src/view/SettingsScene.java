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
	private STextField boardName;
	public SettingsScene() {
		super();

		GridPane root = (GridPane) this.getRoot();

		Label title = new Label("Settings");

		SButton backButton = new SButton();
		backButton.setAlignment(Pos.TOP_LEFT);
		backButton.setText("<--");
		backButton.setOnAction(event -> Main.changeScene(SceneType.MAIN_MENU));

		SButton swapBoard = new SButton();
		swapBoard.setAlignment(Pos.TOP_LEFT);
		swapBoard.setText("swapBoard");
		swapBoard.setOnAction(event -> updateSoundModel());
		
		
		boardName = new STextField("Board Name");
		
		root.add(title, 0, 1);
		root.add(backButton, 0, 0);
		root.add(swapBoard, 0, 4);
		root.add(boardName, 0, 3);
	}

	private void updateSoundModel() {
		SettingsModel.soundboard = new SoundBoard(SoundBoard.nameToInfo(boardName.getText()));
	}
}
