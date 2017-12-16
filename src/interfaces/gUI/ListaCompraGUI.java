package interfaces.gUI;

import java.util.HashMap;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import modelo.datos.*;
import modelo.persistencia.*;
 
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
			System.out.println("Error al cargar datos"); // TODO Mostrar mensaje de error start
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
    	MenuItem saveList = new MenuItem("Save List");
    	saveList.setOnAction(new SaveListListener(listaCompra, persistencia));
    	MenuItem openList = new MenuItem("Open List");
    	openList.setOnAction(new OpenListListener(listaCompra, persistencia));
    	MenuItem clearList = new MenuItem("Clear List");
    	clearList.setOnAction(new ClearListListener(listaCompra));
    	MenuItem Exit = new MenuItem("Exit");
    	Exit.setOnAction(new ExitListener());
    	menuShoppingList.getItems().addAll(saveList, openList, clearList, Exit);
    	Menu menuFavoritesList = new Menu("Favorites list"); // TODO Implementar funcionalidades Menu Favoritos
    	MenuItem addFavourite = new MenuItem("Add favourite");
    	MenuItem delFavourite = new MenuItem("Remove favourite");
    	MenuItem clearFavourites = new MenuItem("Clear favourites");
    	menuFavoritesList.getItems().addAll(addFavourite, delFavourite, clearFavourites);
    	menuBar.getMenus().addAll(menuShoppingList, menuFavoritesList);
    	root.setTop(menuBar);
    	//CENTER
    	GridPane shoppingList = listarCompra();
    	// TODO Implementar funcionalidades Centro: ListaCompra (EventHandler o EventFilter)
    	root.setCenter(shoppingList);
    	//BOTTOM
    	Text madeBy = new Text("Made by Clara Palacios Rodrigo & Miguel Ángel León Bardavío"); // TODO Centrar autores
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
			// TODO modificar cantidad
			productList.add(productQuantity, 1, rowIndex);
			//Está comprado
			CheckBox isBought = new CheckBox("Comprado");
			isBought.setSelected(liCo.get(prod).getEstaComprado());
			// TODO marcarComprado
			productList.add(isBought, 2, rowIndex);
			//Es Favorito
			CheckBox isFavourite = new CheckBox("Favorito");
			isFavourite.setSelected(listaCompra.getFavoritos().contains(prod));
			productList.add(isFavourite, 3, rowIndex);
			// TODO marcarFavorito
			//Eliminar
			Button deleteButton = new Button("Eliminar"); // TODO Node grafphic x para eliminar producto de la lista
			productList.add(deleteButton, 4, rowIndex);
			//TODO eliminar
			//Siguiente fila
			rowIndex++;
		}
    	return productList;
    }
    
    
	 public static void main(String[] args) {
	        launch(args);
	 }
}