/**
 * 
 */
package interfaces.gUI;

import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import modelo.datos.ListaCompra;

/**
 * @author Miguel Ángel León
 *
 */
public class ClearListListener implements EventHandler<ActionEvent> {

	/**
	 * Lista de la compra.
	 */
	ListaCompra listaCompra;
	
	
	/**
	 * Constructor.
	 * 
	 * @param liCo Lista de la compra.
	 */
	public ClearListListener(ListaCompra liCo) {
		this.listaCompra = liCo;
	}

	@Override
	public void handle(ActionEvent arg0) {
		Alert confirmation = new Alert(AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
		confirmation.setHeaderText("¿Vaciar lista de la compra?");
		Optional<ButtonType> result = confirmation.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.YES)
			this.listaCompra.limpiarListaCompra();
	}

}
