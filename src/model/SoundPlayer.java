package model;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundPlayer {
	private static final String BASE = "res/Sounds/Scenes/";
	private SoundPlayer() {
		
	}
	public static boolean play(String string) {
		Media media = new Media(new File(BASE + string + ".wav").toURI().toString());
		Constants.setMp(new MediaPlayer(media));
		Constants.getMp().play();
		return true;
	}
	
}
