package view;

import java.io.File;
import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.SoundBoard;
import model.SoundInformation;

public class SoundBoardView extends GridPane {
	public ArrayList<Button> buttons;
	public ArrayList<String> locations;

	public SoundBoardView(SoundBoard model) {
		super();
		int where = 0;
		buttons = new ArrayList<Button>();
		locations = new ArrayList<String>();
		for (SoundInformation info : model.getData()) {
			String image = info.image;
			String sound = info.sound;
			File f = new File(image);
			Image i = new Image(f.toURI().toString());
			ImageView iv = new ImageView(i);
			iv.setFitWidth(100);
			iv.setFitHeight(100);
			Button cur = new Button("", iv);
			cur.setOnAction(event -> model.playSound(sound));			
			this.add(cur, where++, 0);
			buttons.add(cur);
			locations.add(image);
		}
	}
}
