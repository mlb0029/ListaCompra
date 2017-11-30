package modelo.datos;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Almacena los datos de lista de la compra &lt;&lt;Singleton&gt;&gt;.
 * <p>
 * Almacena la lista de la compra y la lista de favoritos.
 * 
 * @author MIGUEL ANGEL LEON BARDAVIO
 */
/**
 * @author Miguel Ángel León
 *
 */
/**
 * @author Miguel Ángel León
 *
 */
public class ListaCompra implements IListaCompra{
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
	
	/**
	 * Elimina la única instancia de la clase.
	 */
	public static void delInstance() {
		instance = null;
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
	
	/* (non-Javadoc)
	 * @see modelo.datos.IListaCompra#getListaCompra()
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

	/* (non-Javadoc)
	 * @see modelo.datos.IListaCompra#getFavoritos()
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
	
	/* (non-Javadoc)
	 * @see modelo.datos.IListaCompra#añadirProducto(java.lang.String, java.lang.Integer)
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


	/* (non-Javadoc)
	 * @see modelo.datos.IListaCompra#modificarCantidad(java.lang.String, java.lang.Integer)
	 */
	@Override
	public Boolean modificarCantidad(String nombreProducto, Integer cantidad) {
		Boolean retorno = false;
		LineaProducto liProd = getLineaProducto(nombreProducto);
		if (liProd != null) {
			retorno = liProd.setCantidad(cantidad);
		}
		return retorno;
	}

	/* (non-Javadoc)
	 * @see modelo.datos.IListaCompra#marcarComprado(java.lang.String)
	 */
	@Override
	public Boolean marcarComprado(String nombreProducto) {
		Boolean retorno = false;
		LineaProducto liProd = getLineaProducto(nombreProducto);
		if (liProd != null) {
			liProd.marcarComprado();;
			retorno = true;
		}
		return retorno;
	}

	/* (non-Javadoc)
	 * @see modelo.datos.IListaCompra#desmarcarComprado(java.lang.String)
	 */
	@Override
	public Boolean desmarcarComprado(String nombreProducto) {
		Boolean retorno = false;
		LineaProducto liProd = getLineaProducto(nombreProducto);
		if (liProd != null) {
			liProd.desmarcarComprado();
			retorno = true;
		}
		return retorno;
	}

	/* (non-Javadoc)
	 * @see modelo.datos.IListaCompra#eliminarProducto(java.lang.String)
	 */
	public Boolean eliminarProducto(String nombreProducto) {
		Boolean retorno = listaCompra.remove(nombreProducto) != null;
		if (retorno && !getProducto(nombreProducto).isFavorito())
			this.listaProductos.remove(nombreProducto);
		return retorno;
	}


	/* (non-Javadoc)
	 * @see modelo.datos.IListaCompra#añadirFavorito(java.lang.String)
	 */
	public void añadirFavorito(String nombreProducto) {
		Producto producto = listaProductos.getOrDefault(nombreProducto, new Producto(nombreProducto, false));
		producto.marcarFavorito();
		listaProductos.put(nombreProducto, producto);
	}
	
	/* (non-Javadoc)
	 * @see modelo.datos.IListaCompra#eliminarFavorito(java.lang.String)
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

	/* (non-Javadoc)
	 * @see modelo.datos.IListaCompra#limpiarListaCompra()
	 */
	public void limpiarListaCompra () {
		for (String producto : getListaCompra().keySet()) {
			if (!listaProductos.get(producto).isFavorito())
				listaProductos.remove(producto);
			listaCompra.remove(producto);
		}
	}

	
	/* (non-Javadoc)
	 * @see modelo.datos.IListaCompra#limpiarFavoritos()
	 */
	public void limpiarFavoritos () {
		HashSet<String> favoritos = getFavoritos();
		for (String fav : favoritos)
			if (listaCompra.containsKey(fav))
				getProducto(fav).desmarcarFavorito();
			else
				listaProductos.remove(fav);
	} 
}
