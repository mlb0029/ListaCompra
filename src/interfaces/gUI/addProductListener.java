/**
 * 
 */
package interfaces.gUI;

import java.util.ArrayList;
import java.util.HashMap;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
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
public class addProductListener implements EventHandler<MouseEvent> {

	private ListaCompra listaCompra;
	private ComboBox<String> productName;
	private Spinner<Integer> productQuantity;
	private CheckBox productIsFavourite;
	Stage ventana;

	public addProductListener(Stage ventana, ListaCompra listaCompra, ComboBox<String> productName,
			Spinner<Integer> productQuantity, CheckBox isFavourite) {
		this.listaCompra = listaCompra;
		this.productName = productName;
		this.productQuantity = productQuantity;
		this.productIsFavourite = isFavourite;
		this.ventana=ventana;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.event.EventHandler#handle(javafx.event.Event)
	 */
	@Override
	

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
			//ArrayList<String> nombreslista = new ArrayList<String>(listaCompra.keySet());


			if (!nomProd.isEmpty()) {
				this.listaCompra.anadirProducto(nomProd, intFactory.getValue());
				this.listaCompra.modificarCantidad(nomProd, (intFactory.getValue()));
			}
			if (esFav == true) {
				this.listaCompra.setComprado(nomProd, true);
			} else {
				this.listaCompra.setComprado(nomProd, false);
			}
			
			BorderPane root = (BorderPane) ventana.getScene().lookup("#root");
			root.setCenter(anadirProd(this.listaCompra.getListaCompra()));
		}
	
	}
	
	public GridPane anadirProd(HashMap<String, LineaProducto> liCo) {


		
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
	
	//he añadido a ListaComra
}