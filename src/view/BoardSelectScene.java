package view;

import java.io.File;

import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import model.SettingsModel;
import model.SoundBoard;
import ui.SButton;

public class BoardSelectScene extends SScene {

	public BoardSelectScene() {
		super();

		BorderPane root = (BorderPane) this.getRoot();
		Common.addTopBar(root, "Board Select", true);
		createSelector(root, 0);
	}

	private void createSelector(BorderPane root, int row) {
		GridPane grid = new GridPane();
		File[] boards = new File("res/soundboards").listFiles();
		int i = row;
		for(File board : boards) {
			SButton selectBoard = new SButton(board.getName());
			selectBoard.setOnAction(event -> {
				SettingsModel.soundboard = new SoundBoard(SoundBoard.nameToInfo(board.getName()));
			});
			grid.add(selectBoard, 0, i++);
		}
		grid.setAlignment(Pos.CENTER);
		root.setCenter(grid);
	}	

}
