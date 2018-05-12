package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import model.Constants;

/**
 * Child class of JavaFX Scene that performs basic build-up operations on
 * initialization
 * 
 * @author Lucas Robertson
 *
 */
public class SScene extends Scene {

	/**
	 * Default constructor
	 */
	public SScene() {
		super(buildGrid(Constants.DEFAULT_POS), Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
	}

	/**
	 * Initializes the root node of the scene
	 * 
	 * @param position
	 *            Default layout position
	 * @return A constructed root GridPane node
	 */
	private static GridPane buildGrid(Pos position) {
		GridPane grid = new GridPane();
		grid.setAlignment(position);
		grid.getStylesheets().add("assets/style.css");
		return grid;
	}
}