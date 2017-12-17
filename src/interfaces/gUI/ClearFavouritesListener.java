/**
 * 
 */
package interfaces.gUI;

import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import modelo.datos.ListaCompra;

/**
 * @author Miguel Ángel León
 *
 */
public class ClearFavouritesListener implements EventHandler<ActionEvent> {

	private ListaCompra listaCompra;

	public ClearFavouritesListener(ListaCompra listaCompra) {
		this.listaCompra = listaCompra;
	}

	/* (non-Javadoc)
	 * @see javafx.event.EventHandler#handle(javafx.event.Event)
	 */
	@Override
	public void handle(ActionEvent arg0) {
		Alert confirmation = new Alert(AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
		confirmation.setHeaderText("¿Eliminar todos los favoritos?");
		Optional<ButtonType> result = confirmation.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.YES)
			this.listaCompra.limpiarFavoritos();
	}
}
