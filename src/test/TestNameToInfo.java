package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import model.SoundBoard;
import model.SoundInformation;

public class TestNameToInfo {

	@Test
	public void test() {
		StringBuilder sb = new StringBuilder();
		List<SoundInformation> farmList = SoundBoard.nameToInfo("farm");
		for (int i = 0; i < farmList.size(); i++) {
			sb.append(farmList.get(i).getImage() + farmList.get(i).getSound());
		}
		assertEquals("res/soundboards/farm/images/Dog.jpgres/soundboards/farm/sounds/Dog.wavres/soundboards/farm/images/Horse.jpgres/soundboards/farm/sounds/Horse.wavres/soundboards/farm/images/Chicken.jpgres/soundboards/farm/sounds/Chicken.wavres/soundboards/farm/images/Cow.jpgres/soundboards/farm/sounds/Cow.wavres/soundboards/farm/images/Fox.jpgres/soundboards/farm/sounds/Fox.wavres/soundboards/farm/images/Pig.jpgres/soundboards/farm/sounds/Pig.wavres/soundboards/farm/images/Goat.jpgres/soundboards/farm/sounds/Goat.wav", sb.toString());
	}
	
	@Test
	public void test2() {
		StringBuilder sb = new StringBuilder();
		List<SoundInformation> jungleList = SoundBoard.nameToInfo("jungle");
		for (int i = 0; i < jungleList.size(); i++) {
			sb.append(jungleList.get(i).getImage() + jungleList.get(i).getSound());
		}
		assertEquals("res/soundboards/jungle/images/elephant.jpgres/soundboards/jungle/sounds/elephant.wavres/soundboards/jungle/images/tiger.jpgres/soundboards/jungle/sounds/tiger.wavres/soundboards/jungle/images/frog.jpgres/soundboards/jungle/sounds/frog.wavres/soundboards/jungle/images/monkey.jpgres/soundboards/jungle/sounds/monkey.wavres/soundboards/jungle/images/snake.jpgres/soundboards/jungle/sounds/snake.wav", sb.toString());
	}

}
