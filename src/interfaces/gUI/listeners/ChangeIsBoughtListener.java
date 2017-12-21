package interfaces.gUI.listeners;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import modelo.datos.ListaCompra;

/**
 * Listener que permite modificar si el producto está comprado  o no.
 * 
 * @author MIGUEL ANGEL LEON BARDAVIO
 * @author CLARA PALACIOS RODRIGO
 * @see ListaCompra
 */
public class ChangeIsBoughtListener implements ChangeListener<Boolean> {

	/**
	 * Referencia a lista compra.
	 * 
	 * @see ListaCompra
	 */
	private ListaCompra listaCompra;
	
	/**
	 * Nombre del producto.
	 */
	private String producto;

	/**
	 * Constructor que inicializa los parámetros.
	 * 
	 * @param productName  Nombre del producto.
	 * @param listaCompra Lista de la compra.
	 * 
	 */
	public ChangeIsBoughtListener(ListaCompra listaCompra, String productName) {
		this.listaCompra = listaCompra;
		this.producto = productName;
	}

	/* (non-Javadoc)
	 * @see javafx.beans.value.ChangeListener#changed(javafx.beans.value.ObservableValue, java.lang.Object, java.lang.Object)
	 */
	@Override
	public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		this.listaCompra.setComprado(producto, newValue);
	}

}
