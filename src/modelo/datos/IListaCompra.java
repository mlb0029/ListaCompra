package modelo.datos;

import java.util.Collection;
import java.util.Map;

import modelo.datos.LineaProducto;

/**
 * Provee de los métodos que necesita las interfaces para interactuar con las estructuras de datos.
 * 
 * @author MIGUEL ANGEL LEON BARDAVIO
 *
 */
public interface IListaCompra {

	/**
	 * Añade un producto a la lista de la compra.
	 * <p>
	 * Inicialmente no favorito, para almacenarlo como favorito usar "añadirFavorito(String)".
	 * Se lleva la lista de la compra y la de favoritos independientemente.
	 * Si el producto está en la lista de la compra, no se efectúan cambios y se devuelve false.
	 * 
	 * @param nombreProducto Nombre del producto a comprar.
	 * @param cantidad Cantidad del producto a comprar.
	 * @return True si se ha añadido, False en caso contario.
	 * @see IListaCompra#añadirFavorito(String)
	 */
	public Boolean añadirProducto(String nombreProducto, Integer cantidad);
	
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
	public Boolean eliminarProducto(String nombreProducto);
	
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
	public Boolean modificarCantidad(String nombreProducto, Integer cantidad);
	
	/**
	 * Añade o marca un producto como favorito.
	 * <p>
	 * Se añade un producto sin almacenarlo en la lista de la compra, o se marca un producto existente como
	 * favorito.
	 * 
	 * @param nombreProducto Nombre del producto a marcar como favorito.
	 */
	public void añadirFavorito(String nombreProducto);
	
	/**
	 * Elimina o desmarca un producto como favorito.
	 * <p>
	 * Si el producto no es favorito no se efectúan cambios y se devuelve false.
	 * 
	 * @param nombreProducto Nombre del producto favorito a quitar.
	 * @return True si se ha eliminado como favorito, false en caso contrario.
	 */
	public Boolean eliminarFavorito(String nombreProducto);
	
	/**
	 * Marca un producto como comprado.
	 * 
	 * @param nombreProducto Nombre del producto a marcar como comprado.
	 * @return True si se ha hecho correctamente, false si el producto no existe.
	 */
	public Boolean marcarComprado(String nombreProducto);
	
	/**
	 * Desmarca un producto como comprado.
	 * 
	 * @param nombreProducto Nombre del producto a desmarcar como comprado.
	 * @return True si se ha hecho correctamente, false si el producto no existe.
	 */
	public Boolean desmarcarComprado(String nombreProducto);
	
	
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
	public Map<String, LineaProducto> getListaCompra();
	
	
	/**
	 * Devuelve una Collección que contiene los nombres de los productos favoritos.
	 * 
	 * @return Productos favoritos.
	 */
	public Collection<String> getFavoritos();
	
	/**
	 * Limpia la lista de la compra, sin eliminar los favoritos.
	 */
	public void limpiarListaCompra ();

	/**
	 * Limpia la lista de favoritos.
	 */
	public void limpiarFavoritos ();
}