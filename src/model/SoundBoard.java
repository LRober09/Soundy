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
			
			info.add(new SoundInformation("res/soundboards/" + name + "/images/" + item + ".jpg", 
					"res/soundboards/" + name + "/sounds/" + item + ".wav"));
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
	public static ArrayList<SoundInformation> nameToInfo(String name) {
		File f = new File("res/soundboards/" + name + "/images");
		File[] files = f.listFiles();
		String[] list = new String[files.length];
		for(int i = 0; i < files.length; i++) {
			list[i] = files[i].getName().split("\\.")[0];
		}
		return listToInfo(list, name);
	}
	public static ArrayList<SoundInformation> pathListToInfo(ArrayList<String[]> list) {
		ArrayList<SoundInformation> info = new ArrayList<SoundInformation>();
		for(String[] item : list) {
			
			info.add(new SoundInformation(item[0], item[1]));
		}
		return info;
	}
	
	
}
