/**
 * 
 */
package interfaces.gUI.panels;

import interfaces.gUI.listeners.ClearFavouritesListener;
import interfaces.gUI.listeners.ClearListListener;
import interfaces.gUI.listeners.ExitListener;
import interfaces.gUI.listeners.OpenListListener;
import interfaces.gUI.listeners.SaveListListener;
import interfaces.gUI.listeners.addFavouriteListener;
import interfaces.gUI.listeners.delFavouriteListener;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import modelo.datos.ListaCompra;
import modelo.persistencia.IPersistencia;
import util.IListaListeners;

/**
 * @author Miguel Ángel León
 *
 */
public class BarraMenus extends MenuBar implements IListaListeners {



	ListaCompra listaCompra;
	
	IPersistencia persistencia;
	
	/**
	 * @param listaCompra
	 * @param persistencia
	 */
	public BarraMenus(MainPanel mainPanel) {
		
		this.listaCompra = mainPanel.getListaCompra();
		this.persistencia = mainPanel.getPersistencia();
		
    	Menu menuShoppingList = new Menu("Shopping list");
    	MenuItem saveList = new MenuItem("Save List");
    	saveList.setOnAction(new SaveListListener(listaCompra, persistencia));
    	MenuItem openList = new MenuItem("Open List");
    	openList.setOnAction(new OpenListListener(listaCompra, persistencia));
    	MenuItem clearList = new MenuItem("Clear List");
    	clearList.setOnAction(new ClearListListener(listaCompra));
    	MenuItem Exit = new MenuItem("Exit");
    	Exit.setOnAction(new ExitListener());
    	menuShoppingList.getItems().addAll(saveList, openList, clearList, Exit);
    	Menu menuFavoritesList = new Menu("Favorites list");
    	MenuItem addFavourite = new MenuItem("Add favourite");
    	addFavourite.setOnAction(new addFavouriteListener(listaCompra));
    	MenuItem delFavourite = new MenuItem("Remove favourite");
    	delFavourite.setOnAction(new delFavouriteListener(listaCompra));
    	MenuItem clearFavourites = new MenuItem("Clear favourites");
    	clearFavourites.setOnAction(new ClearFavouritesListener(listaCompra));
    	menuFavoritesList.getItems().addAll(addFavourite, delFavourite, clearFavourites);
    	this.getMenus().addAll(menuShoppingList, menuFavoritesList);
	}
	
	/* (non-Javadoc)
	 * @see util.IListaListeners#update()
	 */
	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
