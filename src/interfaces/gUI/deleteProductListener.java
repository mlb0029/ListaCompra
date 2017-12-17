/**
 * 
 */
package interfaces.gUI;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import modelo.datos.ListaCompra;

/**
 * @author Miguel Ángel León
 *
 */
public class deleteProductListener implements EventHandler<MouseEvent> {

	private ListaCompra listaCompra;
	private String producto;

	public deleteProductListener(ListaCompra listaCompra, Label productName) {
		this.listaCompra = listaCompra;
		this.producto = productName.getText();
	}

	/* (non-Javadoc)
	 * @see javafx.event.EventHandler#handle(javafx.event.Event)
	 */
	@Override
	public void handle(MouseEvent arg0) {
		this.listaCompra.eliminarProducto(producto);
	}

}
