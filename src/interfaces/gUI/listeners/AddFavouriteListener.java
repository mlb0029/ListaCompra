package interfaces.gUI.listeners;

import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputDialog;
import modelo.datos.ListaCompra;

/**
 * Listener para añadir favorito.
 * 
 * @author MIGUEL ANGEL LEON BARDAVIO
 * @see ListaCompra
 */
public class AddFavouriteListener implements EventHandler<ActionEvent> {

	/**
	 * Referencia a la lista de la compra.
	 * 
	 * @see ListaCompra
	 */
	private ListaCompra listaCompra;

	/**
	 * Constructor que recibe la referencia a la lista de la compra.
	 * 
	 * @param listaCompra Lista de la compra.
	 */
	public AddFavouriteListener(ListaCompra listaCompra) {
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
