package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

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
	private static HashMap<String, LineaProducto> listaCompra;

	/**
	 * Instancia la clase con las listas vacias.
	 */
	private ListaCompra() {
		this.listaCompra = new HashMap<String, LineaProducto>();
		this.listaProductos = new HashMap<String, Producto>();
	}

	/**
	 * Devuelve la única instancia de la clase e instancia la clase si no hay
	 * instancia.
	 * 
	 * @return La única instancia de la clase
	 */
	public static ListaCompra getInstance() {
		if (instance == null)
			instance = new ListaCompra();
		return instance;
	}

	public static HashMap<String, String> getFavoritos() {
		HashMap<String, String> favo = new HashMap<String, String>();
		for (LineaProducto p : listaCompra.values()) {
			favo.put(p.getProducto().getNombre(), p.getProducto().getNombre());
		}
		return favo;

	}

	/**
	 * Devuelve la lista de la compra.
	 * 
	 * @return Collección que contiene la lista de la compra.
	 */
	public HashMap<String, LineaProducto> getListaCompra() {
		return listaCompra;
	}

	/**
	 * Devuelve un producto dado su nombre.
	 * 
	 * @param nombreProducto
	 *            Nombre del producto del cual se desea obtener información.
	 * @return Producto o null si el producto no existe.
	 */
	public Producto getProducto(String nombreProducto) {
		return listaProductos.getOrDefault(nombreProducto, null);
	}

	/**
	 * Añade un producto a la lista de la compra.
	 * <p>
	 * Si el producto no existe, lo añade a la lista de productos como no
	 * favorito. Si ya había un producto con mismo nombre en la lista de la
	 * compra, devuelve false y la función no realiza ningún cambio.
	 * 
	 * @param nombreProducto
	 *            Nombre del producto a comprar.
	 * @param cantidad
	 *            Cantidad del producto a comprar
	 * @return False si el producto ya existía por tanto no ha tenido efecto la
	 *         llamada a la funcion, True en caso contrario.
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
		} else if (!listaCompra.containsKey(nombreProducto)) {
			LineaProducto lineaProducto = new LineaProducto(producto, cantidad);
			listaCompra.put(nombreProducto, lineaProducto);
			retorno = true;
		}
		return retorno;
	}

	/**
	 * Elimina, si existe, un producto de la lista de la compra.
	 * <p>
	 * Si el producto no está marcado coo favorito, se elimina de la lista de
	 * productos.
	 * 
	 * @param nombreProducto
	 *            Nombre del producto a eliminar.
	 * @return true si ha sido eliminado algún producto de la lista de la compra
	 *         y false en caso contrario.
	 */
	public Boolean eliminarProducto(String nombreProducto) {
		Boolean retorno = listaCompra.remove(nombreProducto) != null;
		if (retorno && !getProducto(nombreProducto).isFavorito())
			this.listaProductos.remove(nombreProducto);
		return retorno;
	}

	/**
	 * Devuelve los productos que se han almacenado como favoritos.
	 * 
	 * @return Set con los productos que se han almacenado como favoritos.
	 */
	// public HashSet<String> getFavoritos() {
	// HashSet<String> retorno = new HashSet<String>();
	// for (Producto producto : listaProductos.values())
	// if (producto.isFavorito())
	// retorno.add(producto.getNombre());
	// return retorno;
	// }

	/**
	 * Añade o marca un producto como favorito, sin afectar a la lista de la
	 * compra.
	 * 
	 * @param nombreProducto
	 *            Nombre del producto.
	 */
	public void añadirFavorito(String nombreProducto) {
		Producto producto = listaProductos.getOrDefault(nombreProducto, new Producto(nombreProducto, false));
		producto.marcarFavorito();
		listaProductos.put(nombreProducto, producto);
	}

	/**
	 * Elimina o desmarca un producto como favorito, sin afectar a la lista de
	 * la compra.
	 * <p>
	 * Si el producto no existe devuelve false.
	 * 
	 * @param nombreProducto
	 *            Nombre del producto desmarcar como favorito.
	 * @return false si el producto no existe o true en caso contrario
	 */
	public Boolean eliminarFavorito(String nombreProducto) {
		LineaProducto prodTemp = listaCompra.get(nombreProducto);
		Producto producto = getProducto(nombreProducto);
		Boolean retorno = producto != null;
		if (retorno)
			if (listaCompra.containsKey(nombreProducto)) {
				prodTemp.getProducto().desmarcarFavorito();
				listaCompra.put(prodTemp.getProducto().getNombre(), prodTemp);
			} else
				listaProductos.remove(nombreProducto);
		return retorno;
	}

	public Boolean marcarComprado(String producto) {
		boolean var = false;
		LineaProducto prodTemp = listaCompra.get(producto);
		if (prodTemp != null) {
			if (!prodTemp.getEstaComprado()) {
				prodTemp.marcarComprado();
				listaCompra.put(prodTemp.getProducto().getNombre(), prodTemp);
				var = true;
			}
		}
		return var;
	}
	public Boolean modificarCantidad(String producto,int cantidad) {
		boolean var = false;
		LineaProducto prodTemp = listaCompra.get(producto);
		if (prodTemp != null) {
			if (!prodTemp.getEstaComprado()) {
				prodTemp.setCantidad(cantidad);
				listaCompra.put(prodTemp.getProducto().getNombre(), prodTemp);
				var = true;
			}
		}
		return var;
	}
	/**
	 * Limpia la lista de favoritos.
	 */
	public void limpiarFavoritos() {
		for (Producto producto : listaProductos.values())
			if (producto.isFavorito())
				if (listaCompra.containsKey(producto.getNombre()))
					producto.desmarcarFavorito();
				else
					listaProductos.remove(producto.getNombre());
	}
}
