package main;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import view.SceneFactory;
/*
 * This acts as the main controller to connect the View and Model 
 * 
 */
public class Main extends Application{
	private static Stage stage;

	public static void main(String[] args) {
		launch(args);
	}


	@Override
	public void start(Stage stage) throws Exception {
		Main.stage = stage;
		changeScene(SceneFactory.LOADING_SCENE);
		stage.show();
		
		// All this is is a slight delay to show the loading screen then switch to the main menu.
		PauseTransition delay = new PauseTransition(Duration.seconds(2));
		delay.setOnFinished(event -> changeScene(SceneFactory.MAIN_MENU));
		delay.play();
	}
	/*
	 * This method switches what is actually being displayed
	 */
	public static void changeScene(int key) {
		stage.setScene(SceneFactory.get(key));
	}

}
