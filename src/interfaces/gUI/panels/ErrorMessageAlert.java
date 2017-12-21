/**
 * 
 */
package interfaces.gUI.panels;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * @author Miguel Ángel León
 *
 */
public class ErrorMessageAlert {

	private static Alert errorMsg = new Alert(AlertType.ERROR);

	public static void showErrorMsg(String msg) {
		errorMsg.setContentText(msg);
		errorMsg.showAndWait();
	}

}
