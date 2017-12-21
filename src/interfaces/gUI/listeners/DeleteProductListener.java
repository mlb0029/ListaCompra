/**
 * 
 */
package interfaces.gUI.listeners;

import java.util.Optional;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import modelo.datos.ListaCompra;

/**
 * Permite borrar un producto de la lista de la compra.
 * 
 * @author MIGUEL ANGEL LEON BARDAVIO
 * @see ListaCompra
 */
public class DeleteProductListener implements EventHandler<MouseEvent> {

	/**
	 * Referencia a lista de la compra.
	 * 
	 * @see ListaCompra
	 */
	private ListaCompra listaCompra;
	
	/**
	 * Nombre del producto sobre el que actúa.
	 */
	private String producto;
	
	/**
	 * Constructor.
	 * 
	 * @param listaCompra Lista de la compra.
	 * @param productName Nombre del producto.
	 */
	public DeleteProductListener(ListaCompra listaCompra, String productName) {
		this.listaCompra = listaCompra;
		this.producto = productName;
	}

	/* (non-Javadoc)
	 * @see javafx.event.EventHandler#handle(javafx.event.Event)
	 */
	@Override
	public void handle(MouseEvent arg0) {
		Alert confirmation = new Alert(AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
		confirmation.setHeaderText("¿Eliminar " + producto + " de la lista de la  compra?");
		Optional<ButtonType> result = confirmation.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.YES)
			this.listaCompra.eliminarProducto(producto);
	}
}
