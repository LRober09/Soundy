package model;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

public class SettingsModel {
	private static final String BASE_PATH = "res/soundboards/";
	private static SoundBoard soundboard = null;
	private static Background bg = null;
	
	private SettingsModel() {
	}
	
	public static SoundBoard getSoundboard() {
		return soundboard;
	}
	
	public static SoundBoard setSoundboard(SoundBoard soundboard) {
		String name = getBoardName(soundboard.getData().get(0).getImage());
		File f = new File(BASE_PATH + name + name.substring(0, name.length()-1) + ".png");
		if (f.exists()) {
			Image img = new Image(new File(BASE_PATH + name + name.substring(0, name.length()-1) + ".png").toURI().toString());		
		    BackgroundSize bSize = new BackgroundSize(Constants.getScreenHeight(), Constants.getScreenWidth(), false, false, true, false);
		    setBG(new Background(new BackgroundImage(img, BackgroundRepeat.ROUND,
					BackgroundRepeat.ROUND, BackgroundPosition.CENTER, bSize)));
		}
		else {
			setBG(null);
		}
		SettingsModel.soundboard = soundboard;
		return soundboard;
	}
	
	private static String getBoardName(String s) {
		return s.split("/")[2] + "/";
	}
	
	public static Background getBG() {
		return bg == null ? Background.EMPTY : bg;
	}
	
	public static boolean setBG(Background bg) {
		SettingsModel.bg = bg;
		return true;
	}
}
