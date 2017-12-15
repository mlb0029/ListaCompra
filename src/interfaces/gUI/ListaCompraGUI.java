/**
 * 
 */
package interfaces.gUI;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import modelo.datos.LineaProducto;
import modelo.datos.ListaCompra;
import modelo.persistencia.IPersistencia;
import modelo.persistencia.PersistenciaListaCompra;
 
public class ListaCompraGUI extends Application {
    
	private Stage mainStage = null;
	
	ListaCompra listaCompra;
	
	IPersistencia persistencia;
	
    @Override
    public void start(Stage primaryStage) {
    	//Lista Compra
    	this.listaCompra = ListaCompra.getInstance();
		this.persistencia = new PersistenciaListaCompra();
		try {
			listaCompra = persistencia.cargarCont();
		} catch (Exception e) {
			System.out.println("Error al cargar datos");
		}
		//GUI
        this.mainStage = primaryStage;
        this.mainStage.setTitle("Shopping List");
		this.mainStage.setScene(setupMainScene());
		this.mainStage.show();
    }
    
    Scene setupMainScene(){
    	BorderPane root = new BorderPane();
    	//TOP
    	MenuBar menuBar = new MenuBar();
    	Menu menuShoppingList = new Menu("Shopping list");
    	MenuItem saveclearList = new MenuItem("Save List");
    	saveclearList.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				try {
					persistencia.guardarContenido(listaCompra);
				} catch (Exception e) {
					System.out.println("Error inesperado"); // TODO
				}
			}
		});
    	MenuItem openList = new MenuItem("Open List");
    	openList.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					listaCompra = persistencia.cargarCont();
				} catch (Exception e) {
					System.out.println("Error inesperado");
				}
			}
		});
    	MenuItem clearList = new MenuItem("Clear List");
    	clearList.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				listaCompra.limpiarListaCompra();
			}
		});
    	MenuItem Exit = new MenuItem("Exit");
    	Exit.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				System.exit(0);
			}
		});
    	menuShoppingList.getItems().addAll(saveclearList, openList, clearList, Exit);
    	Menu menuFavoritesList = new Menu("Favorites list");
    	MenuItem addFavourite = new MenuItem("Add favourite");
    	MenuItem delFavourite = new MenuItem("Remove favourite");
    	MenuItem clearFavourites = new MenuItem("Clear favourites");
    	menuFavoritesList.getItems().addAll(addFavourite, delFavourite, clearFavourites);
    	menuBar.getMenus().addAll(menuShoppingList, menuFavoritesList);
    	root.setTop(menuBar);
    	//CENTER
    	GridPane shoppingList = listarCompra();
    	root.setCenter(shoppingList);
    	//BOTTOM
    	Text madeBy = new Text("Made by Clara Palacios Rodrigo & Miguel Ángel León Bardavío");
    	madeBy.setTextAlignment(TextAlignment.CENTER);
    	root.setBottom(madeBy);
    	return new Scene(root, 640, 360);
    }
    GridPane listarCompra(){
		HashMap<String, LineaProducto> liCo = new HashMap<String, LineaProducto>(listaCompra.getListaCompra());
    	GridPane productList = new GridPane();
    	Integer rowIndex = 0;
		for (String prod : liCo.keySet()) {
			// Nombre del producto
			Label productName = new Label(prod);
			productList.add(productName, 0, rowIndex);
			// Cantidad
			Spinner<Integer> productQuantity =  new Spinner<Integer>(1, Integer.MAX_VALUE, liCo.get(prod).getCantidad());
			productQuantity.setEditable(true);
			productList.add(productQuantity, 1, rowIndex);
			//Está comprado
			CheckBox isBought = new CheckBox("Comprado");
			isBought.setSelected(liCo.get(prod).getEstaComprado());
			productList.add(isBought, 2, rowIndex);
			//Es Favorito
			CheckBox isFavourite = new CheckBox("Favorito");
			isFavourite.setSelected(listaCompra.getFavoritos().contains(prod));
			productList.add(isFavourite, 3, rowIndex);
			//Eliminar
			Button deleteButton = new Button("Eliminar"); // TODO Node grafphic x
			productList.add(deleteButton, 4, rowIndex);
			//Siguiente fila
			rowIndex++;
		}
    	return productList;
    }
    
    
	 public static void main(String[] args) {
	        launch(args);
	 }
}