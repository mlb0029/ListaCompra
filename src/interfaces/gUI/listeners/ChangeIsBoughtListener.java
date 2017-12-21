/**
 * 
 */
package interfaces.gUI.listeners;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import modelo.datos.ListaCompra;

/**
 * @author Miguel Ángel León
 *
 */
public class ChangeIsBoughtListener implements ChangeListener<Boolean> {

	private ListaCompra listaCompra;
	private String producto;

	/**
	 * @param productName 
	 * @param listaCompra 
	 * 
	 */
	public ChangeIsBoughtListener(ListaCompra listaCompra, String productName) {
		this.listaCompra = listaCompra;
		this.producto = productName;
	}

	@Override
	public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		this.listaCompra.setComprado(producto, newValue);
	}

}
