/**
 * 
 */
package interfaces.gUI;

import java.util.ArrayList;
import java.util.HashMap;

import com.sun.xml.internal.ws.org.objectweb.asm.Label;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import modelo.datos.LineaProducto;
import modelo.datos.ListaCompra;

/**
 * @author Miguel Ángel León
 *
 */
public class addProductListener implements EventHandler<MouseEvent> {

	private ListaCompra listaCompra;
	private ComboBox<String> productName;
	private Spinner<Integer> productQuantity;
	private CheckBox productIsFavourite;
	GridPane ventana;

	public addProductListener( ListaCompra listaCompra, ComboBox<String> productName,
			Spinner<Integer> productQuantity, CheckBox isFavourite) {
		this.listaCompra = listaCompra;
		this.productName = productName;
		this.productQuantity = productQuantity;
		this.productIsFavourite = isFavourite;
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.event.EventHandler#handle(javafx.event.Event)
	 */
	@Override
	// public void handle(MouseEvent arg0) {
	// Alert errorMessage;
	// if (!this.listaCompra.anadirProducto(productName.getValue(),
	// productQuantity.getValue())) {
	// errorMessage = new Alert(AlertType.ERROR);
	// errorMessage.setHeaderText("Campo 'Nombre' vacío o producto ya
	// existente");
	// errorMessage.showAndWait();
	// }
	// if (productIsFavourite.isSelected())
	// this.listaCompra.anadirFavorito(productName.getValue());
	// }

	public void handle(MouseEvent arg0) {
		// para el nombre del producto
		String nomProd = productName.getValue();
		// System.out.println(linModifi.getText());
		// para es favorito
		Boolean esFav = productIsFavourite.isSelected();
		// para Cantidad
		SpinnerValueFactory<Integer> numero = productQuantity.getValueFactory();
		SpinnerValueFactory.IntegerSpinnerValueFactory intFactory = (SpinnerValueFactory.IntegerSpinnerValueFactory) productQuantity
				.getValueFactory();

		HashMap<String, LineaProducto> listaCompra = this.listaCompra.getListaCompra();
		if (!nomProd.equals("")) {
			ArrayList<String> nombreslista = new ArrayList<String>(listaCompra.keySet());

			// String nombreProducto = nombreslista.get((nomProd));

			if (!nomProd.isEmpty()) {
				this.listaCompra.anadirProducto(nomProd, intFactory.getValue());
				this.listaCompra.modificarCantidad(nomProd, (intFactory.getValue()));
			}
			if (esFav == true) {
				this.listaCompra.setComprado(nomProd, true);
			} else {
				this.listaCompra.setComprado(nomProd, false);
			}

		}
ventana.getBoundsInParent();
	}

}