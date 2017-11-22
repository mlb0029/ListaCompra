package modelo;

import java.util.HashMap;

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
	 * Almacena la lista de la compra.
	 */
	HashMap<String, LineaProducto> listaCompra;
	
	/**
	 * Instancia la clase con las listas vacias.
	 */
	public ListaCompra() {
		this.listaCompra = new HashMap<String, LineaProducto>();
	}
	
	public boolean añadirProducto(String producto, Integer cantidad, Boolean esFavorito) {
		LineaProducto item = this.listaCompra.put(producto, new LineaProducto(producto, cantidad, esFavorito));
		if (item.equals(null))
			return false;
		return true;
		//TODO Comprobar errores
	}
	
	public boolean eliminarProducto(String producto) {
		return this.listaCompra.remove(producto) != null;
		//TODO Test para probar que funciona
	}
	
	public void vaciarListaCompra() {
		this.listaCompra.clear();
	}
	
	public LineaProducto getLinea(String producto) {
		return this.listaCompra[producto];
	}
}
