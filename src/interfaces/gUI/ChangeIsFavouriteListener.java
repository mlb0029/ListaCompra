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
public class ChangeIsFavouriteListener implements ChangeListener<Boolean> {

	private ListaCompra listaCompra;
	private String producto;

	public ChangeIsFavouriteListener(ListaCompra listaCompra, Label productName) {
		this.listaCompra = listaCompra;
		this.producto = productName.getText();
	}

	@Override
	public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		if (newValue)
			this.listaCompra.anadirFavorito(producto);
		else
			this.listaCompra.eliminarFavorito(producto);
	}

}
