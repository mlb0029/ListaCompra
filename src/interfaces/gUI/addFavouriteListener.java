/**
 * 
 */
package interfaces.gUI;

import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputDialog;
import modelo.datos.ListaCompra;

/**
 * @author Miguel Ángel León
 *
 */
public class addFavouriteListener implements EventHandler<ActionEvent> {

	private ListaCompra listaCompra;

	public addFavouriteListener(ListaCompra listaCompra) {
		this.listaCompra = listaCompra;
	}

	/* (non-Javadoc)
	 * @see javafx.event.EventHandler#handle(javafx.event.Event)
	 */
	@Override
	public void handle(ActionEvent arg0) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Add favourite");
		dialog.setHeaderText("Añadir Favorito");
		dialog.setContentText("Nombre de producto favorito: ");
		
		Optional<String> result = dialog.showAndWait ();
		if(result.isPresent())
			this.listaCompra.anadirFavorito(result.get());
	}

}
