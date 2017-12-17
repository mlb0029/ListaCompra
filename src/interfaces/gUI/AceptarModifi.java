package interfaces.gUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import modelo.datos.LineaProducto;
import modelo.datos.ListaCompra;
import modelo.datos.Producto;

/**
 * Cierra la aplicación.
 * 
 * @author Miguel Ángel León
 */
public class AceptarModifi implements EventHandler<ActionEvent> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.event.EventHandler#handle(javafx.event.Event)
	 */
	private Stage ventana;
	private ListaCompra lista;

	public AceptarModifi(Stage stage, ListaCompra lista) {
		this.lista = lista;
		this.ventana = stage;

	}

	@Override
	public void handle(ActionEvent arg0) {
		TextField linModifi = (TextField) ventana.getScene().lookup("#linModifi");
		// System.out.println(linModifi.getText());

		TextField cantText = (TextField) ventana.getScene().lookup("#cantText");
		// System.out.println(cantText.getText());

		TextField compradoText = (TextField) ventana.getScene().lookup("#compradoText");
		// System.out.println(compradoText.getText());

		TextField favoText = (TextField) ventana.getScene().lookup("#favoText");
		// System.out.println(favoText.getText());

		HashMap<String, LineaProducto> lista = this.lista.getListaCompra();
		if (!linModifi.getText().isEmpty()) {
			if (Integer.parseInt(linModifi.getText()) < lista.keySet().size()) {

				System.out.println("NO ENTRA");
				ArrayList<String> nombreslista = new ArrayList<String>(lista.keySet());
				String nombreprod = nombreslista.get(Integer.parseInt(linModifi.getText()));
				if (!cantText.getText().isEmpty()) {
					this.lista.modificarCantidad(nombreprod, Integer.parseInt(cantText.getText()));
				}

				if (!compradoText.getText().isEmpty()) {
					if (compradoText.getText().toUpperCase().equals("SI")) {
						this.lista.setComprado(nombreprod, true);
					}
					if (compradoText.getText().toUpperCase().equals("NO")) {
						this.lista.setComprado(nombreprod, false);
					}
				}

				if (!favoText.getText().isEmpty()) {
					if (favoText.getText().toUpperCase().equals("SI")) {
						this.lista.anadirFavorito(nombreprod);
					}
					if (favoText.getText().toUpperCase().equals("NO")) {
						this.lista.eliminarFavorito(nombreprod);
					}
				}

				// Actualizar ventana
				BorderPane root = (BorderPane) ventana.getScene().lookup("#root");
				// esto es un ejemplo para demostrar que funciona.
				root.setLeft(listarCompra(this.lista.getListaCompra()));

				// limpiar datos
				linModifi.setText("");
				cantText.setText("");
				compradoText.setText("");
				favoText.setText("");
			}
		}

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
			Label isFavourite = new Label(String.valueOf(lista.getFavoritos().contains(prod)));

			productList.add(favo, 6, rowIndex);
			productList.add(isFavourite, 7, rowIndex);
			// TODO marcarFavorito

			// TODO eliminar
			// Siguiente fila
			rowIndex++;
		}
		return productList;
	}
}
