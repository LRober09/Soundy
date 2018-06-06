package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.SoundInformation;

public class SoundInfoGetTest {

	// Author: Austin Spierling
	@Test
	public void testGetImage() {
		SoundInformation testObj = new SoundInformation("test", "obj");
		String res = testObj.getImage();
		assertTrue(res.equals("test"));
	}
	
	// Author: Austin Spierling
	@Test
	public void testGetSound() {
		SoundInformation testObj = new SoundInformation("test", "obj");
		String res = testObj.getSound();
		assertTrue(res.equals("obj"));
	}

}
