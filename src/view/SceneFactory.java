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

	/*
	 * 
	 * This method does the selecting. type is one of the values specified above.
	 */
	public static Scene get(SceneType type) {
		switch (type) {
		case LOADING:
			return new Loading();
		case MAIN_MENU:
			return new MainMenu();
		case CASUAL:
		case GUESSING:
		case MEMORY:
			return new Game(type);
		case SETTINGS:
			return new Settings();
		case SANDBOX:
			return new Sandbox();
		default:
			//throw new InvalidSceneTypeException(type);
			return null;
		}
	}

}
