package test;

import static org.junit.Assert.*;

import javax.swing.JFrame;

import org.junit.Test;

import javafx.embed.swing.JFXPanel;
import model.SoundPlayer;

public class Test4 {

	// Author: Lucas Robertson
	@Test
	public void test() {
		JFrame frame = new JFrame("FX");
		JFXPanel fxPanel = new JFXPanel();
		frame.add(fxPanel);
		String p = "Casual";
		assertTrue(SoundPlayer.play(p));
	}
}
