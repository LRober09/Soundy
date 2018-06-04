package view;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.geometry.Insets;
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
		BoardCreator.setPathList(new ArrayList<String[]>());
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
		Insets split = new Insets(5);
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
		BorderPane.setMargin(g, split);
		name.setAlignment(Pos.CENTER);
		name.setMaxWidth(300);
		bp.setCenter(name);		
		BorderPane.setMargin((Node)name, split);
		save.setAlignment(Pos.CENTER);
		bp.setBottom(save);
		BorderPane.setMargin((Node)save, split);
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
			grid.setHgap(5);
		}
	}

	private void updateSoundBoard(SoundBoard soundBoard) {
		try {
			center.getChildren().remove(BoardCreator.getSoundBoard().getView());
		} catch (NullPointerException npe) {
			// this means the board is not yet set
		}
		BoardCreator.setSoundBoard(soundBoard);
		ArrayList<Button> buttons = (ArrayList<Button>) soundBoard.getView().getButtons();
		ArrayList<String> locations = (ArrayList<String>) soundBoard.getView().getLocations();
		for (int i = 0; i < buttons.size(); i++) {
			String location = locations.get(i);
			buttons.get(i).setOnAction(event -> addToUserBoard(location));
		}
		soundBoard.getView().setAlignment(Pos.CENTER);
		center.add(soundBoard.getView(), 0, 1);
	}

	private void addToUserBoard(String loc) {
		for(int i = 0; i < BoardCreator.getPathList().size(); i++) {
			if(BoardCreator.getName(BoardCreator.getPathList().get(i)[0]).equals(BoardCreator.getName(loc))){
				BoardCreator.getPathList().remove(i);
				break;
			}
		}
		BoardCreator.getPathList().add(BoardCreator.getPaths(loc));
		SoundBoard board = new SoundBoard(SoundBoard.pathListToInfo((ArrayList<String[]>) BoardCreator.getPathList()));
		BoardCreator.setNewBoard(board);
		board.getView().setAlignment(Pos.CENTER);
		center.add(board.getView(), 0, 0);
		center.setVgap(20);
	}
}
