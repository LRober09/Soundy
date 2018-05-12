package view;

import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;

public class Loading extends SScene {
	
	/**
	 * Creates a new loading scene
	 */
	public Loading() {
		super();
		
		ProgressBar pb = new ProgressBar();
		((GridPane)this.getRoot()).add(pb, 0, 0);
	}
}
