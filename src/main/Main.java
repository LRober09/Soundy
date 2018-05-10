package main;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import view.SceneFactory;
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
		PauseTransition delay = new PauseTransition(Duration.seconds(2));
		delay.setOnFinished(event -> changeScene(SceneFactory.MAIN_MENU));
		delay.play();
	}

	public static void changeScene(int key) {
		stage.setScene(SceneFactory.get(key));
	}

}
