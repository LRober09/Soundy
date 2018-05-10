package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import main.Main;
import model.Constants;

public class MainMenu {
	//These are the keys used to handle button clicks and set the text of the buttons.
	//Might be useful to switch these into an arrayList
	private static final String CASUAL = "casual";
	private static final String MEMORY = "memory";
	private static final String GUESSING = "guessing";
	private static final String SETTINGS = "settings";
	private static final String TITLE = "SOUNDY";
	
	//This is just the structures of the buttons (the text they have and their location)
	private static final String[][] BUTTONS = {
			{CASUAL},
			{MEMORY},
			{GUESSING},
			{SETTINGS}};
	
	/*
	 * build and return the main menu scene
	 */
	public static Scene create() {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		addTitle(grid);
		addButtons(grid);
		return new Scene(grid, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
	}

	//add a title to the grid
	private static void addTitle(GridPane grid) {
		Label title = new Label();
		title.setText(TITLE);
		grid.add(title, 0, 0);
	}
	// add buttons to the grid and set up the event handler
	private static void addButtons(GridPane grid) {
		//Event handler that correctly switches the scene
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
		
		//Make the buttons and add them.
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
