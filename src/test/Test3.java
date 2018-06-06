package test;

import static org.junit.Assert.*;

import javax.swing.JFrame;

import org.junit.Test;

import javafx.embed.swing.JFXPanel;
import javafx.scene.layout.BorderPane;

import view.Common;

public class Test3 {

	// Author: Henry Bowman
	@Test
	public void test() {
		JFrame frame = new JFrame("FX");
		JFXPanel fxPanel = new JFXPanel();
		frame.add(fxPanel);
		BorderPane bp = new BorderPane();
		String name = "name";
		boolean back = false;
		boolean res = Common.addTopBar(bp, name, back);
		assertTrue(res);
	}
	
	// Author: Henry Bowman
	@Test
	public void test2() {
		JFrame frame = new JFrame("FX");
		JFXPanel fxPanel = new JFXPanel();
		frame.add(fxPanel);
		BorderPane bp = new BorderPane();
		String name = "name";
		boolean back = true;
		boolean res = Common.addTopBar(bp,  name,  back);
		assertTrue(res);
	}
}
