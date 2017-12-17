/**
 * 
 */
package interfaces.gUI;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
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
	public ChangeIsBoughtListener(ListaCompra listaCompra, Label productName) {
		this.listaCompra = listaCompra;
		this.producto = productName.getText();
	}

	@Override
	public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		this.listaCompra.setComprado(producto, newValue);
	}

}
