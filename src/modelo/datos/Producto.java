package modelo.datos;

/**
 * Almacena informaci�n acerca de los productos.
 * 
 * @author MIGUEL ANGEL LEON BARDAVIO
 *
 */
public class Producto {
	
	@Override
	public String toString() {
		return  nombre + ", Es Favorito:" + esFavorito;
	}

	/**
	 * Nombre del producto.
	 */
	private String nombre;
	
	/**
	 * Indica si el producto est� marcado como favorito o no.
	 */
	private Boolean esFavorito;

	/**
	 * Instancia la clase seg�n los argumentos que se le pasen.
	 * 
	 * @param nombre Nombre del producto.
	 * @param esFavorito Indica si el producto est� marcado como favorito o no.
	 */
	Producto(String nombre, Boolean esFavorito) {
		this.nombre = nombre;
		this.esFavorito = esFavorito;
	}

	/**
	 * Devuelve el nombre del producto.
	 * 
	 * @return El nombre del producto.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @return Si est� marcado como favorito o no.
	 */
	public Boolean isFavorito() {
		return esFavorito;
	}

	/**
	 * Marca el producto como favorito.
	 */
	void marcarFavorito() {
		this.esFavorito = true;
	}
	
	/**
	 * Marca el producto como favorito.
	 */
	void desmarcarFavorito() {
		this.esFavorito = false;
	}
	
	/**
	 * Marca o desmarca el producto como favorito seg�n su estado actual.
	 */
	void setFavorito() {
		this.esFavorito = !this.esFavorito;
	}
	
}
