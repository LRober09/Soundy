package test;

import static org.junit.Assert.*;

import org.junit.Test;

import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import model.SoundPlayer;

public class Test4 {

	// Author: Lucas Robertson
	@Test
	public void test() {
		JFXPanel fxPanel = new JFXPanel();
		String p = "Casual";
		assertTrue(SoundPlayer.play(p));
	}
}
