package view;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import main.Main;
import model.Constants;
import model.SettingsModel;
import ui.SButton;

public class SettingsScene extends SScene {
	/**
	 * Creates a new Settings scene
	 */
	public SettingsScene() {
		super();
		BorderPane root = (BorderPane) this.getRoot();
		Common.addTopBar(root, "Settings", true);
		SButton upZoom = new SButton("upZoom");
		upZoom.setOnAction(e -> {
			Constants.setZoom(Constants.getZoom() + .25);
			Main.refreshSize();
			SettingsModel.getSoundboard().getView().refreshZoom();
		});
		SButton downZoom = new SButton("downZoom");
		downZoom.setOnAction(e -> {
			Constants.setZoom(Constants.getZoom() - .25);
			Main.refreshSize();
			SettingsModel.getSoundboard().getView().refreshZoom();
		});
		GridPane mid = new GridPane();
		mid.add(upZoom, 0, 0);
		mid.add(downZoom, 0, 1);
		root.setCenter(mid);
	}
}
