package aplicacion;

import java.awt.List;
import java.util.ArrayList;
import java.util.Map;

import interfaces.InterfazConsola;
import persistencia.IPersistencia;
import persistencia.PersistenciaLista;
import modelo.LineaProducto;
import modelo.Producto;
@SuppressWarnings("unused")

/**
 * Clase Aplicaci�n.
 * <p>
 * Clase donde tiene lugar la funci�n main.
 * 
 * @author MIGUEL ANGEL LEON BARDAVIO
 *
 *
 */
public class Aplicacion {

	/**
	 * Nombre del fichero d�nde se almacenan los datos.
	 */
	public static final String fileListaCompra = "listaCompra.txt";

	/**
	 * Funci�n main.
	 * 
	 * @param args Argumentos
	 */
	public static void main(String[] args) {
		InterfazConsola apliListaCompra = new InterfazConsola();
    	apliListaCompra.muestraMenu();		
		
	}

}
