package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.User;

public class TestUserAuth {

	// Author: Lucas Robertson
	@Test
	public void testUserAuth() {
		User testUser = new User("David");
		Boolean res = testUser.authenticate("password");
		assertTrue(res);
	}
	
	// Author: Lucas Robertson
	@Test
	public void testUserAuthGet() {
		User testUser = new User("David");
		testUser.authenticate("password");
		Boolean res = testUser.isAuthenticated();
		assertTrue(res);
	}

}
