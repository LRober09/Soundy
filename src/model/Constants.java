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
	private static int zoom = 1;
	
	public static final Pos DEFAULT_POS = Pos.CENTER;

	private Constants() {
	}
	public static void setZoom(int z) {
		zoom = z;
	}
	public static int getZoom() {
		return zoom;
	}
	public static int getSCREEN_WIDTH() {
		return screenWidth * zoom;
	}
	public static int getSCREEN_HEIGHT() {
		return screenHeight * zoom;
	}

}
