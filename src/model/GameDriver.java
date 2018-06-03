package model; 
import java.util.ArrayList;
import java.util.Random;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.MediaPlayer;
import main.Main;
import view.SceneType;
public class GameDriver {
	private ArrayList<Button> sequence;
	private ArrayList<Button> tempSequence;
	private Label score;
	private Label status;
	public GameDriver(SceneType type, Label scoreboard, Label status) {
		score = scoreboard;
		status = status;
		sequence = new ArrayList<>();
		tempSequence = new ArrayList<>();
		switch(type) {
		case GUESSING:
			initGuessingDriver();
			break;
		case MEMORY:
			initMemoryDriver();
			break;
		default:
			initCasualDriver();
			break;
		}
	}
	
	private void initCasualDriver() {
		for(Button b : SettingsModel.getSoundboard().getView().getButtons()) {
			b.setOnAction(e -> SettingsModel.getSoundboard().playSound(b));
		}		
	}

	private void initMemoryDriver() {
		for(Button b : SettingsModel.getSoundboard().getView().getButtons()) {
			b.setOnAction(e -> memClick(b));
		}
	}

	private void memClick(Button b) {
		boolean good = false;
		if(tempSequence.remove(0).equals(b)) {
			good = true;
		} 
		if(!good) {
			Main.changeScene(SceneType.MAIN_MENU);
			User.getCurrentUser().decrementScore(sequence.size() * sequence.size());
			return;
		}
		if(tempSequence.isEmpty()) {
			User.getCurrentUser().setScore(User.getCurrentUser().getScore() + sequence.size() * sequence.size());
			score.setText(""+User.getCurrentUser().getScore());
			status.setText("Level " + sequence.size());
			genNextItem();
		}
	}

	private void initGuessingDriver() {
		for(Button b : SettingsModel.getSoundboard().getView().getButtons()) {
			b.setOnAction(e -> guessClick(b));
		}
	}

	private void guessClick(Button b) {
		if(sequence.remove(0).equals(b)) {
			User.getCurrentUser().setScore(User.getCurrentUser().getScore() + 1);
			genNextItem();
			User.getCurrentUser().setScore(User.getCurrentUser().getScore() + 1);
			status.setText("you rock");
			score.setText(""+User.getCurrentUser().getScore());
		} else {
			status.setText("you suck");
			User.getCurrentUser().decrementScore(1);
		}
	}

	public void genNextItem() {
		Random rand = new Random();
		int button = rand.nextInt(SettingsModel.getSoundboard().getView().getButtons().size());
		sequence.add(SettingsModel.getSoundboard().getView().getButtons().get(button));
		tempSequence.addAll(sequence);
		playSequence();
	}

	private void playSequence() {
		ArrayList<MediaPlayer> list = new ArrayList<>();
		for(Button b : sequence) {
			list.add(SettingsModel.getSoundboard().getplayer(b));
		}
		for(int i = 0; i < list.size(); i++) {
			final int j = i;
			list.get(i).setOnEndOfMedia(() -> {
				if(j + 1 < list.size()) {
					list.get(j).dispose();
					list.get(j+1).play();
			}});
			
		}
		list.get(0).play();
	}
}
