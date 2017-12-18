/**
 * 
 */
package interfaces.gUI;

import java.util.HashMap;
import java.util.Optional;

import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Spinner;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import modelo.datos.LineaProducto;
import modelo.datos.ListaCompra;

/**
 * @author Miguel Ángel León
 *
 */
public class deleteProductListener implements EventHandler<MouseEvent> {

	private ListaCompra listaCompra;
	private Label producto;
	Stage ventana;
	public deleteProductListener(Stage ventana, ListaCompra listaCompra, Label productName) {
		this.listaCompra = listaCompra;
		this.producto = productName;
		this.ventana=ventana;
	}

	/* (non-Javadoc)
	 * @see javafx.event.EventHandler#handle(javafx.event.Event)
	 */
	@Override
	public void handle(MouseEvent arg0) {
		Alert confirmation = new Alert(AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
		confirmation.setHeaderText("¿Eliminar " + producto.getText() + " de la lista de la  compra?");
		Optional<ButtonType> result = confirmation.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.YES)
			this.listaCompra.eliminarProducto(producto.getText());
		BorderPane root = (BorderPane) ventana.getScene().lookup("#root");
		root.setCenter(eliminarProducto(this.listaCompra.getListaCompra()));
	}

	public GridPane  eliminarProducto(HashMap<String, LineaProducto> liCo) {
		GridPane productList = new GridPane();
		Integer rowIndex = 1;

		for (String prod : liCo.keySet()) {
			// Nombre del producto
			Label productName = new Label(prod);
			productList.add(productName, 0, rowIndex);
//			// Cantidad
			Spinner<Integer> productQuantity =  new Spinner<Integer>(1, Integer.MAX_VALUE, liCo.get(prod).getCantidad());
			productQuantity.setEditable(true);
			productQuantity.valueProperty().addListener(new ChangeQuantityListener(listaCompra, productName));
			productList.add(productQuantity, 1, rowIndex);
//			//Está comprado
			CheckBox isBought = new CheckBox("Comprado");
			isBought.setSelected(liCo.get(prod).getEstaComprado());
			isBought.selectedProperty().addListener(new ChangeIsBoughtListener(listaCompra, productName));
			productList.add(isBought, 2, rowIndex);
			//Es Favorito
			CheckBox isFavourite = new CheckBox("Favorito");
			isFavourite.setSelected(listaCompra.getFavoritos().contains(prod));
			productList.add(isFavourite, 3, rowIndex);
			isFavourite.selectedProperty().addListener(new ChangeIsFavouriteListener(listaCompra, productName));
			//Eliminar
			Button deleteButton = new Button("Eliminar"); // TODO Node grafphic x para eliminar producto de la lista
			deleteButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new deleteProductListener(ventana, listaCompra, productName));
			productList.add(deleteButton, 4, rowIndex);
			//Siguiente fila
			rowIndex++;
			System.out.println(prod);
		}
		productList.add(new Separator(Orientation.HORIZONTAL),0,rowIndex,5,1);
		rowIndex++;
		ComboBox<String> productName = new ComboBox<String>();
		productName.getItems().addAll(listaCompra.getFavoritos());
		productName.setEditable(true);
		productName.setPromptText("Nombre Producto");
		productList.add(productName, 0, rowIndex);
		Spinner<Integer> productQuantity =  new Spinner<Integer>(1, Integer.MAX_VALUE, 1);
		productQuantity.setEditable(true);
		productList.add(productQuantity, 1, rowIndex);
		CheckBox isBought = new CheckBox("Comprado");
		isBought.setDisable(true);
		productList.add(isBought, 2, rowIndex);
		CheckBox isFavourite = new CheckBox("Favorito");// TODO Bloquear isFavorito si la seleccion es de la lista
		productList.add(isFavourite, 3, rowIndex);
		Button addButton = new Button("Añadir"); // TODO Node grafphic + para añadir producto a la lista
		addButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new addProductListener(ventana, listaCompra, productName, productQuantity, isFavourite));
		productList.add(addButton, 4, rowIndex);
    	return productList;
	}

}
