package modelo;

import java.util.HashSet;

/**
 * Contiene una forma de almacenar productos favoritos y m�todos que permiten operar con ellos.
 * 
 * @author MIGUEL ANGEL LEON BARDAVIO
 *
 */
public class ListaFavoritos {

	/**
	 * Conjunto de productos que se almacenar�n como favoritos.
	 */
	HashSet<String> listaFavoritos;
	
	/**
	 * Inicializa listaFavoritos inicialmente vac�a.
	 */
	public ListaFavoritos() {
		listaFavoritos = new HashSet<String>();
	}

	
}
