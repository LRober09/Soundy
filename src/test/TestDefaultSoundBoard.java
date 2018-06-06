package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import model.SoundBoard;
import model.SoundInformation;
import model.User;

public class TestDefaultSoundBoard {

	// Author: Brett Nelson
	@Test
	public void testDefaultSound() {
		String output = "";
		List<SoundInformation> defaultList = SoundBoard.getDefaultList();
		for (int i = 0; i < defaultList.size(); i++) {
			output += (defaultList.get(i).getImage() + defaultList.get(i).getSound());
		}
		assertEquals("res/soundboards/farm/images/Pig.jpgres/soundboards/farm/sounds/Pig.wavres/soundboards/farm/images/Cow.jpgres/soundboards/farm/sounds/Cow.wavres/soundboards/farm/images/Chicken.jpgres/soundboards/farm/sounds/Chicken.wavres/soundboards/farm/images/Dog.jpgres/soundboards/farm/sounds/Dog.wavres/soundboards/farm/images/Horse.jpgres/soundboards/farm/sounds/Horse.wavres/soundboards/farm/images/Fox.jpgres/soundboards/farm/sounds/Fox.wavres/soundboards/farm/images/Goat.jpgres/soundboards/farm/sounds/Goat.wav", output);
	}
	
	// Author: Brett Nelson
	@Test
	public void testDecScore() {
		User testUser = new User("user");
		testUser.setScore(400);
		testUser.decrementScore(1);
		int res = testUser.getScore();
		assertEquals(res, 399, 0);
	}

}
