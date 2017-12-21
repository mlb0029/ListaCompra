package interfaces.gUI.listeners;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import modelo.datos.ListaCompra;

/**
 * Listener que permite cambiar la cantidad a comprar de un producto.
 * 
 * @author MIGUEL ANGEL LEON BARDAVIO
 * @author CLARA PALACIOS RODRIGO
 * 
 * @see ListaCompra
 */
public class ChangeQuantityListener implements ChangeListener<Integer> {

	/**
	 * Referencia a listaCompra.
	 * 
	 * @see ListaCompra
	 */
	ListaCompra listaCompra;
	
	/**
	 * Nombre del producto sobre el que actúa.
	 */
	private String product;
	
	/**
	 * Constructor.
	 * 
	 * @param productName Nombre del producto.
	 * @param listaCompra  Lista de la compra.
	 * 
	 */
	public ChangeQuantityListener(ListaCompra listaCompra, String productName) {
		this.listaCompra = listaCompra;
		this.product = productName;
	}

	/* (non-Javadoc)
	 * @see javafx.beans.value.ChangeListener#changed(javafx.beans.value.ObservableValue, java.lang.Object, java.lang.Object)
	 */
	@Override
	public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
		listaCompra.modificarCantidad(product, newValue);
	}

}
