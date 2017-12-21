/**
 * 
 */
package interfaces.gUI.panels;

import interfaces.gUI.listeners.addProductListener;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import modelo.datos.ListaCompra;
import util.IListaListeners;

/**
 * @author Miguel Ángel León
 *
 */
public class AddProductPanel extends VBox implements IListaListeners {

	ListaCompra listaCompra;
	ComboBox<String> productName;
	Spinner<Integer> productQuantity;
	CheckBox isBought;
	CheckBox isFavourite;
	Button addButton;
	
	/**
	 * @param productName
	 * @param productQuantity
	 * @param isBought
	 * @param isFavourite
	 * @param addButton
	 */
	public AddProductPanel(MainPanel mainPanel) {
		
		this.listaCompra = mainPanel.getListaCompra();
		this.listaCompra.addListener(this);
		
		this.productName = new ComboBox<String>();
		productName.getItems().addAll(this.listaCompra.getFavoritos());
		productName.setEditable(true);
		productName.setPromptText("Nombre producto");
		
		this.productQuantity =  new Spinner<Integer>(1, Integer.MAX_VALUE, 1);
		productQuantity.setEditable(true);
		
		this.isFavourite = new CheckBox("Favorito");
		
		this.addButton = new Button("Añadir");
		addButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new addProductListener(this));
		
		this.getChildren().addAll(productName, productQuantity, isFavourite, addButton);
	}
	
	/* (non-Javadoc)
	 * @see util.IListaListeners#update()
	 */
	@Override
	public void update() {
		this.productName.getItems().clear();
		this.productName.getItems().addAll(this.listaCompra.getFavoritos());
	}

	/**
	 * @return the listaCompra
	 */
	public ListaCompra getListaCompra() {
		return listaCompra;
	}

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return this.productName.getValue();
	}

	/**
	 * @return the productQuantity
	 */
	public Integer getProductQuantity() {
		return this.productQuantity.getValue();
	}

	/**
	 * @return the isBought
	 */
	public Boolean getIsBought() {
		return this.isBought.isSelected();
	}

	/**
	 * @return the isFavourite
	 */
	public Boolean getIsFavourite() {
		return this.isFavourite.isSelected();
	}

}
