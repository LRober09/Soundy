package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Authentication;
import util.SQLResponseCodes;

public class TestAuthentication {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testRegisterUserDoesntFail() {
		SQLResponseCodes result = Authentication.registerUser("TestUser", "password");
		
		assertNotEquals("", result);
	}
	
	@Test
	public void testAuthenticateDoesntFail() {
		String result = Authentication.authenticate("TestUser", "password");
		
		assertNotEquals("", result);
	}
	


}
