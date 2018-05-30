package model;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.control.Button;
import javafx.scene.media.MediaPlayer;
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
		boolean good = false;
		if(tempSequence.remove(0).equals(b)) {
			System.out.println("continue");
			good = true;
		} 
		if(!good) {
			System.out.println("you messed up!");
		}
		if(tempSequence.size() == 0) {
			System.out.println("next level");
			genNextItem();
			return;
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
	private synchronized void waitForSound() {
		while(SoundBoard.done == false) {
			//System.out.println("twiddling");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private void playSequence() {
		ArrayList<MediaPlayer> list = new ArrayList<MediaPlayer>();
		for(Button b : sequence) {
			list.add(SettingsModel.soundboard.getplayer(b));
		}
		for(int i = 0; i < list.size(); i++) {
			final int j = i;
			list.get(i).setOnEndOfMedia(new Runnable() {
				@Override
				public void run() {
					if(j + 1 < list.size()) {
						list.get(j+1).play();
					}
				}
			});
		}
		list.get(0).play();
		
	}
}
