package modelo.datos;

/**
 * Contiene información relativa a un producto almacenado en la lista de la compra.
 * <p>
 * 
 * @author MIGUEL ANGEL LEON BARDAVIO
 *
 */
public class LineaProducto {
	//private Integer numLinea;
	
	/**
	 * Producto a almacenar en la lista.
	 */
	private Producto producto;
	
	/**
	 * Cantidad del producto a comprar.
	 */
	private Integer cantidad;
	
	/**
	 * Variable que indica si el producto está comprado o no.
	 */
	private Boolean estaComprado;
	
	/**
	 * Constructor de la clase.
	 * <p>
	 * Crea una instancia de la clase asignandole el nombre del producto, la cantidad a comprar. 
	 * Inicialmente el producto no está comprado.
	 * 
	 * @paramproducto Producto a almacenar en la lista.
	 * @param cantidad Cantidad del producto a comprar.
	 * @param esFavorito Variable que indica si el producto est� almacenado como favorito o no.
	 */
	LineaProducto(Producto producto, Integer cantidad) {
		this.producto = producto;
		this.cantidad = cantidad;
		this.estaComprado = false;
	}

	/**
	 * Devuelve el producto asociado a la linea de la lista.
	 * 
	 * @return El producto asociado a la linea de la lista.
	 */
	public Producto getProducto() {
		return producto;
	}

	/**
	 * Devuelve la cantidad de producto que se desea comprar.
	 * 
	 * @return La cantidad de producto que se desea comprar.
	 */
	public Integer getCantidad() {
		return cantidad;
	}

	
	/**
	 * Modifica la cantidad de producto que se desea comprar, siempre que no sea un valor negativo.
	 * 
	 * @param cantidad Cantidad de un producto que se desea comprar.
	 * @return Devuelve true si se ha modificado la cantidad y false en caso contrario.
	 */
	boolean setCantidad(Integer cantidad) {
		if (cantidad > 0) {
			this.cantidad = cantidad;
			return true;
		}
		return false;
	}

	/**
	 * Devuelve si el producto est� marcado como comprado o no.
	 * 
	 * @return Devuelve true si el producto ha sido comprado y false en caso contrario.
	 */
	public Boolean getEstaComprado() {
		return estaComprado;
	}
	
	/**
	 * @param estaComprado the estaComprado to set
	 */
	public void setEstaComprado(Boolean estaComprado) {
		this.estaComprado = estaComprado;
	}

	/**
	 * Convierte a string.
	 * 
	 * @author CLARA PALACIOS RODRIGO
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Producto:" + producto.getNombre() + ", Cantidad:" + cantidad + ", Esta Comprado:" + estaComprado + "\n";
	}
}
