package model;

import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.media.MediaPlayer;

public class Constants {
	/*
	 * Not sure all of these should be constants or not.. or if this is the best
	 * class to put them in. That can be decided later (there's not much code yet
	 * anyway)
	 */
	private static int screenWidth = 1000;
	private static int screenHeight = 500;
	private static double zoom = 1;
	private static MediaPlayer mp;
	private static List<MediaPlayer> list;
	
	public static final Pos DEFAULT_POS = Pos.CENTER;

	private Constants() {
	}
	public static boolean setZoom(double z) {
		zoom = z;
		return true;
	}
	public static double getZoom() {
		return zoom;
	}
	public static int getScreenWidth() {
		return (int)(screenWidth * zoom);
	}
	public static int getScreenHeight() {
		return (int)(screenHeight * zoom);
	}
	public static List<MediaPlayer> getList() {
		return list;
	}
	public static void setList(List<MediaPlayer> list) {
		Constants.list = list;
	}
	public static MediaPlayer getMp() {
		return mp;
	}
	public static void setMp(MediaPlayer mp) {
		Constants.mp = mp;
	}

}
