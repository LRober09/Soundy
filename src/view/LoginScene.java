package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import main.Main;
import model.Authentication;
import model.Constants;
import model.User;
import ui.SButton;
import ui.SPasswordField;
import ui.STextField;

/**
 * The login/register scene
 * 
 * @author Lucas Robertson
 *
 */
public class LoginScene extends SScene {
	
	private static final String MSG_REQ = "Required";
	private static final String MSG_INVALID = "Invalid username or password";
	private static final String MSG_EXISTS = "User already exists";
	private static final String MSG_LENGTH = "Password must be at least 6 characters";
	private static final String MSG_MATCH = "Passwords do not match";
	
	private STextField loginUsernameTextField;
	private SPasswordField loginPasswordTextField;

	private STextField registerUsernameTextField;
	private SPasswordField registerPasswordTextField;
	private SPasswordField registerConfirmTextField;

	private Label loginUsernameFeedbackLabel;
	private Label loginPasswordFeedbackLabel;
	private Label registerUsernameFeedbackLabel;
	private Label registerPasswordFeedbackLabel;

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
		this.loginUsernameFeedbackLabel = new Label();
		this.loginPasswordFeedbackLabel = new Label();
		this.loginUsernameTextField = new STextField();
		this.loginPasswordTextField = new SPasswordField();
		SButton loginButton = new SButton("Login");

		loginButton.setOnAction(event -> {
			if (validateLoginFields()) { // Validate username and password fields
				User user = new User(loginUsernameTextField.getText());

				// Try to authenticate user - go to main menu if successful, otherwise display
				// an error
				if (user.authenticate(loginPasswordTextField.getText())) {
					Main.changeScene(SceneType.MAIN_MENU);
				} else {
					loginUsernameTextField.setValid(false);
					loginPasswordTextField.setValid(false);
					loginUsernameFeedbackLabel.setVisible(true);
					loginUsernameFeedbackLabel.setText(MSG_INVALID);
				}
			}
		});

		// Add elements to pane
		loginPane.add(usernameLabel, 0, 0);
		loginPane.add(this.loginUsernameTextField, 0, 1);
		loginPane.add(this.loginUsernameFeedbackLabel, 0, 2);
		loginPane.add(passwordLabel, 1, 0);
		loginPane.add(this.loginPasswordTextField, 1, 1);
		loginPane.add(this.loginPasswordFeedbackLabel, 1, 2);
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

		this.registerUsernameFeedbackLabel = new Label();
		this.registerPasswordFeedbackLabel = new Label();

		this.registerUsernameFeedbackLabel.setVisible(false);
		this.registerPasswordFeedbackLabel.setVisible(false);

		this.registerUsernameTextField = new STextField();
		this.registerPasswordTextField = new SPasswordField();
		this.registerConfirmTextField = new SPasswordField();
		SButton registerButton = new SButton("Register");

		registerButton.setOnAction(event -> {
			if (validateRegisterFields()) {
				User user = new User(registerUsernameTextField.getText());
				switch (Authentication.registerUser(user.getUsername(), registerPasswordTextField.getText())) {
				case ENTRY_EXISTS:
					this.registerUsernameTextField.setValid(false);
					this.registerUsernameFeedbackLabel.setVisible(true);
					this.registerUsernameFeedbackLabel.setText(MSG_EXISTS);
					break;
				case DB_LOCKED:
					AlertManager.showFatalError("Database Error",
							"The database is currently locked. Check your SQLite browser for uncommitted changes!");
					break;
				case SQL_EXCEPTION:
					AlertManager.showFatalError("Database Error", "An error occurred while creating your account!");
					break;
				case SUCCESS:
					user.authenticate(registerPasswordTextField.getText());
					Main.changeScene(SceneType.MAIN_MENU);
					break;
				default:
					break;
				}
			}
		});

		registerPane.add(usernameLabel, 0, 0);
		registerPane.add(this.registerUsernameTextField, 0, 1);
		registerPane.add(this.registerUsernameFeedbackLabel, 0, 2);
		registerPane.add(passwordLabel, 1, 0);
		registerPane.add(this.registerPasswordTextField, 1, 1);
		registerPane.add(this.registerPasswordFeedbackLabel, 1, 2);
		registerPane.add(confirmLabel, 2, 0);
		registerPane.add(this.registerConfirmTextField, 2, 1);
		registerPane.add(registerButton, 3, 1);

		return registerPane;
	}

	private boolean validateLoginFields() {
		// Reset field validation
		this.loginUsernameTextField.setValid(true);
		this.loginPasswordTextField.setValid(true);
		this.loginUsernameFeedbackLabel.setVisible(false);
		this.loginPasswordFeedbackLabel.setVisible(false);

		// Validate username length
		if (this.loginUsernameTextField.getText().length() == 0) {
			this.loginUsernameTextField.setValid(false);
			this.loginUsernameFeedbackLabel.setVisible(true);
			this.loginUsernameFeedbackLabel.setText(MSG_REQ);
			return false;
		}

		// Validate password length
		if (this.loginPasswordTextField.getText().length() == 0) {
			this.loginPasswordTextField.setValid(false);
			this.loginPasswordFeedbackLabel.setVisible(true);
			this.loginPasswordFeedbackLabel.setText(MSG_REQ);
			return false;
		}

		return true;
	}

	private boolean validateRegisterFields() {
		// Reset field validation
		this.registerUsernameFeedbackLabel.setVisible(false);
		this.registerPasswordFeedbackLabel.setVisible(false);
		this.registerUsernameTextField.setValid(true);
		this.registerPasswordTextField.setValid(true);
		this.registerConfirmTextField.setValid(true);

		// Validate username length
		if (this.registerUsernameTextField.getText().length() == 0) {
			this.registerUsernameTextField.setValid(false);
			this.registerUsernameFeedbackLabel.setVisible(true);
			this.registerUsernameFeedbackLabel.setText(MSG_REQ);
			return false;
		}

		// Validate password length
		if (this.registerPasswordTextField.getText().length() < 6) {
			this.registerPasswordTextField.setValid(false);
			this.registerPasswordFeedbackLabel.setVisible(true);
			this.registerPasswordFeedbackLabel.setText(MSG_LENGTH);
			return false;
		} else if (!this.registerPasswordTextField.getText().equals(this.registerConfirmTextField.getText())) { // Validate
																												// passwords
																												// match
			this.registerConfirmTextField.setValid(false);
			this.registerPasswordFeedbackLabel.setVisible(true);
			this.registerPasswordFeedbackLabel.setText(MSG_MATCH);
			return false;
		}

		return true;
	}
}
