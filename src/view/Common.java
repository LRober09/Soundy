package view;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import main.Main;

import ui.ButtonStyle;
import ui.SButton;

public class Common {
	private Common() {

	}

	public static SButton backButton() {
		SButton b = new SButton("<--", ButtonStyle.PRIMARY);
		b.setOnAction(event -> Main.changeScene(SceneType.MAIN_MENU));
		return b;
	}

	public static void addTopBar(BorderPane root, String name, boolean backbutton) {
		BorderPane top = new BorderPane();
		if (backbutton) {
			SButton backButton = new SButton();
			backButton.setAlignment(Pos.TOP_LEFT);
			backButton.setText("<--");
			backButton.setOnAction(event -> Main.changeScene(SceneType.MAIN_MENU));
			top.setLeft(backButton);
		}
		Label title = new Label();
		title.setText(name);
		title.setFont(new Font("55", 55));
		title.setAlignment(Pos.CENTER);
		title.setBackground(new Background(new BackgroundFill(Color.web("#2D9CDB"), new CornerRadii(25), Insets.EMPTY)));
		title.setTextFill(Color.WHITE);
		title.setTextAlignment(TextAlignment.CENTER);
		top.setCenter(title);
		root.setTop(top);
	}

}

