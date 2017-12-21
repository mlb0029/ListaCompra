package interfaces.gUI.listeners;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import modelo.datos.ListaCompra;

/**
 * @author Miguel Ángel León
 *
 */
public class ChangeQuantityListener implements ChangeListener<Integer> {

	ListaCompra listaCompra;
	private String product;
	
	/**
	 * @param integer 
	 * @param productName 
	 * @param listaCompra 
	 * 
	 */
	public ChangeQuantityListener(ListaCompra listaCompra, String productName) {
		this.listaCompra = listaCompra;
		this.product = productName;
	}

	@Override
	public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
		listaCompra.modificarCantidad(product, newValue);
	}

}
