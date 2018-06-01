package view;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import model.BoardCreator;
import model.SoundBoard;
import ui.SButton;
import ui.STextField;

public class BoardCreatorScene extends SScene {
	private STextField name;
	private GridPane center;
	public BoardCreatorScene() {
		super();
		BoardCreator.pathlist = new ArrayList<String[]>();
		BorderPane root = (BorderPane) this.getRoot();
		Common.addTopBar(root, "Board Create", true);
		root.setBottom(selectorBottomBar());
		root.setCenter(createCenter());
	}
	
	private Node createCenter() {
		center = new GridPane();
		center.setAlignment(Pos.CENTER);
		return center;
	}

	private BorderPane selectorBottomBar() {
		BorderPane bp = new BorderPane();
		SButton save = new SButton("save");
		save.setOnAction(event -> {
			try {
				BoardCreator.saveBoard(name.getText());
			} catch (IOException e) {
				name.setText("Error Creating Board: " + e.getMessage());

			}
		});
		save.setAlignment(Pos.CENTER);
		
		name = new STextField("name", true);
		GridPane g = new GridPane();
		createSelector(g);
		g.setAlignment(Pos.CENTER);
		bp.setTop(g);
		name.setAlignment(Pos.CENTER);
		name.setMaxWidth(300);
		bp.setCenter(name);		
		save.setAlignment(Pos.CENTER);
		bp.setBottom(save);
		BorderPane.setAlignment(save, Pos.CENTER);
		return bp;
	}

	private void createSelector(GridPane grid) {
		ArrayList<File> boards = new ArrayList<>(Arrays.asList(new File("res/soundboards").listFiles()));
		int i = 0;
		for (File board : boards) {
			SButton selectBoard = new SButton(board.getName());
			selectBoard.setOnAction(event -> updateSoundBoard(new SoundBoard(SoundBoard.nameToInfo(board.getName()))));
			grid.add(selectBoard, i++, 0);
		}
	}

	private void updateSoundBoard(SoundBoard soundBoard) {
		try {
			center.getChildren().remove(BoardCreator.soundBoard.getView());
		} catch (NullPointerException npe) {
			// this means the board is not yet set
		}
		BoardCreator.soundBoard = soundBoard;
		ArrayList<Button> buttons = soundBoard.getView().buttons;
		ArrayList<String> locations = soundBoard.getView().locations;
		for (int i = 0; i < buttons.size(); i++) {
			String location = locations.get(i);
			buttons.get(i).setOnAction(event -> addToUserBoard(location));
		}
		soundBoard.getView().setAlignment(Pos.CENTER);
		center.add(soundBoard.getView(), 0, 1);
	}

	private void addToUserBoard(String loc) {
		for(int i = 0; i < BoardCreator.pathlist.size(); i++) {
			if(BoardCreator.getName(BoardCreator.pathlist.get(i)[0]).equals(BoardCreator.getName(loc))){
				BoardCreator.pathlist.remove(i);
				break;
			}
		}
		BoardCreator.pathlist.add(BoardCreator.getPaths(loc));
		SoundBoard board = new SoundBoard(SoundBoard.pathListToInfo((ArrayList<String[]>) BoardCreator.pathlist));
		BoardCreator.newBoard = board;
		board.getView().setAlignment(Pos.CENTER);
		center.add(board.getView(), 0, 0);
	}
}
