package model;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.control.Button;
import view.SceneType;
public class GameDriver {
	private ArrayList<Button> sequence;
	private ArrayList<Button> tempSequence;
	public GameDriver(SceneType type) {
		sequence = new ArrayList<Button>();
		tempSequence = new ArrayList<Button>();
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
		for(Button b : SettingsModel.soundboard.getView().buttons) {
			b.setOnAction(e -> SettingsModel.soundboard.playSound(b));
		}		
	}

	private void initMemoryDriver() {
		for(Button b : SettingsModel.soundboard.getView().buttons) {
			b.setOnAction(e -> memClick(b));
		}
		genNextItem();
	}

	private void memClick(Button b) {
		if(tempSequence.size() == 0) {
			System.out.println("next level");
			genNextItem();
			return;
		}
		if(tempSequence.remove(0).equals(b)) {
			System.out.println("continue");
		} else {
			
		}
	}

	private void initGuessingDriver() {
		for(Button b : SettingsModel.soundboard.getView().buttons) {
			b.setOnAction(e -> guessClick(b));
		}
		genNextItem();
	}

	private void guessClick(Button b) {
		if(sequence.remove(0).equals(b)) {
			System.out.println("good");
			genNextItem();
		} else {
			System.out.println("bad");
		}
	}

	private void genNextItem() {
		Random rand = new Random();
		int button = rand.nextInt(SettingsModel.soundboard.getView().buttons.size());
		System.out.println(button);
		sequence.add(SettingsModel.soundboard.getView().buttons.get(button));
		tempSequence.addAll(sequence);
		playSequence();
	}

	private void playSequence() {
		for(Button b : sequence) {
			SettingsModel.soundboard.playSound(b);
			while(!SoundBoard.isDone()) {
				
			}
		}
	}
}
