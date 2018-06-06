package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.User;

public class TestUserScore {

	// Author: David James
	@Test
	public void testGetScore() {
		User testUser = new User("user");
		int res = testUser.getScore();
		assertEquals(res, 0, 0);
	}
	
	// Author: David James
	@Test 
	public void testSetScore() {
		User testUser = new User("user");
		testUser.setScore(400);
		int res = testUser.getScore();
		assertEquals(res, 400, 0);
	}

}
