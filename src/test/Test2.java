package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.User;

public class Test2 {

	// Author: David James
	@Test
	public void test() {
		User testUser = new User("testUser");
		assertTrue(User.setCurrentUser(testUser));
	}

	// Author: David James
	@Test
	public void test2() {
		User testUser = new User("testUser");
		assertFalse(testUser.authenticate("testtest"));
	}
}
