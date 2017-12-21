package interfaces.gUI.listeners;

import interfaces.gUI.panels.ErrorMessageAlert;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import modelo.datos.ListaCompra;
import modelo.persistencia.IPersistencia;

/**
 * Delega en IPersitencia almacenar los datos de ListaCompra.
 * 
 * @author Miguel Ángel León
 * @see IPersistencia
 * @see ListaCompra
 */
public class SaveListListener implements EventHandler<ActionEvent>{

	/**
	 * Lista de la compra.
	 */
	ListaCompra listaCompra;
	
	/**
	 * Persistencia.
	 */
	IPersistencia persitence;
	
	/**
	 * Constructor que recibe la lista de la compra y el modelo de persistencia.
	 * 
	 * @param liCo Lista compra.
	 * @param persitence Persitencia.
	 */
	public SaveListListener(ListaCompra liCo, IPersistencia persitence) {
		this.listaCompra = liCo;
		this.persitence = persitence;
	}

	/* (non-Javadoc)
	 * @see javafx.event.EventHandler#handle(javafx.event.Event)
	 */
	@Override
	public void handle(ActionEvent arg0) {
		try {
			this.persitence.guardarContenido(listaCompra);
		} catch (Exception e) {
			ErrorMessageAlert.showErrorMsg("Error al guardar");
		}
	}

}
