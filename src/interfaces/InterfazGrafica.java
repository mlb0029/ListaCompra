package interfaces;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

public class InterfazGrafica extends Application {
	private Stage primaryStage = null;

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Lista  de la Compra");
		this.primaryStage.setScene(setupMainScene());
		this.primaryStage.show();
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
		Label fieldLabel = new Label("Nombre: ");
		fieldLabel.setPrefWidth(50.0);
		TextField fieldInput = new TextField("Leche");
		fieldInput.setPrefWidth(100.0);
		Label fieldLabel2 = new Label("Cantidad: ");
		fieldLabel.setPrefWidth(50.0);
		TextField fieldInput2 = new TextField("1");
		fieldInput.setPrefWidth(100.0);

		textForm.getChildren().addAll(fieldLabel, fieldInput,fieldLabel2, fieldInput2 );

		// botones
		HBox buttons = new HBox();
		buttons.setPadding(new Insets(10.0, 5.0, 10.0, 10.0));
		buttons.setAlignment(Pos.CENTER_RIGHT);
		Button submitButton = new Button("Capturar");
		submitButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new TextGrabButtonListener(fieldInput));
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
