package modelo;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Almacena los datos de lista de la compra.
 * <p>
 * Almacena la lista de la compra y la lista de favoritos.
 * 
 * @author MIGUEL ANGEL LEON BARDAVIO
 *
 */
public class ListaCompra {
	
	
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
	public ListaCompra() {
		this.listaCompra = new HashMap<String, LineaProducto>();
		this.listaProductos = new HashMap<String, Producto>();
	}
	
	public Boolean crearProducto(Producto producto) {
		if (!listaProductos.containsKey(producto.getNombre())) {
			listaProductos.put(producto.getNombre(), producto);
			return true;
		}
		return false;
	}

	public Boolean crearProducto(String nombre, Boolean esFavorito) {
		Producto producto = new Producto(nombre, esFavorito);
		return crearProducto(producto);
	}
	
	public Producto getProducto(String nombre) {
		return listaProductos.get(nombre);
	}
	
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
	
	public HashSet<Producto> getFavoritos(){
		HashSet<Producto> retorno = new HashSet<Producto>();
		for (Producto producto : listaProductos.values())
			if (producto.isFavorito())
				retorno.add(producto);
		return retorno;
	}
	
	public Boolean añadirProducto(String nombre, Boolean esFavorito, Integer cantidad) {
		Producto producto = new Producto(nombre, esFavorito);
		if (crearProducto(producto)) {
			LineaProducto linea = new LineaProducto(producto, cantidad);
			listaCompra.put(nombre, linea);
			return true;
		}
		return false;
	}
	
	public Boolean añadirProducto(Producto producto, Integer cantidad) {
		if (crearProducto(producto)) {
			LineaProducto linea = new LineaProducto(producto, cantidad);
			listaCompra.put(producto.getNombre(), linea);
			return true;
		}
		return false;
	}
	
	public Boolean eliminarProducto() {
		// TODO eliminarProducto()
		return false;
	}
	
	
}
