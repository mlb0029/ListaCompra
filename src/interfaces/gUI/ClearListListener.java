/**
 * 
 */
package interfaces.gUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
		this.listaCompra.limpiarListaCompra();
	}

}
