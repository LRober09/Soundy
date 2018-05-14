package view;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

/**
 * Utility class for easily displaying popup alerts
 * @author Lucas Robertson
 *
 */
public class AlertManager {
	
	/**
	 * Display a popup alert for a fatal error. Upon confirmation of the alert, the system will exit with code 1
	 * @param title The title of the alert
	 * @param message The text content of the alert
	 */
	public static void showFatalError(String title, String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setContentText(message);
		alert.showAndWait().ifPresent(rs -> {
			if (rs == ButtonType.OK) {
				System.exit(1);
			}
		});
	}
}
