package interfaces.gUI;

import java.util.HashMap;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
	public Button ok = null;

	@Override
	public void start(Stage primaryStage) {
		// Lista Compra
		this.listaCompra = ListaCompra.getInstance();
		this.persistencia = new PersistenciaListaCompra();
		try {
			listaCompra = persistencia.cargarCont();
		} catch (Exception e) {
			System.out.println("Error al cargar datos"); // TODO Mostrar mensaje
															// de error start
		}
		// GUI
		this.mainStage = primaryStage;
		this.mainStage.setTitle("Shopping List");
		this.mainStage.setScene(setupMainScene());
		this.mainStage.show();
	}

	Scene setupMainScene() {
		BorderPane root = new BorderPane();
		root.setId("root");

		// TOP
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
		Menu menuFavoritesList = new Menu("Favorites list"); // TODO Implementar
																// funcionalidades
																// Menu
																// Favoritos
		MenuItem addFavourite = new MenuItem("Add favourite");
		MenuItem delFavourite = new MenuItem("Remove favourite");
		MenuItem clearFavourites = new MenuItem("Clear favourites");
		menuFavoritesList.getItems().addAll(addFavourite, delFavourite, clearFavourites);
		menuBar.getMenus().addAll(menuShoppingList, menuFavoritesList);
		root.setTop(menuBar);
		// CENTER
		HashMap<String, LineaProducto> liCo = new HashMap<String, LineaProducto>(listaCompra.getListaCompra());
		GridPane shoppingList = listarCompra(liCo);
		shoppingList.setId("shopList");

		GridPane modifi = modificar(liCo);

		// TODO Implementar funcionalidades Centro: ListaCompra (EventHandler o
		// EventFilter)
		root.setLeft(shoppingList);
		root.setCenter(modifi);
		// BOTTOM
		Text madeBy = new Text("\t\tMade by Clara Palacios Rodrigo & Miguel Ángel León Bardavío"); // TODO
																									// Centrar
																									// autores
		madeBy.setTextAlignment(TextAlignment.CENTER);
		root.setBottom(madeBy);

		HBox contenido = new HBox();

		return new Scene(root, 740, 260);
	}

	public GridPane listarCompra(HashMap<String, LineaProducto> liCo) {

		GridPane productList = new GridPane();
		Integer rowIndex = 0;

		for (String prod : liCo.keySet()) {
			// Nombre del producto
			Label product = new Label(rowIndex + "-Producto:");
			Label canti = new Label(" Cantidad:");
			Label comprado = new Label(" Comprado:");
			Label favo = new Label(" Favorito:");

			Label productName = new Label(prod);
			productList.add(product, 0, rowIndex);
			productList.add(productName, 1, rowIndex);
			// Cantidad

			Label productQuantity = new Label(liCo.get(prod).getCantidad().toString());
			// TODO modificar cantidad
			productList.add(canti, 2, rowIndex);
			productList.add(productQuantity, 3, rowIndex);

			// Está comprado
			Label isBought = new Label(liCo.get(prod).getEstaComprado().toString());
			// TODO marcarComprado
			productList.add(comprado, 4, rowIndex);
			productList.add(isBought, 5, rowIndex);

			// Es Favorito
			Label isFavourite = new Label(String.valueOf(listaCompra.getFavoritos().contains(prod)));

			productList.add(favo, 6, rowIndex);
			productList.add(isFavourite, 7, rowIndex);
			// TODO marcarFavorito

			// TODO eliminar
			// Siguiente fila
			rowIndex++;

		}
		return productList;
	}

	public GridPane modificar(HashMap<String, LineaProducto> liCo) {

		GridPane productList = new GridPane();
		Integer rowIndex = liCo.size();

		Label canti = new Label(" Cantidad:");
		TextField cantText = new TextField();
		cantText.setId("cantText");

		Label comprado = new Label(" Comprado:");
		TextField compradoText = new TextField();
		compradoText.setId("compradoText");

		Label favo = new Label(" Favorito:");
		TextField favoText = new TextField();
		favoText.setId("favoText");

		Label lineaModificar = new Label("Linea a modificar:");
		TextField linModifi = new TextField();
		linModifi.setId("linModifi");

		productList.add(lineaModificar, 0, rowIndex);
		productList.add(linModifi, 1, rowIndex);
		rowIndex += 1;
		productList.add(canti, 0, rowIndex);
		productList.add(cantText, 1, rowIndex);
		rowIndex += 1;

		productList.add(comprado, 0, rowIndex);
		productList.add(compradoText, 1, rowIndex);
		rowIndex += 1;

		productList.add(favo, 0, rowIndex);
		productList.add(favoText, 1, rowIndex);

		rowIndex += 1;
		this.ok = new Button("Aceptar Modificar");
		this.ok.setOnAction(new ExitListener());

		productList.add(ok, 0, rowIndex);
		ok.setOnAction(new AceptarModifi(this.mainStage, this.listaCompra));

		return productList;
	}

	public static void main(String[] args) {
		launch(args);
	}
}