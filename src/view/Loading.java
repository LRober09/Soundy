package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import model.Constants;

public class Loading {
	public static Scene create() {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		ProgressBar pb = new ProgressBar();
		grid.add(pb, 0, 0);
		return new Scene(grid, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
	}
}
