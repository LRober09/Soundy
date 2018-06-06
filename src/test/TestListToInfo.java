package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import model.SoundBoard;
import model.SoundInformation;

public class TestListToInfo {

	@Test
	public void testSize3() {
		StringBuilder sb = new StringBuilder();
		String[] farmList = {"pig", "horse", "cow"};
		List<SoundInformation> farmSoundList = SoundBoard.listToInfo(farmList,  "farm");
		for (int i = 0; i < farmSoundList.size(); i++) {
			sb.append(farmSoundList.get(i).getImage() + farmSoundList.get(i).getSound());
		}
		assertEquals("res/soundboards/farm/images/pig.jpgres/soundboards/farm/sounds/pig.wavres/soundboards/farm/images/horse.jpgres/soundboards/farm/sounds/horse.wavres/soundboards/farm/images/cow.jpgres/soundboards/farm/sounds/cow.wav", sb.toString());
	}
	
	@Test
	public void testSize15() {
		StringBuilder sb = new StringBuilder();
		String bp = "bradPitt";
		String[] celebList = {bp, bp, bp, bp, bp, bp, bp, bp, bp, bp, bp, bp, bp, bp, bp};
		List<SoundInformation> celebSoundList = SoundBoard.listToInfo(celebList,  "celeb");
		for (int i = 0; i < celebSoundList.size(); i++) {
			sb.append(celebSoundList.get(i).getImage() + celebSoundList.get(i).getSound());
		}
		assertEquals("res/soundboards/celeb/images/bradPitt.jpgres/soundboards/celeb/sounds/bradPitt.wavres/soundboards/celeb/images/bradPitt.jpgres/soundboards/celeb/sounds/bradPitt.wavres/soundboards/celeb/images/bradPitt.jpgres/soundboards/celeb/sounds/bradPitt.wavres/soundboards/celeb/images/bradPitt.jpgres/soundboards/celeb/sounds/bradPitt.wavres/soundboards/celeb/images/bradPitt.jpgres/soundboards/celeb/sounds/bradPitt.wavres/soundboards/celeb/images/bradPitt.jpgres/soundboards/celeb/sounds/bradPitt.wavres/soundboards/celeb/images/bradPitt.jpgres/soundboards/celeb/sounds/bradPitt.wavres/soundboards/celeb/images/bradPitt.jpgres/soundboards/celeb/sounds/bradPitt.wavres/soundboards/celeb/images/bradPitt.jpgres/soundboards/celeb/sounds/bradPitt.wavres/soundboards/celeb/images/bradPitt.jpgres/soundboards/celeb/sounds/bradPitt.wavres/soundboards/celeb/images/bradPitt.jpgres/soundboards/celeb/sounds/bradPitt.wavres/soundboards/celeb/images/bradPitt.jpgres/soundboards/celeb/sounds/bradPitt.wavres/soundboards/celeb/images/bradPitt.jpgres/soundboards/celeb/sounds/bradPitt.wavres/soundboards/celeb/images/bradPitt.jpgres/soundboards/celeb/sounds/bradPitt.wavres/soundboards/celeb/images/bradPitt.jpgres/soundboards/celeb/sounds/bradPitt.wav", sb.toString());
	}
	
	@Test
	public void testSize1() {
		StringBuilder sb = new StringBuilder();
		String[] singleList = {"onlyItem"};
		List<SoundInformation> singleSoundList = SoundBoard.listToInfo(singleList,  "single");
		for (int i = 0; i < singleSoundList.size(); i++) {
			sb.append(singleSoundList.get(i).getImage() + singleSoundList.get(i).getSound());
		}
		assertEquals("res/soundboards/single/images/onlyItem.jpgres/soundboards/single/sounds/onlyItem.wav", sb.toString());
	}

}
