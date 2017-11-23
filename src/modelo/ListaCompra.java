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
	 * Devuelve la �nica instancia de la clase e instancia la clase si no hay instancia.
	 * 
	 * @return La �nica instancia de la clase
	 */
	public static ListaCompra getInstance() {
		if (instance == null)
			instance = new ListaCompra();
		return instance;
	}

	/**
	 * Devuelve la lista de la compra.
	 * 
	 * @return Collecci�n que contiene la lista de la compra.
	 */
	public HashMap<String, LineaProducto> getListaCompra() {
		return listaCompra;
	}

	/**
	 * A�ade un producto a la lista de la compra.
	 * <p>
	 * Si el producto existe como favorito se a�ade ese producto, en caso contrario se crea un producto 
	 * no favorito y se a�ade a la lista. En ambos casos se les asigna la cantidad a comprar pasada como argumento.
	 * 
	 * @param nombreProducto Nombre del producto a comprar.
	 * @param cantidad Cantidad del producto a comprar
	 */
	public void a�adirProducto(String nombreProducto, Integer cantidad) {
		Producto producto = listaProductos.getOrDefault(nombreProducto, new Producto(nombreProducto, false));
		LineaProducto linea = new LineaProducto(producto, cantidad);
		
		listaProductos.put(nombreProducto, producto);
		listaCompra.put(nombreProducto, linea);
	}


	/**
	 * Elimina, si existe, un producto de la lista de la compra.
	 * 
	 * @param nombreProducto Nombre del producto a eliminar.
	 * @return true si ha sido eliminado alg�n producto de la lista de la compra y false en caso contrario.
	 */
	public Boolean eliminarProducto(String nombreProducto) {
			return listaCompra.remove(nombreProducto) != null;
	}


	/**
	 * Devuelve los productos que se han almacenado como favoritos.
	 * 
	 * @return Set con los productos que se han almacenado como favoritos.
	 */
	public HashSet<String> getFavoritos(){
		HashSet<String> retorno = new HashSet<String>();
		for (Producto producto : listaProductos.values())
			if (producto.isFavorito())
				retorno.add(producto.getNombre());
		return retorno;
	}


	/**
	 * A�ade o marca un producto como favorito, sin afectar a la lista de la compra.
	 * 
	 * @param nombreProducto Nombre del producto.
	 */
	public void a�adirFavorito(String nombreProducto) {
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
		Boolean retorno = false;
		Producto producto = listaProductos.get(nombreProducto);
		if (producto != null) {
			if (listaCompra.containsKey(nombreProducto))
				producto.desmarcarFavorito();
			else
				listaProductos.remove(nombreProducto);
			retorno = true;
		}
		return retorno;
	}

	/**
	 * Limpia la lista de favoritos.
	 * 
	 * @return false si no hab�a favoritos, true en caso contrario.
	 */
	public Boolean limpiarFavoritos () {
		Boolean retorno = false;
		for (Producto producto : listaProductos.values()) {
			if (producto.isFavorito()) {
				if (listaCompra.containsKey(producto.getNombre()))
					producto.desmarcarFavorito();
				else
					listaProductos.remove(producto.getNombre());
				retorno = true;
			}
		}
		return retorno;
	} 
}
