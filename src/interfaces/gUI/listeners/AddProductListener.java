/**
 * 
 */
package interfaces.gUI.listeners;

import interfaces.gUI.panels.AddProductPanel;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import modelo.datos.ListaCompra;

/**
 * @author Miguel Ángel León
 *
 */
public class addProductListener implements EventHandler<MouseEvent> {

	private ListaCompra listaCompra;

	private AddProductPanel app;

	public addProductListener(AddProductPanel addProductPanel) {
		this.app = addProductPanel;
		this.listaCompra = this.app.getListaCompra();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.event.EventHandler#handle(javafx.event.Event)
	 */
	@Override
	public void handle(MouseEvent arg0) {
		this.listaCompra.anadirProducto(this.app.getProductName(), this.app.getProductQuantity());
		if (this.app.getIsFavourite()) {
			this.listaCompra.anadirFavorito(this.app.getProductName());
		}
	}
}