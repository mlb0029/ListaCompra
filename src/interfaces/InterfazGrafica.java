package interfaces;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

/**
 * Interfaz gráfica de la aplicación.
 * 
 * @author CLARA PALACIOS RODRIGO
 *
 */
public class InterfazGrafica extends Application {
	
	/**
	 * Escenario principal.
	 */
	private Stage mainStage = null;

	/**
	 * Método de inicialización.
	 */
	@Override
	public void start(Stage mainStage) throws Exception {
		this.mainStage = mainStage;
		this.mainStage.setTitle("Lista  de la Compra");
		this.mainStage.setScene(setupMainScene());
		this.mainStage.show();
	}

	public Scene setupMainScene() {
		// Group root = new Group();
		VBox root = new VBox();
		root.setSpacing(10.0);
		root.setPadding(new Insets(10.0, 10.0, 10.0, 10.0));
		HBox textForm = new HBox();
		textForm.setSpacing(10.0);
		textForm.setPadding(new Insets(10.0, 10.0, 10.0, 10.0));
		textForm.setAlignment(Pos.CENTER_LEFT);
		root.getChildren().addAll(textForm);
		TextField form1_ProductName = new TextField("Leche");
		form1_ProductName.setPrefWidth(100.0);
		Spinner<Integer> form2_Amount =  new Spinner<Integer>(1, Integer.MAX_VALUE, 1);
		form2_Amount.setEditable(true);
		form2_Amount.setPrefWidth(100.0);
		CheckBox form3_SetComprado = new CheckBox("Comprado");
		CheckBox form4_SetFavorito = new CheckBox("Favorito");
		textForm.getChildren().addAll(form1_ProductName, form2_Amount, form3_SetComprado, form4_SetFavorito);

		// botones
		HBox buttons = new HBox();
		buttons.setPadding(new Insets(10.0, 5.0, 10.0, 10.0));
		buttons.setAlignment(Pos.CENTER_RIGHT);
		Button submitButton = new Button("Capturar");
		submitButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new TextGrabButtonListener(form1_ProductName));
		buttons.getChildren().addAll(submitButton);
		root.getChildren().addAll(buttons);
		Scene scene = new Scene(root, 640, 360);
		return scene;
	}

	public class TextGrabButtonListener implements EventHandler<MouseEvent> {
		private TextField textField = null;

		public TextGrabButtonListener(TextField textField) {
			this.textField = textField;
		}

		@Override
		public void handle(MouseEvent evt) {
			Button source = (Button) evt.getSource();
			source.setText(this.textField.getText());
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
