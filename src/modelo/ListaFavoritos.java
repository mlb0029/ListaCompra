package modelo;

import java.util.HashSet;

/**
 * Contiene una forma de almacenar productos favoritos y métodos que permiten operar con ellos.
 * 
 * @author MIGUEL ANGEL LEON BARDAVIO
 *
 */
public class ListaFavoritos {

	/**
	 * Conjunto de productos que se almacenarán como favoritos.
	 */
	HashSet<String> listaFavoritos;
	
	/**
	 * Inicializa listaFavoritos inicialmente vacía.
	 */
	public ListaFavoritos() {
		listaFavoritos = new HashSet<String>();
	}

	
}
