package view;

import java.io.File;
import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import main.Main;
import model.SoundInformation;

public class SoundBoardView extends GridPane{
	public SoundBoardView(ArrayList<SoundInformation> data) {
		super();
		int where = 0;
		for(SoundInformation info : data) {
			String image = info.image;
			String sound = info.sound;
			File f = new File(image);
			Image i = new Image(f.toURI().toString());
			Button cur = new Button("", new ImageView(i));
			
			
			
			cur.setOnAction(event -> playSound(sound));
			this.add(cur, where++, 0);
		}
	}
	private void playSound(String sound) {
		Media media = new Media(new File(sound).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.play();
	}

}
