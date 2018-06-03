package model;

import javafx.geometry.Pos;

public class Constants {
	/*
	 * Not sure all of these should be constants or not.. or if this is the best
	 * class to put them in. That can be decided later (there's not much code yet
	 * anyway)
	 */
	private static int screenWidth = 1000;
	private static int screenHeight = 500;
	private static double zoom = 1;
	
	public static final Pos DEFAULT_POS = Pos.CENTER;

	private Constants() {
	}
	public static void setZoom(double z) {
		zoom = z;
	}
	public static double getZoom() {
		return zoom;
	}
	public static int getSCREEN_WIDTH() {
		return (int)(screenWidth * zoom);
	}
	public static int getSCREEN_HEIGHT() {
		return (int)(screenHeight * zoom);
	}

}
