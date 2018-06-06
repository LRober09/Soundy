package test;

import static org.junit.Assert.*;
import model.SettingsModel;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.Background;
import javafx.embed.swing.JFXPanel;
import javafx.scene.image.Image;
import org.junit.Test;

public class Test5 {

	// Author: Nicky Kyono
	@Test
	public void test1() {
		Background bg = SettingsModel.getBG();
		if (SettingsModel.setBG(Background.EMPTY)) {
			assertTrue(bg.isEmpty());
		}
	}
	
	// Author: Nicky Kyono
	@Test
	public void test2() {
		JFXPanel fxPan = new JFXPanel();
		
		SettingsModel.setBG(Background.EMPTY);		
		
		Image img = new Image("file:res/soundboards/farm/images/Cow.jpg");
		BackgroundSize bgSize = BackgroundSize.DEFAULT;
		BackgroundPosition bgPos = BackgroundPosition.DEFAULT;
		BackgroundRepeat bgRepeat = BackgroundRepeat.NO_REPEAT;
		BackgroundImage bgImage = new BackgroundImage(img, bgRepeat, bgRepeat, bgPos, bgSize);
				
		Background bg = new Background(bgImage);
		
		if (SettingsModel.setBG(bg)) {
			assertFalse(SettingsModel.getBG().isEmpty());
		}
		
	}
}
