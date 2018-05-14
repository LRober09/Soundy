package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import main.Main;
import model.Constants;
import model.User;
import ui.SButton;
import ui.SPasswordField;
import ui.STextField;

public class LoginScene extends SScene {
	private STextField login_usernameTextField;
	private SPasswordField login_passwordTextField;

	private STextField register_usernameTextField;
	private SPasswordField register_passwordTextField;
	private SPasswordField register_confirmTextField;

	private Label login_usernameFeedbackLabel;
	private Label login_passwordFeedbackLabel;
	private Label register_usernameFeedbackLabel;
	private Label register_passwordFeedbackLabel;

	public LoginScene() {
		super(Pos.TOP_LEFT);

		GridPane root = (GridPane) this.getRoot();

		// Setup TabPane
		TabPane tabPane = new TabPane();
		tabPane.setPrefWidth(Constants.SCREEN_WIDTH);
		tabPane.setPrefHeight(Constants.SCREEN_HEIGHT - 20);
		Tab loginTab = new Tab("Login");
		Tab registerTab = new Tab("Register");

		loginTab.setClosable(false);
		registerTab.setClosable(false);

		// Setup tab content
		loginTab.setContent(this.buildLoginPane());
		registerTab.setContent(this.buildRegisterPane());

		// Add content to root
		tabPane.getTabs().addAll(loginTab, registerTab);
		root.add(tabPane, 0, 0);
	}

	private GridPane buildLoginPane() {
		GridPane loginPane = new GridPane();
		loginPane.setAlignment(Pos.CENTER);
		loginPane.setHgap(10);
		loginPane.setVgap(10);
		loginPane.setPadding(new Insets(10));

		Label usernameLabel = new Label("Username:");
		Label passwordLabel = new Label("Password:");
		this.login_usernameFeedbackLabel = new Label();
		this.login_passwordFeedbackLabel = new Label();
		this.login_usernameTextField = new STextField();
		this.login_passwordTextField = new SPasswordField();
		SButton loginButton = new SButton("Login");

		// loginButton.setOnAction(event -> Main.changeScene(SceneType.MAIN_MENU));
		loginButton.setOnAction(event -> {
			if(validateLoginFields()) {
				User user = new User(this.login_usernameTextField.getText().toLowerCase());
				if(user.authenticateUser(this.login_passwordTextField.getText())) {
					Main.changeScene(SceneType.MAIN_MENU);
				}
			}
		});

		loginPane.add(usernameLabel, 0, 0);
		loginPane.add(this.login_usernameTextField, 0, 1);
		loginPane.add(this.login_usernameFeedbackLabel, 0, 2);
		loginPane.add(passwordLabel, 1, 0);
		loginPane.add(this.login_passwordTextField, 1, 1);
		loginPane.add(this.login_passwordFeedbackLabel, 1, 2);
		loginPane.add(loginButton, 2, 1);

		return loginPane;
	}

	private GridPane buildRegisterPane() {
		GridPane registerPane = new GridPane();
		registerPane.setAlignment(Pos.CENTER);
		registerPane.setHgap(10);
		registerPane.setVgap(10);
		registerPane.setPadding(new Insets(10));

		Label usernameLabel = new Label("Username:");
		Label passwordLabel = new Label("Password:");
		Label confirmLabel = new Label("Confirm Password:");

		this.register_usernameFeedbackLabel = new Label();
		this.register_passwordFeedbackLabel = new Label();

		this.register_usernameFeedbackLabel.setVisible(false);
		this.register_passwordFeedbackLabel.setVisible(false);

		this.register_usernameTextField = new STextField();
		this.register_passwordTextField = new SPasswordField();
		this.register_confirmTextField = new SPasswordField();
		SButton registerButton = new SButton("Register");

		registerButton.setOnAction(event -> {
			if(validateRegisterFields()) {
				
			}
		});

		registerPane.add(usernameLabel, 0, 0);
		registerPane.add(this.register_usernameTextField, 0, 1);
		registerPane.add(this.register_usernameFeedbackLabel, 0, 2);
		registerPane.add(passwordLabel, 1, 0);
		registerPane.add(this.register_passwordTextField, 1, 1);
		registerPane.add(this.register_passwordFeedbackLabel, 1, 2);
		registerPane.add(confirmLabel, 2, 0);
		registerPane.add(this.register_confirmTextField, 2, 1);
		registerPane.add(registerButton, 3, 1);

		return registerPane;
	}

	private boolean validateLoginFields() {
		// Reset field validation
		this.login_usernameTextField.setValid(true);
		this.login_passwordTextField.setValid(true);
		this.login_usernameFeedbackLabel.setVisible(false);
		this.login_passwordFeedbackLabel.setVisible(false);
		
		// Validate username length
		if(this.login_usernameTextField.getText().length() == 0) {
			this.login_usernameTextField.setValid(false);
			this.login_usernameFeedbackLabel.setVisible(true);
			this.login_usernameFeedbackLabel.setText("Required");
			return false;
		}
		
		// Validate password length
		if(this.login_passwordTextField.getText().length() == 0) {
			this.login_passwordTextField.setValid(false);
			this.login_passwordFeedbackLabel.setVisible(true);
			this.login_passwordFeedbackLabel.setText("Required");
			return false;
		}
		
		return true;
	}

	private boolean validateRegisterFields() {
		// Reset field validation
		this.register_usernameFeedbackLabel.setVisible(false);
		this.register_passwordFeedbackLabel.setVisible(false);
		this.register_usernameTextField.setValid(true);
		this.register_passwordTextField.setValid(true);
		this.register_confirmTextField.setValid(true);
		
		// Validate username length
		if(this.register_usernameTextField.getText().length() == 0) {
			this.register_usernameTextField.setValid(false);
			this.register_usernameFeedbackLabel.setVisible(true);
			this.register_usernameFeedbackLabel.setText("Required");
			return false;
		}
		
		// Validate password length
		if(this.register_passwordTextField.getText().length() < 6) {
			this.register_passwordTextField.setValid(false);
			this.register_passwordFeedbackLabel.setVisible(true);
			this.register_passwordFeedbackLabel.setText("Password must be at least 6 characters");
			return false;
		} else if (!this.register_passwordTextField.getText().equals(this.register_confirmTextField.getText())) { // Validate passwords match
			this.register_confirmTextField.setValid(false);
			this.register_passwordFeedbackLabel.setVisible(true);
			this.register_passwordFeedbackLabel.setText("Passwords do not match");
			return false;
		}
		
		
		System.out.println("Valid!");
		return true;
	}
}
