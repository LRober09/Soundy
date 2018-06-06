package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.User;

public class TestSetUser {

	// Author: Nicky Kyono
	@Test
	public void testSetUser() {
		User testUser = new User("user");
		User.setCurrentUser(testUser);
		User res = User.getCurrentUser();
		assertTrue(res.equals(testUser));
	}
	
	// Author: Nicky Kyono
	@Test
	public void testGetUser() {
		User res = User.getCurrentUser();
		assertNull(res);
	}

}
