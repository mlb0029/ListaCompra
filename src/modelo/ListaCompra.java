package modelo;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Almacena los datos de lista de la compra &lt;&lt;Singleton&gt;&gt;.
 * <p>
 * Almacena la lista de la compra y la lista de favoritos.
 * 
 * @author MIGUEL ANGEL LEON BARDAVIO
 */
public class ListaCompra {
	
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
	
	/**
	 * Elimina la única instancia de la clase.
	 */
	public static void delInstance() {
		instance = null;
	}

	/**
	 * Devuelve el número de productos que contiene la lista de la compra.
	 * 
	 * @return Número de productos que contiene la lista de la compra.
	 */
	public Integer size() {
		return listaCompra.size();
	}

	/**
	 * Devuelve la lista de la compra.
	 * <p>
	 * Devuelve un HashMap&lt;String, LineaProducto&gt;.
	 * 
	 * @return Collección que contiene la lista de la compra.
	 */
	public HashMap<String, LineaProducto> getListaCompra() {
		return new HashMap<String, LineaProducto>(listaCompra);
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
	 * Devuelve el número de productos que contiene la lista de la compra más los que están almacenados como
	 * favoritos y no están en la lista de la compra.
	 * 
	 * @return Número total de productos.
	 */
	public Integer numProductos() {
		return listaProductos.size();
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
	 * Devuelve un producto dado su nombre.
	 * 
	 * @param nombreProducto Nombre del producto del cual se desea obtener información.
	 * @return Producto o null si el producto no existe.
	 */
	public Producto getProducto(String nombreProducto) {
		return listaProductos.getOrDefault(nombreProducto, null);
	}
	/**
	 * Añade un producto a la lista de la compra.
	 * <p>
	 * Si el producto no existe, lo añade a la lista de productos como no favorito. Si ya había un producto con
	 * mismo nombre en la lista de la compra, devuelve false y la función no realiza ningún cambio.
	 * 
	 * @param nombreProducto Nombre del producto a comprar.
	 * @param cantidad Cantidad del producto a comprar
	 * @return False si el producto ya existía por tanto no ha tenido efecto la llamada a la funcion, True en caso contrario.
	 */
	public Boolean añadirProducto(String nombreProducto, Integer cantidad) {
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
	 * Elimina, si existe, un producto de la lista de la compra.
	 * <p>
	 * Si el producto no está marcado coo favorito, se elimina de la lista de productos.
	 * 
	 * @param nombreProducto Nombre del producto a eliminar.
	 * @return true si ha sido eliminado algún producto de la lista de la compra y false en caso contrario.
	 */
	public Boolean eliminarProducto(String nombreProducto) {
		Boolean retorno = listaCompra.remove(nombreProducto) != null;
		if (retorno && !getProducto(nombreProducto).isFavorito())
			this.listaProductos.remove(nombreProducto);
		return retorno;
	}


	/**
	 * Devuelve el número de productos que se almacenan como favoritos.
	 * 
	 * @return Número de productos que se almacenan como favoritos.
	 */
	public Integer numFavoritos() {
		return getFavoritos().size();
	}

	/**
	 * Devuelve los nombres de los productos que se han almacenado como favoritos.
	 * 
	 * @return Set con los nombres de los productos que se han almacenado como favoritos.
	 */
	public HashSet<String> getFavoritos(){
		HashSet<String> retorno = new HashSet<String>();
		for (Producto producto : listaProductos.values())
			if (producto.isFavorito())
				retorno.add(producto.getNombre());
		return retorno;
	}

	/**
	 * Añade o marca un producto como favorito, sin afectar a la lista de la compra.
	 * 
	 * @param nombreProducto Nombre del producto.
	 */
	public void añadirFavorito(String nombreProducto) {
		Producto producto = listaProductos.getOrDefault(nombreProducto, new Producto(nombreProducto, false));
		producto.marcarFavorito();
		listaProductos.put(nombreProducto, producto);
	}
	
	/**
	 * Elimina o desmarca un producto como favorito, sin afectar a la lista de la compra.
	 * <p>
	 * Si el producto no existe devuelve false.
	 * 
	 * @param nombreProducto Nombre del producto desmarcar como favorito.
	 * @return false si el producto no existe o true en caso contrario
	 */
	public Boolean eliminarFavorito(String nombreProducto) {
		Producto producto = getProducto(nombreProducto);
		Boolean retorno = producto != null && producto.isFavorito();
		if (retorno)
			if (listaCompra.containsKey(nombreProducto))
				producto.desmarcarFavorito();
			else
				listaProductos.remove(nombreProducto);
		return retorno;
	}

	/**
	 * Limpia la lista de favoritos.
	 */
	public void limpiarFavoritos () {
		HashSet<String> favoritos = getFavoritos();
		for (String fav : favoritos)
			if (listaCompra.containsKey(fav))
				getProducto(fav).desmarcarFavorito();
			else
				listaProductos.remove(fav);
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
}
