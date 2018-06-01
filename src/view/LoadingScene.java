package view;

import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;

public class LoadingScene extends SScene {
	
	/**
	 * Creates a new loading scene
	 */
	public LoadingScene() {
		super();
		
		ProgressBar pb = new ProgressBar();
		((BorderPane)this.getRoot()).setCenter(pb);
	}
	
}
