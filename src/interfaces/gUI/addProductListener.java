/**
 * 
 */
package interfaces.gUI;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.input.MouseEvent;
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

	public addProductListener(ListaCompra listaCompra, ComboBox<String> productName, Spinner<Integer> productQuantity, CheckBox isFavourite) {
		this.listaCompra = listaCompra;
		this.productName = productName;
		this.productQuantity = productQuantity;
		this.productIsFavourite = isFavourite;
	}

	/* (non-Javadoc)
	 * @see javafx.event.EventHandler#handle(javafx.event.Event)
	 */
	@Override
	public void handle(MouseEvent arg0) {
		 Alert errorMessage;
		if (!this.listaCompra.anadirProducto(productName.getValue(), productQuantity.getValue())) {
			errorMessage = new Alert(AlertType.ERROR);
			errorMessage.setHeaderText("Campo 'Nombre' vacío o producto ya existente");
			errorMessage.showAndWait();
		}
		if (productIsFavourite.isSelected())
			this.listaCompra.anadirFavorito(productName.getValue());
	}

}
