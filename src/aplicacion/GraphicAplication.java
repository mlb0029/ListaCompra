package aplicacion;

import java.io.FileNotFoundException;

import interfaces.gUI.panels.ErrorMessageAlert;
import interfaces.gUI.panels.MainPanel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modelo.datos.*;
import modelo.persistencia.*;
 
/**
 * Aplicación gráfica.
 * 
 * @author CLARA PALACIOS RODRIGO
 *
 */
public class GraphicAplication extends Application {
    
	/**
	 * Ventana principal.
	 */
	private Stage mainStage = null;
	
	/**
	 * Lista de la compra.
	 */
	private ListaCompra listaCompra;
	
	/**
	 * Modelo de persistencia.
	 */
	private IPersistencia persistencia;
	
    /* (non-Javadoc)
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    @Override
    public void start(Stage primaryStage) {
    	//Lista Compra
    	this.listaCompra = ListaCompra.getInstance();
		this.persistencia = new PersistenciaListaCompra();
		try {
			listaCompra = persistencia.cargarCont();
		} catch (FileNotFoundException e) {
			try {
				ErrorMessageAlert.showErrorMsg("No se encuentra el fichero");
				this.persistencia.guardarContenido(listaCompra);
			} catch (Exception e1) {
				ErrorMessageAlert.showErrorMsg("Error al cargar los datos del fichero");
				System.exit(1);
			}
		}
		catch (Exception e) {
			ErrorMessageAlert.showErrorMsg("Error al cargar los datos del fichero");
			System.exit(1);
		}
		//GUI
        this.mainStage = primaryStage;
        this.mainStage.setTitle("Shopping List");
		this.mainStage.setScene(new Scene(new MainPanel(listaCompra, persistencia)));
		this.mainStage.show();
    }
    
	 /**
	  * Función main().
	  * 
	 * @param args Argumentos.
	 */
	public static void main(String[] args) {
	        launch(args);
	 }
}