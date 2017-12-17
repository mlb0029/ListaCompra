package interfaces.gUI;

import java.util.HashMap;
import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
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
			Alert errorMessage = new Alert(AlertType.ERROR);
			errorMessage.setHeaderText("Error al abrir el fichero");
			errorMessage.showAndWait();
			System.exit(1);
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
    	Menu menuFavoritesList = new Menu("Favorites list");
    	MenuItem addFavourite = new MenuItem("Add favourite");
    	addFavourite.setOnAction(new addFavouriteListener(listaCompra));
    	MenuItem delFavourite = new MenuItem("Remove favourite");
    	delFavourite.setOnAction(new delFavouriteListener(listaCompra));
    	MenuItem clearFavourites = new MenuItem("Clear favourites");
    	clearFavourites.setOnAction(new ClearFavouritesListener(listaCompra));
    	menuFavoritesList.getItems().addAll(addFavourite, delFavourite, clearFavourites);
    	menuBar.getMenus().addAll(menuShoppingList, menuFavoritesList);
    	root.setTop(menuBar);
    	//CENTER
    	GridPane shoppingList = listarCompra();
    	// TODO Implementar funcionalidades Centro: ListaCompra (EventHandler o EventFilter)
    	root.setCenter(shoppingList);
    	//BOTTOM
    	Text madeBy = new Text("Made by Clara Palacios Rodrigo & Miguel Ángel León Bardavío");
    	BorderPane.setAlignment(madeBy, Pos.CENTER);
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
			productQuantity.valueProperty().addListener(new ChangeQuantityListener(listaCompra, productName));
			productList.add(productQuantity, 1, rowIndex);
			//Está comprado
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
			deleteButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new deleteProductListener(listaCompra, productName));
			productList.add(deleteButton, 4, rowIndex);
			//Siguiente fila
			rowIndex++;
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
		addButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new addProductListener(listaCompra, productName, productQuantity, isFavourite));
		productList.add(addButton, 4, rowIndex);
    	return productList;
    }
    
	 public static void main(String[] args) {
	        launch(args);
	 }
}