package model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import view.SoundBoardView;

public class SoundBoard {
	private static String[] defaultList = { "pig", "cow", "chicken", "dog", "horse" };
	private SoundBoardView view;
	private List<SoundInformation> data;

	private static final String SOUNDBOARD_DIRECTORY = "res/soundboards/";

	public SoundBoard(ArrayList<SoundInformation> data) {
		this.data = data;
		this.view = new SoundBoardView(this);
	}

	public SoundBoardView getView() {
		return view;
	}

	public static List<SoundInformation> getDefaultList() {
		return listToInfo(defaultList, "farm");
	}

	public static List<SoundInformation> listToInfo(String[] list, String name) {
		ArrayList<SoundInformation> info = new ArrayList<>();
		for (String item : list) {

			info.add(new SoundInformation(SOUNDBOARD_DIRECTORY + name + "/images/" + item + ".jpg",
					SOUNDBOARD_DIRECTORY + name + "/sounds/" + item + ".wav"));
		}
		return info;
	}

	public void playSound(String sound) {
		Media media = new Media(new File(sound).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.play();
	}

	public List<SoundInformation> getData() {
		return data;
	}

	public static List<SoundInformation> nameToInfo(String name) {
		File f = new File(SOUNDBOARD_DIRECTORY + name + "/images");
		File[] files = f.listFiles();
		String[] list = new String[files.length];
		for (int i = 0; i < files.length; i++) {
			list[i] = files[i].getName().split("\\.")[0];
		}
		return listToInfo(list, name);
	}

	public static List<SoundInformation> pathListToInfo(List<String[]> list) {
		ArrayList<SoundInformation> info = new ArrayList<>();
		for (String[] item : list) {

			info.add(new SoundInformation(item[0], item[1]));
		}
		return info;
	}

	public static boolean done = false;

	public static boolean isDone() {
		return done;
	}

	public void playSound(Button b) {
		int i = getView().buttons.indexOf(b);
		Media media = new Media(new File(data.get(i).sound).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.play();
	}

	public MediaPlayer getplayer(Button b) {
		int i = getView().buttons.indexOf(b);
		Media media = new Media(new File(data.get(i).sound).toURI().toString());
		return new MediaPlayer(media);
	}
}
