package view;

import java.io.File;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import main.Main;
import model.SettingsModel;
import model.SoundBoard;
import ui.SButton;

public class BoardSelectScene extends SScene {

	public BoardSelectScene() {
		super();
		
		GridPane grid = (GridPane) this.getRoot();

		Label title = new Label();
		title.setText("Board Select");
		grid.add(title, 0, 1);
		SButton backButton = new SButton();
		backButton.setAlignment(Pos.TOP_LEFT);
		backButton.setText("<--");
		backButton.setOnAction(event -> Main.changeScene(SceneType.MAIN_MENU));
		grid.add(backButton, 0, 0);
		createSelector(grid);
	}

	private void createSelector(GridPane grid) {
		File[] boards = new File("res/soundboards").listFiles();
		int i = 2;
		for(File board : boards) {
			SButton selectBoard = new SButton(board.getName());
			selectBoard.setOnAction(event -> {
				SettingsModel.soundboard = new SoundBoard(SoundBoard.nameToInfo(board.getName()));
			});
			grid.add(selectBoard, 0, i++);
		}
	}	

}
