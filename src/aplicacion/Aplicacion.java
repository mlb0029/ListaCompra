package aplicacion;

import interfaces.tUI.InterfazConsola;

/**
 * Clase donde tiene lugar la función main.
 * 
 * @author CLARA PALACIOS RODRIGO
 */
public class Aplicacion {

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