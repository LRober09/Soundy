package view;

import javafx.scene.Scene;

/*
 * This class is a utility to select scenes across the app
 * 
 */
public class SceneFactory {
	private SceneFactory() {
		
	}
	
	/*
	 * 
	 * These are the unique identifiers for the scenes.
	 * 
	 */
	public static final int LOADING_SCENE = 0;
	public static final int MAIN_MENU = 1;
	protected static final int CASUAL = 2;
	protected static final int GUESSING = 3;
	protected static final int SETTINGS = 4;
	protected static final int MEMORY = 5;
	protected static final int SANDBOX = 6;

	/*
	 * 
	 * This method does the selecting. type is one of the values specified above.
	 */
	public static Scene get(int type) {
		switch (type) {
		case LOADING_SCENE:
			return Loading.create();
		case MAIN_MENU:
			return MainMenu.create();
		case CASUAL:
		case GUESSING:
		case MEMORY:
			return Game.create(type);
		case SETTINGS:
			return Settings.create();
		case SANDBOX:
			return Sandbox.create();
		default:
			return null;

		}
	}

}
