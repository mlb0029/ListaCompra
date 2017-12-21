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
public class ChangeIsFavouriteListener implements ChangeListener<Boolean> {

	private ListaCompra listaCompra;
	private String producto;

	public ChangeIsFavouriteListener(ListaCompra listaCompra, String productName) {
		this.listaCompra = listaCompra;
		this.producto = productName;
	}

	@Override
	public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		if (newValue)
			this.listaCompra.anadirFavorito(producto);
		else
			this.listaCompra.eliminarFavorito(producto);
	}

}
