package model;

import java.io.File;
import java.util.ArrayList;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import view.SoundBoardView;

public class SoundBoard {
	private static String[] defaultList = {"pig", "cow", "chicken", "dog", "horse"};
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
		return listToInfo(defaultList, "farm");
	}
	public static ArrayList<SoundInformation> listToInfo(String[] list, String name) {
		ArrayList<SoundInformation> info = new ArrayList<SoundInformation>();
		for(String item : list) {
			info.add(new SoundInformation("res/images/farm/" + item + ".jpg", 
					"res/sounds/" + name + "/" + item + ".wav"));
		}
		return info;	
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
