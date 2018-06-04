package view;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import main.Main;
import ui.ButtonStyle;
import ui.SButton;

public class Common {
	public static SButton backButton() {
		SButton b = new SButton("<--", ButtonStyle.PRIMARY);
		b.setOnAction(event -> Main.changeScene(SceneType.MAIN_MENU));
		return b;
	}

	public static void addTopBar(BorderPane root, String name, boolean backbutton) {
		BorderPane top = new BorderPane();
		Insets split = new Insets(5);
		if (backbutton) {
			SButton backButton = new SButton();
			backButton.setAlignment(Pos.TOP_LEFT);
			backButton.setText("Back");
			backButton.setOnAction(event -> Main.changeScene(SceneType.MAIN_MENU));
			top.setLeft(backButton);
			BorderPane.setMargin((Node)backButton, split);
		}
		Label title = new Label();
		title.setText(name);
		title.setFont(new Font("55", 55));
		title.setAlignment(Pos.CENTER);
		title.setTextAlignment(TextAlignment.CENTER);
		top.setCenter(title);
		root.setTop(top);
	}

}
