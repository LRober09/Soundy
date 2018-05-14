package view;

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
	public static SScene get(SceneType type) {
		switch (type) {
		case LOGIN:
			return new LoginScene();
		case LOADING:
			return new LoadingScene();
		case MAIN_MENU:
			return new MainMenuScene();
		case CASUAL:
		case GUESSING:
		case MEMORY:
			return new GameScene(type);
		case SETTINGS:
			return new SettingsScene();
		case SANDBOX:
			return new Sandbox();
		default:
			return null;
		}
	}

}
