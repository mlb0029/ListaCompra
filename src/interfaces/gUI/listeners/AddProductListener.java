package interfaces.gUI.listeners;

import interfaces.gUI.panels.AddProductPanel;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import modelo.datos.ListaCompra;

/**
 * Listener que permite añadir un producto a la lista de la compra.
 * 
 * @author MIGUEL ANGEL LEON BARDAVIO
 * @author CLARA PALACIOS RODRIGO
 * @see ListaCompra
 *
 */
public class AddProductListener implements EventHandler<MouseEvent> {

	/**
	 * Referencia a la lista de la compra.
	 * 
	 * @see ListaCompra
	 */
	private ListaCompra listaCompra;

	/**
	 * Referencia al panel de donde recoge los datos del nuevo producto.
	 */
	private AddProductPanel app;

	/**
	 * Constructor que inicializa los atributos de la clase.
	 * 
	 * @param addProductPanel Panel de donde recoge los datos del nuevo producto.
	 */
	public AddProductListener(AddProductPanel addProductPanel) {
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