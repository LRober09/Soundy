package view;

import javafx.scene.control.Button;
import main.Main;
import ui.ButtonStyle;
import ui.SButton;

public class Common {
	public static SButton backButton() {
		SButton b = new SButton("<--", ButtonStyle.PRIMARY);
		b.setOnAction(event -> Main.changeScene(SceneType.MAIN_MENU));
		return b;
	}

}
