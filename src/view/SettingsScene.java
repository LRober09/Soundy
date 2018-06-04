package view;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
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
		mid.setAlignment(Pos.CENTER);
		mid.add(upZoom, 0, 0);
		GridPane.setHalignment(upZoom, HPos.CENTER);
		mid.add(downZoom, 0, 1);
		GridPane.setHalignment(downZoom, HPos.CENTER);
		root.setCenter(mid);
		mid.setVgap(5);
	}
}
