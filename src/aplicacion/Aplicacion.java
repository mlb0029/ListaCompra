package aplicacion;

import interfaces.InterfazConsola;

/**
 * Clase Aplicación.
 * <p>
 * Clase donde tiene lugar la función main.
 * 
 * @author MIGUEL ANGEL LEON BARDAVIO
 *
 *
 */
public class Aplicacion {

	/**
	 * Nombre del fichero dónde se almacenan los datos.
	 */
	public static final String fileListaCompra = "listaCompra.txt";

	/**
	 * Función main.
	 * 
	 * @param args Argumentos
	 */
	public static void main(String[] args) {
		InterfazConsola apliListaCompra = new InterfazConsola();
    	apliListaCompra.muestraMenu();		
	}
}