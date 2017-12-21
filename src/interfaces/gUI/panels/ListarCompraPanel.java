package interfaces.gUI.panels;

import java.util.HashMap;

import interfaces.gUI.listeners.ChangeIsBoughtListener;
import interfaces.gUI.listeners.ChangeIsFavouriteListener;
import interfaces.gUI.listeners.ChangeQuantityListener;
import interfaces.gUI.listeners.deleteProductListener;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import modelo.datos.LineaProducto;
import modelo.datos.ListaCompra;
import util.IListaListeners;

public class ListarCompraPanel extends GridPane implements IListaListeners {

	private ListaCompra listaCompra;
	
	/**
	 * @param listaCompra
	 */
	public ListarCompraPanel(ListaCompra listaCompra) {
		this.listaCompra = listaCompra;
		this.listaCompra.addListener(this);
		update();
	}

	@Override
	public void update() {
		
		this.getChildren().clear();
		
		HashMap<String, LineaProducto> liCo = new HashMap<String, LineaProducto>(listaCompra.getListaCompra());
		Integer rowIndex = 0;
		for (String prod : liCo.keySet()) {
			// Nombre del producto
			Label productName = new Label(prod);
			this.add(productName, 0, rowIndex);
			// Cantidad
			Spinner<Integer> productQuantity =  new Spinner<Integer>(1, Integer.MAX_VALUE, liCo.get(prod).getCantidad());
			productQuantity.setEditable(true);
			productQuantity.valueProperty().addListener(new ChangeQuantityListener(listaCompra, prod));
			this.add(productQuantity, 1, rowIndex);
			//Está comprado
			CheckBox isBought = new CheckBox("Comprado");
			isBought.setSelected(liCo.get(prod).getEstaComprado());
			isBought.selectedProperty().addListener(new ChangeIsBoughtListener(listaCompra, prod));
			this.add(isBought, 2, rowIndex);
			//Es Favorito
			CheckBox isFavourite = new CheckBox("Favorito");
			isFavourite.setSelected(listaCompra.getFavoritos().contains(prod));
			this.add(isFavourite, 3, rowIndex);
			isFavourite.selectedProperty().addListener(new ChangeIsFavouriteListener(listaCompra, prod));
			//Eliminar
			Button deleteButton = new Button("Eliminar"); // TODO Node grafphic x para eliminar producto de la lista
			deleteButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new deleteProductListener(listaCompra, prod));
			this.add(deleteButton, 4, rowIndex);
			//Siguiente fila
			rowIndex++;
		}
	}
}
