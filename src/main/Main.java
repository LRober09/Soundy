package main;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.SQLite;
import model.SettingsModel;
import model.SoundBoard;
import model.SoundInformation;
import model.User;
import view.SceneFactory;
import view.SceneType;
import view.SoundBoardView;

/*
 * This acts as the main controller to connect the View and Model 
 * 
 */
public class Main extends Application {
	private static Stage stage;
	private static final Logger logger = Logger.getLogger(Main.class.getName());
	
	public static void main(String[] args) {
		initApp();
		launch(args);
	}
	private static void initApp() {
		/*TODO: put this default somewhere else!*/
		ArrayList<SoundInformation> defaultSounds = new ArrayList<SoundInformation>();
		defaultSounds.add(new SoundInformation("res/images/farm/pig.jpg", "res/sounds/farm/pig.wav"));
		defaultSounds.add(new SoundInformation("res/images/farm/pig.jpg", "res/sounds/farm/pig.wav"));
		SettingsModel.soundboard = new SoundBoard(defaultSounds);
	}
	@Override
	public void start(Stage stage) throws Exception {
		Main.stage = stage;
		changeScene(SceneType.LOADING);

		stage.show();

		// All this is is a slight delay to show the loading screen then switch to the
		// main menu.
		PauseTransition delay = new PauseTransition(Duration.seconds(0.5));
		delay.setOnFinished(event -> changeScene(SceneType.LOGIN));
		delay.play();

	}

	@Override
	public void stop() {
		// Remove current user's token on application exit
		if (User.getCurrentUser() != null) {
			System.out.println("here");
			try {
				SQLite.clearUserToken(User.getCurrentUser().getUsername()).getValue();
			} catch (Exception e) {
				logger.log(Level.SEVERE, e.getMessage());
			}
		}
	}

	/*
	 * This method switches what is actually being displayed
	 */
	public static void changeScene(SceneType key) {
		stage.setScene(SceneFactory.get(key));
	}

}
