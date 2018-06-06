package test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import model.SettingsModel;

import javafx.embed.swing.JFXPanel;
import org.junit.Test;
import model.SoundBoard;
import model.SoundInformation;

public class Test6 {

	// Author: Lucas Robertson
	@Test
	public void test1() {
		JFXPanel fxPan = new JFXPanel();

		SoundInformation soundInfo = new SoundInformation("/res/soundboards/farm/images/Cow.jpg", "/res/soundboards/farm/sounds/Cow.wav");
		SoundInformation soundInfo2 = new SoundInformation("/res/soundboards/farm/images/Cow2.jpg", "/res/soundboards/farm/sounds/Cow2.wav");
		List<SoundInformation> siList = Arrays.asList(soundInfo, soundInfo2);
		SoundBoard sb = new SoundBoard(siList);
		
		SettingsModel.setSoundboard(sb);
		
		SoundInformation soundInfo3 = new SoundInformation("/res/soundboards/farm/images/Chicken.jpg", "/res/soundboards/farm/sounds/Chicken.wav");
		SoundInformation soundInfo4 = new SoundInformation("/res/soundboards/farm/images/Chicken2.jpg", "/res/soundboards/farm/sounds/Chicken2.wav");
		List<SoundInformation> siList2 = Arrays.asList(soundInfo3, soundInfo4);
		SoundBoard sb2 = new SoundBoard(siList2);
		
		SettingsModel.setSoundboard(sb2);

		assertEquals(SettingsModel.getSoundboard().getData().get(0).getImage(), "/res/soundboards/farm/images/Chicken.jpg");
	}
}

