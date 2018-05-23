package view;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import main.Main;
import model.BoardCreator;
import model.SettingsModel;
import model.SoundBoard;
import ui.SButton;
import ui.STextField;

public class BoardCreatorScene extends SScene {
	private STextField name;
	public BoardCreatorScene() {
		super();
		BoardCreator.pathlist = new ArrayList<String[]>();
		GridPane grid = (GridPane) this.getRoot();

		Label title = new Label();
		title.setText("Board Select");
		// grid.add(title, 0, 1);
		
		SButton backButton = new SButton();
		backButton.setAlignment(Pos.TOP_LEFT);
		backButton.setText("<--");
		backButton.setOnAction(event -> Main.changeScene(SceneType.MAIN_MENU));
		grid.add(backButton, 0, 0);
		SButton save = new SButton("save");
		grid.add(save, 0, 3);
		save.setOnAction(event -> {
			try {
				BoardCreator.saveBoard(name.getText());
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		createSelector(grid, 4);
		
		STextField name = new STextField("name", true);
		grid.add(name, 1, 0);
		this.name = name;


		
	}

	private void createSelector(GridPane grid, int row) {
		ArrayList<File> boards = new ArrayList<File>(Arrays.asList(new File("res/soundboards").listFiles()));
		removeUserBoards(boards);
		int i = row;
		for (File board : boards) {
			SButton selectBoard = new SButton(board.getName());
			selectBoard.setOnAction(event -> {
				updateSoundBoard(new SoundBoard(SoundBoard.nameToInfo(board.getName())));
			});
			grid.add(selectBoard, 0, i++);
		}

	}

	private void updateSoundBoard(SoundBoard soundBoard) {
		GridPane grid = (GridPane) this.getRoot();
		try {
			grid.getChildren().remove(BoardCreator.soundBoard.getView());
		} catch (NullPointerException npe) {
			// this means the board is not yet set
		}
		BoardCreator.soundBoard = soundBoard;
		ArrayList<Button> buttons = soundBoard.getView().buttons;
		ArrayList<String> locations = soundBoard.getView().locations;
		for (int i = 0; i < buttons.size(); i++) {
			String location = locations.get(i);
			buttons.get(i).setOnAction(event -> {
				addToUserBoard(location);
			});
		}
		grid.add(soundBoard.getView(), 0, 2);
	}

	private void addToUserBoard(String loc) {
		BoardCreator.pathlist.add(getPaths(loc));
		SoundBoard board = new SoundBoard(SoundBoard.pathListToInfo(BoardCreator.pathlist));
		BoardCreator.newBoard = board;
		GridPane grid = (GridPane) this.getRoot();
		grid.add(board.getView(), 0, 1);
	}

	private String[] getPaths(String loc) {
		System.out.println(loc);
		String boardName = loc.split("/")[2];
		String objectName = loc.split("/")[4].split("\\.")[0];
		String[] paths = new String[2];
		paths[0] = "res/soundboards/" + boardName + "/images/" + objectName + ".jpg";
		paths[1] = "res/soundboards/" + boardName + "/sounds/" + objectName + ".wav";
		return paths;
	}

	private void removeUserBoards(ArrayList<File> boards) {
		for (int i = 0; i < boards.size(); i++) {
			if (boards.get(i).getName().contains("USER")) {
				boards.remove(i);
			}
		}
	}

}
