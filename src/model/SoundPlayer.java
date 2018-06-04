package model;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundPlayer {
	private static final String BASE = "res/Sounds/Scenes/";
	private SoundPlayer() {
		
	}
	private static MediaPlayer mediaPlayer;
	public static void play(String string) {
		Media media = new Media(new File(BASE + string + ".wav").toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.play();
	}
	
}
