package interfaces.gUI.panels;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import modelo.datos.ListaCompra;
import modelo.persistencia.IPersistencia;

/**
 * Nodo proncipal de la interfaz gráfica.
 * 
 * @author MIGUEL ANGEL LEON BARDAVIO
 *
 */
public class MainPanel extends BorderPane {

	/**
	 * Referencia a la lista de la compra.
	 */
	private ListaCompra listaCompra;
	
	/**
	 * Referencia a modelo de persistencia.
	 */
	private IPersistencia persistencia;
	
	/**
	 * Costructor.
	 * 
	 * @param listaCompra Lista de la compra.
	 * @param persistencia Modelo de Persistencia.
	 */
	public MainPanel(ListaCompra listaCompra, IPersistencia persistencia) {
		
		this.listaCompra = listaCompra;
		this.persistencia = persistencia;
		
		//TOP
		this.setTop(new BarraMenus(this));
		
		//LEFT
		this.setLeft(new AddProductPanel(this));
		
		//CENTER
		this.setCenter(new ScrollPane(new ListarCompraPanel(listaCompra)));
		
		//BOTTOM
		Label madeBy = new Label("CLARA PALACIOS RODRIGO & MIGUEL ANGEL LEON BARDAVIO");
		this.setBottom(madeBy);
		MainPanel.setAlignment(madeBy, Pos.CENTER);
	}

	/**
	 * @return the listaCompra
	 */
	public ListaCompra getListaCompra() {
		return listaCompra;
	}

	/**
	 * @return the persistencia
	 */
	public IPersistencia getPersistencia() {
		return persistencia;
	}

}
