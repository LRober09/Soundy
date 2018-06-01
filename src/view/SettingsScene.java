package view;

import javafx.scene.layout.BorderPane;

public class SettingsScene extends SScene {
	/**
	 * Creates a new Settings scene
	 */
	public SettingsScene() {
		super();

		BorderPane root = (BorderPane) this.getRoot();
		Common.addTopBar(root, "Settings", true);
	}
}

