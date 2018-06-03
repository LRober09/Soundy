package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import model.Constants;
import model.SettingsModel;

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
		super(buildBoarder(), Constants.getSCREEN_WIDTH(), Constants.getSCREEN_HEIGHT());
	}
	
	private static BorderPane buildBoarder() {

		
		BorderPane bp = new BorderPane();
		bp.getStylesheets().add("assets/style.css");
		bp.setBackground(SettingsModel.getBG()); 
		return bp;
	}

	/**
	 * Creates a new SScene with the specified layout positioning
	 * @param layout JavaFX Pos layout
	 */
	public SScene(Pos layout) {
		super(buildGrid(layout), Constants.getSCREEN_WIDTH(), Constants.getSCREEN_HEIGHT());
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
		grid.setVgap(10);
		grid.setHgap(10);
		return grid;
	}
}
