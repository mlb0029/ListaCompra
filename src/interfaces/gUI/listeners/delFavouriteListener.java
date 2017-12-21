/**
 * 
 */
package interfaces.gUI.listeners;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import interfaces.gUI.panels.ErrorMessageAlert;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import modelo.datos.ListaCompra;

/**
 * @author Miguel Ángel León
 *
 */
public class delFavouriteListener implements EventHandler<ActionEvent> {

	private ListaCompra listaCompra;

	public delFavouriteListener(ListaCompra listaCompra) {
		this.listaCompra = listaCompra;
	}

	/* (non-Javadoc)
	 * @see javafx.event.EventHandler#handle(javafx.event.Event)
	 */
	@Override
	public void handle(ActionEvent arg0) {
		List<String> favoritos = new ArrayList<String>(this.listaCompra.getFavoritos());
		if (!favoritos.isEmpty()) {
			ChoiceDialog<String> dialog = new ChoiceDialog<String>(favoritos.get(0),favoritos);
			dialog.setTitle("Remove favourite");
			dialog.setHeaderText("Eliminar Favorito");
			dialog.setContentText("Eliminar: ");
			Optional<String> result = dialog.showAndWait ();
			if(result.isPresent()) {
				Alert confirmation = new Alert(AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
				confirmation.setHeaderText("¿Eliminar " + result.get().toString() + " de favoritos?");
				Optional<ButtonType> confirm = confirmation.showAndWait();
				if(confirm.isPresent() && confirm.get() == ButtonType.YES)
					this.listaCompra.eliminarFavorito(result.get());
			}
		}else {
			ErrorMessageAlert.showErrorMsg("No hay favoritos");
		}
	}

}
