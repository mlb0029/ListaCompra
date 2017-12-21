package interfaces.gUI.panels;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Permite alertar de un error.
 * 
 * @author MIGUEL ANGEL LEON BARDAVIO
 * @author CLARA PALACIOS RODRIGO
 *
 */
public class ErrorMessageAlert {

	/**
	 * Cuadro de rror.
	 */
	private static Alert errorMsg = new Alert(AlertType.ERROR);

	/**
	 * Función para mostrar un mensaje de error.
	 * 
	 * @param msg Mensaje a mostrar.
	 */
	public static void showErrorMsg(String msg) {
		errorMsg.setContentText(msg);
		errorMsg.showAndWait();
	}

}
