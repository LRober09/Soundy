package model;

import java.io.File;
import java.util.ArrayList;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import view.SoundBoardView;

public class SoundBoard {
	private static String[] defaultList = {"pig", "cow", "chicken"};
	private SoundBoardView view;
	private ArrayList<SoundInformation> data;
	public SoundBoard(ArrayList<SoundInformation> data) {
		this.data = data;
		this.view = new SoundBoardView(this);
	}
	public SoundBoardView getView() {
		return view;
	}
	public static ArrayList<SoundInformation> getDefaultList() {
		/*TODO: put this default somewhere else!*/
		ArrayList<SoundInformation> defaultSounds = new ArrayList<SoundInformation>();
		for(String animal : defaultList) {
			defaultSounds.add(new SoundInformation("res/images/farm/" + animal + ".jpg", 
					"res/sounds/farm/" + animal + ".wav"));
		}
		return defaultSounds;
	}
	public void playSound(String sound) {
		Media media = new Media(new File(sound).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.play();
	}
	public ArrayList<SoundInformation> getData() {
		return data;
	}
}
