package modelo.datos;

/**
 * Almacena informaci�n acerca de los productos.
 * 
 * @author MIGUEL ANGEL LEON BARDAVIO
 *
 */
public class Producto {
	
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
	 * @param esFavorito the esFavorito to set
	 */
	public void setEsFavorito(Boolean esFavorito) {
		this.esFavorito = esFavorito;
	}

	@Override
	public String toString() {
		return  nombre + ", Es Favorito:" + esFavorito;
	}
	
}
