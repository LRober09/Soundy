package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Constants;

public class Test1 {

	// Author: Austin Spierling
	@Test
	public void test1() {
		Constants.setZoom(5);
		assertEquals(5, Constants.getZoom(), 0);
	}
	
	// Author: Austin Spierling
	@Test
	public void test2() {
		Constants.setZoom(5);
		assertEquals(5000, Constants.getScreenWidth(), 0);
	}
	
	// Author: Brett Nelson
	@Test
	public void test3() {
		Constants.setZoom(3);
		assertEquals(1500, Constants.getScreenHeight(), 0);
	}
	
	// Author: Brett Nelson
	@Test
	public void test4() {
		boolean test = Constants.setZoom(12);
		assertTrue(test);
	}

}
