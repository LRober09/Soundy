package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import main.Main;
import model.Constants;

public class MainMenu {
	private static final String CASUAL = "casual";
	private static final String MEMORY = "memory";
	private static final String GUESSING = "guessing";
	private static final String SETTINGS = "settings";
	private static final String TITLE = "SOUNDY";
	private static final String[][] BUTTONS = {
			{CASUAL},
			{MEMORY},
			{GUESSING},
			{SETTINGS}};
	public static Scene create() {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		addTitle(grid);
		addButtons(grid);
		return new Scene(grid, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
	}

	private static void addTitle(GridPane grid) {
		Label title = new Label();
		title.setText(TITLE);
		grid.add(title, 0, 0);
	}

	private static void addButtons(GridPane grid) {
		EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Button src = (Button)e.getSource();
				String which = src.getText();
				if(which.equals(CASUAL)) {
					Main.changeScene(SceneFactory.CASUAL);
				}else if(which.equals(GUESSING)) {
					Main.changeScene(SceneFactory.GUESSING);
				}else if(which.equals(MEMORY)) {
					Main.changeScene(SceneFactory.MEMORY);
				}else if(which.equals(SETTINGS)) {
					Main.changeScene(SceneFactory.SETTINGS);
				}
			}
		};
		int i = 1;
		for(String[] row : BUTTONS) {
			for(String s : row) {
				Button b = new Button();
				b.setText(s);
				b.setOnAction(buttonHandler);
				grid.add(b, 0, i);
			}
			i++;
		}
	}
}
