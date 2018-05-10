package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import main.Main;
import model.Constants;

public class Game {

	public static Scene create(int type) {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		Label title = new Label();
		title.setText("GAME");
		grid.add(title, 0,1);
		
		Button backButton = new Button();
		backButton.setAlignment(Pos.TOP_LEFT);
		backButton.setText("<--");
		backButton.setOnAction(event -> Main.changeScene(SceneFactory.MAIN_MENU));
		grid.add(backButton, 0, 0);
		return new Scene(grid, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
	}

}
