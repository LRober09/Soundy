package view;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.Constants;
import model.SoundBoard;
import model.SoundInformation;

public class SoundBoardView extends GridPane {
	private List<Button> buttons;
	private List<String> locations;

	public SoundBoardView(SoundBoard model) {
		super();
		int where = 0;
		setButtons(new ArrayList<>());
		setLocations(new ArrayList<>());
		for (SoundInformation info : model.getData()) {
			String image = info.getImage();
			String sound = info.getSound();
			File f = new File(image);
			Image i = new Image(f.toURI().toString());
			ImageView iv = new ImageView(i);
			iv.setFitWidth(100 * Constants.getZoom());
			iv.setFitHeight(100 * Constants.getZoom());
			Button cur = new Button("", iv);
			cur.setOnAction(event -> model.playSound(sound));			
			this.add(cur, where++, 0);
			getButtons().add(cur);
			getLocations().add(image);
		}
		this.setAlignment(Pos.CENTER);
	}

	public List<Button> getButtons() {
		return buttons;
	}
	
	public void refreshZoom() {
		for (Button b : buttons) {
			ImageView iv = (ImageView)b.getGraphic();
			iv.setFitWidth(100 * Constants.getZoom());
			iv.setFitHeight(100 * Constants.getZoom());
			b.setGraphic(iv);
		}
	}
	
	public void setButtons(List<Button> buttons) {
		this.buttons = buttons;
	}

	public List<String> getLocations() {
		return locations;
	}

	public void setLocations(List<String> locations) {
		this.locations = locations;
	}
}
