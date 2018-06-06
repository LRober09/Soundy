package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.SoundInformation;

public class TestSoundInfoSet {

	// Author: Henry Bowman
	@Test
	public void testSetImage() {
		SoundInformation testObj = new SoundInformation("test", "obj");
		testObj.setImage("update");
		String res = testObj.getImage();
		assertTrue(res.equals("update"));
	}
	
	// Author: Henry Bowman
	@Test
	public void testSetSound() {
		SoundInformation testObj = new SoundInformation("test", "obj");
		testObj.setSound("update");
		String res = testObj.getSound();
		assertTrue(res.equals("update"));
	}

}
