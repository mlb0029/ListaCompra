package modelo;

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
	 * Crea una instancia de la clase asignandole el nombre del producto, la cantidad a comprar y si se 
	 * va a almacenar el producto como favorito o no. Inicialmente el producto no está comprado.
	 * 
	 * @paramproducto Producto a almacenar en la lista.
	 * @param cantidad Cantidad del producto a comprar.
	 * @param esFavorito Variable que indica si el producto está almacenado como favorito o no.
	 */
	public LineaProducto(Producto producto, Integer cantidad) {
		this.producto = producto;
		this.cantidad = cantidad;
		this.estaComprado = false;
		//TODO Comprobar errores en LineaProducto(Producto producto, Integer cantidad)
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
	public boolean setCantidad(Integer cantidad) {
		if (cantidad > 0) {
			this.cantidad = cantidad;
			return true;
		}
		return false;
	}

	/**
	 * Devuelve si el producto está marcado como comprado o no.
	 * 
	 * @return Devuelve true si el producto ha sido comprado y false en caso contrario.
	 */
	public Boolean getEstaComprado() {
		return estaComprado;
	}

	
	/**
	 * Marca un producto de la lista como comprado.
	 */
	public void marcarComprado() {
		this.estaComprado = true;
	}
	
	/**
	 * Marca un producto de la lista como no comprado. 
	 */
	public void desmarcarComprado() {
		this.estaComprado = false;
	}
}
