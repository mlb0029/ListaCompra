package modelo.datos;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Almacena los datos de lista de la compra &lt;&lt;Singleton&gt;&gt;.
 * <p>
 * Almacena la lista de la compra y la lista de favoritos.
 * 
 * @author MIGUEL ANGEL LEON BARDAVIO
 */
/**
 * @author MIGUEL ANGEL LEON BARDAVIO
 *
 */
public class ListaCompra {
	//***********************************
	// ATRIBUTOS
	//***********************************
	/**
	 * Permite desarrillar la clase como &lt;&lt;Singleton&gt;&gt;.
	 */
	private static ListaCompra instance = null;
	
	/**
	 * Almacena una lista de productos.
	 */
	private HashMap<String, Producto> listaProductos;

	/**
	 * Almacena la lista de la compra.
	 */
	private HashMap<String, LineaProducto> listaCompra;
	
	//***********************************
	// MÉTODOS
	//***********************************
	
	//Contructor e instancias
	/**
	 * Instancia la clase con las listas vacias.
	 */
	private ListaCompra() {
		this.listaCompra = new HashMap<String, LineaProducto>();
		this.listaProductos = new HashMap<String, Producto>();
	}	
	
	/**
	 * Devuelve la única instancia de la clase e instancia la clase si no hay instancia.
	 * 
	 * @return La única instancia de la clase
	 */
	public static ListaCompra getInstance() {
		if (instance == null)
			instance = new ListaCompra();
		return instance;
	}
	
	//Tamaños
	
	/**
	 * Devuelve el número de productos que contiene la lista de la compra.
	 * 
	 * @return Número de productos que contiene la lista de la compra.
	 */
	public Integer size() {
		return listaCompra.size();
	}

	/**
	 * Devuelve el número de productos que contiene la lista de la compra más los que están almacenados como
	 * favoritos y no están en la lista de la compra.
	 * 
	 * @return N�mero total de productos.
	 */
	public Integer numProductos() {
		return listaProductos.size();
	}

	/**
	 * Devuelve el número de productos que se almacenan como favoritos.
	 * 
	 * @return Número de productos que se almacenan como favoritos.
	 */
	public Integer numFavoritos() {
		return getFavoritos().size();
	}

	//Getters 
	
	/**
	 * Devuelve la lista de la compra en una estructura "Map".
	 * <p>
	 * Claves serán el nómbre del producto y Valores las estructuras que contienen información acerca de este.
	 * No permitirán cambios las estructuras que se utilicen.
	 * 
	 * @return Lista de la compra.
	 * @see Map
	 * @see modelo.datos.LineaProducto
	 */
	public HashMap<String, LineaProducto> getListaCompra() {
		return new HashMap<String, LineaProducto>(listaCompra);
	}
	
	/**
	 * Devuelve la lista de los productos existentes.
	 * <p>
	 * HasMap&lt;String, Producto&gt; de la lista de productos.
	 * 
	 * @return La lista de los productos existentes.
	 */
	public HashMap<String, Producto> getListaProductos(){
		return listaProductos;
	}

	/**
	 * Devuelve una Collección que contiene los nombres de los productos favoritos.
	 * 
	 * @return Productos favoritos.
	 */
	public HashSet<String> getFavoritos(){
		HashSet<String> retorno = new HashSet<String>();
		for (Producto producto : listaProductos.values())
			if (producto.isFavorito())
				retorno.add(producto.getNombre());
		return retorno;
	}

	/**
	 * Devuelve la linea de la lista asociada a un producto.
	 * 
	 * @param nombreProducto Nombre del prducto.
	 * @return Linea de un producto (Producto y cantidad) o null si no existe.
	 * @see LineaProducto
	 */
	public LineaProducto getLineaProducto(String nombreProducto) {
		return this.listaCompra.getOrDefault(nombreProducto, null);
	}
	
	/**
	 * Devuelve un producto dado su nombre.
	 * 
	 * @param nombreProducto Nombre del producto del cual se desea obtener información.
	 * @return Producto o null si el producto no existe.
	 */
	public Producto getProducto(String nombreProducto) {
		return listaProductos.getOrDefault(nombreProducto, null);
	}
	
	//Setters
	
	/**
	 * Añade un producto a la lista de la compra.
	 * <p>
	 * Inicialmente no favorito, para almacenarlo como favorito usar "anadirFavorito(String)".
	 * Se lleva la lista de la compra y la de favoritos independientemente.
	 * Si el producto está en la lista de la compra, no se efectúan cambios y se devuelve false.
	 * 
	 * @param nombreProducto Nombre del producto a comprar.
	 * @param cantidad Cantidad del producto a comprar.
	 * @return True si se ha añadido, False en caso contario.
	 * @see IListaCompra#anadirFavorito(String)
	 */
	public Boolean anadirProducto(String nombreProducto, Integer cantidad) {
		Boolean retorno = false;
		Producto producto = getProducto(nombreProducto);
		if (producto == null) {
			producto = new Producto(nombreProducto, false);
			LineaProducto lineaProducto = new LineaProducto(producto, cantidad);
			listaProductos.put(nombreProducto, producto);
			listaCompra.put(nombreProducto, lineaProducto);
			retorno = true;
		}else if (!listaCompra.containsKey(nombreProducto)) {
			LineaProducto lineaProducto = new LineaProducto(producto, cantidad);
			listaCompra.put(nombreProducto, lineaProducto);
			retorno = true;
		}
		return retorno;
	}


	/**
	 * Modifica la cantidad a comprar de un producto.
	 * <p>
	 * Si se introducie cantidad menor o igual que cero o no existe el producto en la lista de la compra,
	 * no se efectúan cambios y se devuelve false.
	 * 
	 * @param nombreProducto Nombre del producto del cual se va a modificar la cantidad a comprar.
	 * @param cantidad Nueva cantidad.
	 * @return True si ha habido cambios, False en caso contario.
	 */
	public Boolean modificarCantidad(String nombreProducto, Integer cantidad) {
		Boolean retorno = false;
		LineaProducto liProd = getLineaProducto(nombreProducto);
		if (liProd != null) {
			retorno = liProd.setCantidad(cantidad);
		}
		return retorno;
	}

	/**
	 * Marca o desmarca un producto como comprado.
	 * 
	 * @param nombreProducto Nombre del producto a marcar/desmarcar como comprado.
	 * @param esComprado Ha sido comprado o no?
	 * @return True si se ha realizado la operación, false si el producto no existe.
	 */
	public Boolean setComprado(String nombreProducto, Boolean esComprado) {
		Boolean retorno = false;
		LineaProducto liProd = getLineaProducto(nombreProducto);
		if (liProd != null) {
			liProd.setEstaComprado(esComprado);
			retorno = true;
		}
		return retorno;
	}

	/**
	 * Elimina un producto de la lista de la compra.
	 * <p>
	 * Si no está en la lista de la compra, no se efectúan cambios y se devuelve false.
	 * Los cambios que se puedan efectuar, solo afectan a la lista de la compra. Si el producto está
	 * marcado como Favorito no eliminar de la lista de favoritos.
	 * 
	 * @param nombreProducto Nombre del producto a eliminar de la lista de la compra.
	 * @return True si se ha eliminado, False en caso contario.
	 */
	public Boolean eliminarProducto(String nombreProducto) {
		Boolean retorno = listaCompra.remove(nombreProducto) != null;
		if (retorno && !getProducto(nombreProducto).isFavorito())
			this.listaProductos.remove(nombreProducto);
		return retorno;
	}


	/**
	 * Añade o marca un producto como favorito.
	 * <p>
	 * Se añade un producto sin almacenarlo en la lista de la compra, o se marca un producto existente como
	 * favorito.
	 * 
	 * @param nombreProducto Nombre del producto a marcar como favorito.
	 */
	public void anadirFavorito(String nombreProducto) {
		Producto producto = listaProductos.getOrDefault(nombreProducto, new Producto(nombreProducto, false));
		producto.setEsFavorito(true);
		listaProductos.put(nombreProducto, producto);
	}
	
	/**
	 * Elimina o desmarca un producto como favorito.
	 * <p>
	 * Si el producto no es favorito no se efectúan cambios y se devuelve false.
	 * 
	 * @param nombreProducto Nombre del producto favorito a quitar.
	 * @return True si se ha eliminado como favorito, false en caso contrario.
	 */
	public Boolean eliminarFavorito(String nombreProducto) {
		Producto producto = getProducto(nombreProducto);
		Boolean retorno = producto != null && producto.isFavorito();
		if (retorno)
			if (listaCompra.containsKey(nombreProducto))
				producto.setEsFavorito(false);
			else
				listaProductos.remove(nombreProducto);
		return retorno;
	}

	/**
	 * Limpia la lista de la compra, sin eliminar los favoritos.
	 */
	public void limpiarListaCompra () {
		for (String producto : getListaCompra().keySet()) {
			if (!listaProductos.get(producto).isFavorito())
				listaProductos.remove(producto);
			listaCompra.remove(producto);
		}
	}

	
	/**
	 * Limpia la lista de favoritos.
	 */
	public void limpiarFavoritos () {
		HashSet<String> favoritos = getFavoritos();
		for (String fav : favoritos)
			if (listaCompra.containsKey(fav))
				getProducto(fav).setEsFavorito(false);
			else
				listaProductos.remove(fav);
	}

	/**
	 * Vacía tanto la lista de la compra como la de favoritos.
	 */
	public void clearAll() {
		this.limpiarListaCompra();
		this.limpiarFavoritos();
	}
}
