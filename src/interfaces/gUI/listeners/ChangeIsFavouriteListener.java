package interfaces.gUI.listeners;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import modelo.datos.ListaCompra;
import modelo.datos.Producto;

/**
 * Listener que permite modificar el atributo 'favorito' de un producto.
 * 
 * @author MIGUEL ANGEL LEON BARDAVIO
 * @author CLARA PALACIOS RODRIGO
 * @see ListaCompra
 * @see Producto
 */
public class ChangeIsFavouriteListener implements ChangeListener<Boolean> {

	/**
	 * Referencia a listaCompra.
	 * 
	 * @see ListaCompra
	 */
	private ListaCompra listaCompra;
	
	/**
	 * Nombre del producto.
	 */
	private String producto;

	/**
	 * Constuctor.
	 * @param listaCompra Lista de la compra.
	 * @param productName Nombre d eun producto.
	 */
	public ChangeIsFavouriteListener(ListaCompra listaCompra, String productName) {
		this.listaCompra = listaCompra;
		this.producto = productName;
	}

	/* (non-Javadoc)
	 * @see javafx.beans.value.ChangeListener#changed(javafx.beans.value.ObservableValue, java.lang.Object, java.lang.Object)
	 */
	@Override
	public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		if (newValue)
			this.listaCompra.anadirFavorito(producto);
		else
			this.listaCompra.eliminarFavorito(producto);
	}

}
