package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import main.Main;
import model.Constants;
import ui.ButtonStyle;
import ui.SButton;
import ui.STextField;

public class Sandbox {

	public static Scene create() {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.getStylesheets().add("assets/style.css");
		Label title = new Label();
		title.setText("SANDBOX");
		grid.add(title, 0,1);
		
		Button backButton = new Button();
		backButton.setAlignment(Pos.TOP_LEFT);
		backButton.setText("<--");
		backButton.setOnAction(event -> Main.changeScene(SceneFactory.MAIN_MENU));
		grid.add(backButton, 0, 0);
		
		SButton primaryButton = new SButton("Primary");
		SButton successButton = new SButton("Success", ButtonStyle.SUCCESS);
		SButton warningButton = new SButton("Warning", ButtonStyle.WARNING);
		SButton dangerButton = new SButton("Danger", ButtonStyle.DANGER);
		
		STextField defaultInput = new STextField("Valid input");
		STextField invalidInput = new STextField("Invalid input", false);
		
		grid.add(primaryButton, 0, 1);
		grid.add(successButton, 0, 2);
		grid.add(warningButton, 0, 3);
		grid.add(dangerButton, 0, 4);
		grid.add(defaultInput, 1, 1);
		grid.add(invalidInput, 1, 2);
		
		
		return new Scene(grid, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
	}

}
