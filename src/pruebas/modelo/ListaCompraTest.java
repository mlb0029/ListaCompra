/**
 * 
 */
package pruebas.modelo;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import modelo.datos.*;

/**
 * @author migue
 *
 */
public class ListaCompraTest {

	ListaCompra listaCompra;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.listaCompra = ListaCompra.getInstance();
		this.listaCompra.añadirProducto("Lechugas", 2);
		this.listaCompra.añadirProducto("Tomates", 6);
		this.listaCompra.añadirProducto("Bote de pepinillos", 1);
		this.listaCompra.añadirProducto("Manzanas", 8);
		this.listaCompra.añadirProducto("Mandarinas", 10);
		this.listaCompra.añadirFavorito("Cecina");
		this.listaCompra.añadirFavorito("Natillas");
		this.listaCompra.añadirFavorito("Manzanas");
		this.listaCompra.añadirFavorito("Mandarinas");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.listaCompra = null;
		ListaCompra.delInstance();
	}

	/**
	 * Test method for {@link modelo.ListaCompra#getInstance()}.
	 */
	@Test
	public final void testGetInstance() {
		assertSame(this.listaCompra, ListaCompra.getInstance());
		this.listaCompra = null;
		assertNotNull(ListaCompra.getInstance());
	}

	/**
	 * Test method for {@link modelo.ListaCompra#getListaCompra()}.
	 */
	@Test
	public final void testGetListaCompra() {
		HashMap<String, LineaProducto> liCo = this.listaCompra.getListaCompra();
		assertNotNull(liCo);
		assertEquals(5, liCo.size());
		assertTrue(liCo.containsKey("Manzanas"));
	}

	/**
	 * Test method for {@link modelo.ListaCompra#size()}.
	 */
	@Test
	public final void testSize() {
		assertEquals(5, (int)this.listaCompra.size());
	}

	/**
	 * Test method for {@link modelo.ListaCompra#numProductos()}.
	 */
	@Test
	public final void testNumProductos() {
		assertEquals(7, (int)this.listaCompra.numProductos());
	}

	/**
	 * Test method for {@link modelo.ListaCompra#getListaProductos()}.
	 */
	@Test
	public final void testGetListaProductos(){
		HashMap<String, Producto> liCo = this.listaCompra.getListaProductos();
		assertNotNull(liCo);
		assertEquals(7, liCo.size());
		assertTrue(liCo.containsKey("Manzanas"));
		assertTrue(liCo.containsKey("Cecina"));
	}
	
	/**
	 * Test method for {@link modelo.ListaCompra#getProducto(java.lang.String)}.
	 */
	@Test
	public final void testGetProducto() {
		Producto producto = this.listaCompra.getProducto("Cecina");
		assertNotNull(producto);
		assertTrue(producto.isFavorito());
		producto = this.listaCompra.getProducto("Fregona");
		assertNull(producto);
	}

	/**
	 * Test method for {@link modelo.ListaCompra#añadirProducto(java.lang.String, java.lang.Integer)}.
	 */
	@Test
	public final void testañadirProducto() {
		//Caso de fallo
		assertFalse(this.listaCompra.añadirProducto("Lechugas", 5));
		assertEquals(2, (int)this.listaCompra.getLineaProducto("Lechugas").getCantidad());
		assertEquals(7, (int)this.listaCompra.numProductos());
		assertEquals(4, (int)this.listaCompra.numFavoritos());
		assertEquals(5, (int)this.listaCompra.size());
		//Caso de acierto (Meter fav)
		assertTrue(this.listaCompra.añadirProducto("Cecina", 1));
		assertEquals(1, (int)this.listaCompra.getLineaProducto("Cecina").getCantidad());
		assertEquals(7, (int)this.listaCompra.numProductos());
		assertEquals(4, (int)this.listaCompra.numFavoritos());
		assertEquals(6, (int)this.listaCompra.size());
		//Caso de acierto (Meter nuevo)
		assertTrue(this.listaCompra.añadirProducto("Galletas", 2));
		assertEquals(2, (int)this.listaCompra.getLineaProducto("Galletas").getCantidad());
		assertEquals(8, (int)this.listaCompra.numProductos());
		assertEquals(4, (int)this.listaCompra.numFavoritos());
		assertEquals(7, (int)this.listaCompra.size());
	}
	
	/**
	 * Test method for {@link modelo.LineaProducto#getProducto()}.
	 * Test method for {@link modelo.ListaCompra#getProducto(String)}.
	 * Test method for {@link modelo.Producto#getNombre()}.
	 */
	@Test
	public final void gettersProducto() {
		this.listaCompra.añadirProducto("Galletas", 2);
		assertEquals(8, (int)this.listaCompra.numProductos());
		assertEquals(4, (int)this.listaCompra.numFavoritos());
		assertEquals(6, (int)this.listaCompra.size());
		LineaProducto liProd = this.listaCompra.getLineaProducto("Galletas");
		Producto productoA = liProd.getProducto();
		Producto productoB = this.listaCompra.getProducto("Galletas");
		Producto productoC = this.listaCompra.getListaCompra().get("Galletas").getProducto();
		Producto productoD = this.listaCompra.getListaProductos().get("Galletas");
		assertSame(productoA, productoB);
		assertSame(productoA, productoC);
		assertSame(productoA, productoD);
		assertSame(productoB, productoC);
		assertSame(productoB, productoD);
		assertSame(productoC, productoD);
		assertEquals(productoA.getNombre(), productoB.getNombre());
		assertEquals(productoA.getNombre(), productoC.getNombre());
		assertEquals(productoA.getNombre(), productoD.getNombre());
		assertEquals(productoB.getNombre(), productoC.getNombre());
		assertEquals(productoB.getNombre(), productoD.getNombre());
		assertEquals(productoC.getNombre(), productoD.getNombre());
	}
	
	/**
	 * Test method for {@link modelo.Producto#isFavorito()}.
	 */
	@Test
	public final void testProductoIsFavorito() {
		this.listaCompra.añadirProducto("Galletas", 2);
		assertEquals(8, (int)this.listaCompra.numProductos());
		assertEquals(4, (int)this.listaCompra.numFavoritos());
		assertEquals(6, (int)this.listaCompra.size());
		assertFalse(this.listaCompra.getLineaProducto("Galletas").getProducto().isFavorito());
		assertFalse(this.listaCompra.getProducto("Galletas").isFavorito());
		assertFalse(this.listaCompra.getListaCompra().get("Galletas").getProducto().isFavorito());
		assertFalse(this.listaCompra.getListaProductos().get("Galletas").isFavorito());
		this.listaCompra.añadirFavorito("Galletas");
		assertEquals(8, (int)this.listaCompra.numProductos());
		assertEquals(5, (int)this.listaCompra.numFavoritos());
		assertEquals(6, (int)this.listaCompra.size());
		assertTrue(this.listaCompra.getLineaProducto("Galletas").getProducto().isFavorito());
		assertTrue(this.listaCompra.getProducto("Galletas").isFavorito());
		assertTrue(this.listaCompra.getListaCompra().get("Galletas").getProducto().isFavorito());
		assertTrue(this.listaCompra.getListaProductos().get("Galletas").isFavorito());
	}

	/**
	 * Test method for {@link modelo.ListaCompra#eliminarProducto(java.lang.String)}.
	 */
	@Test
	public final void testEliminarProducto() {
		//Caso de fallo: No existe
		assertFalse(this.listaCompra.eliminarProducto("Fregona"));
		assertEquals(7, (int)this.listaCompra.numProductos());
		assertEquals(4, (int)this.listaCompra.numFavoritos());
		assertEquals(5, (int)this.listaCompra.size());
		//Caso de fallo: El producto no est� en la lista pero es almacenado como favorito
		assertFalse(this.listaCompra.eliminarProducto("Cecina"));
		assertEquals(7, (int)this.listaCompra.numProductos());
		assertEquals(4, (int)this.listaCompra.numFavoritos());
		assertEquals(5, (int)this.listaCompra.size());
		//Caso de acierto: El producto es favorito
		assertTrue(this.listaCompra.eliminarProducto("Mandarinas"));
		assertEquals(7, (int)this.listaCompra.numProductos());
		assertEquals(4, (int)this.listaCompra.numFavoritos());
		assertEquals(4, (int)this.listaCompra.size());
		//Caso de acierto: El producto no es favorito
		assertTrue(this.listaCompra.eliminarProducto("Lechugas"));
		assertEquals(6, (int)this.listaCompra.numProductos());
		assertEquals(4, (int)this.listaCompra.numFavoritos());
		assertEquals(3, (int)this.listaCompra.size());
	}

	/**
	 * Test method for {@link modelo.ListaCompra#getFavoritos()}.
	 */
	@Test
	public final void testGetFavoritos() {
		assertEquals(4, this.listaCompra.getFavoritos().size());
		assertEquals((int)this.listaCompra.numFavoritos(), this.listaCompra.getFavoritos().size());
		assertTrue(this.listaCompra.getFavoritos().contains("Cecina"));
		assertTrue(this.listaCompra.getFavoritos().contains("Natillas"));
		assertTrue(this.listaCompra.getFavoritos().contains("Manzanas"));
		assertTrue(this.listaCompra.getFavoritos().contains("Mandarinas"));
		assertFalse(this.listaCompra.getFavoritos().contains("Lechuga"));
	}

	/**
	 * Test method for {@link modelo.ListaCompra#numFavoritos()}.
	 */
	@Test
	public final void testNumFavoritos() {
		assertEquals(4, (int)this.listaCompra.numFavoritos());
	}

	/**
	 * Test method for {@link modelo.ListaCompra#añadirFavorito(java.lang.String)}.
	 */
	@Test
	public final void testañadirFavorito() {
		//Caso de acierto (En lista compra y no favorito)
		assertFalse(this.listaCompra.getProducto("Lechugas").isFavorito());
		this.listaCompra.añadirFavorito("Lechugas");
		assertTrue(this.listaCompra.getProducto("Lechugas").isFavorito());
		assertEquals(5, (int)this.listaCompra.size());
		assertEquals(5, (int)this.listaCompra.numFavoritos());
		assertEquals(7, (int)this.listaCompra.numProductos());
		//Caso de acierto (Nuevo)
		this.listaCompra.añadirFavorito("Macarrones");
		assertTrue(this.listaCompra.getProducto("Macarrones").isFavorito());
		assertEquals(8, (int)this.listaCompra.numProductos());
		assertEquals(6, (int)this.listaCompra.numFavoritos());
		assertEquals(5, (int)this.listaCompra.size());
	}

	/**
	 * Test method for {@link modelo.ListaCompra#eliminarFavorito(java.lang.String)}.
	 */
	@Test
	public final void testEliminarFavorito() {
		//Caso de fallo: No existe
		assertFalse(this.listaCompra.eliminarFavorito("Fregona"));
		assertEquals(7, (int)this.listaCompra.numProductos());
		assertEquals(4, (int)this.listaCompra.numFavoritos());
		assertEquals(5, (int)this.listaCompra.size());
		//Caso de fallo: Est� en la lista de la compra pero no es favorito
		assertFalse(this.listaCompra.eliminarFavorito("Lechugas"));
		assertEquals(7, (int)this.listaCompra.numProductos());
		assertEquals(4, (int)this.listaCompra.numFavoritos());
		assertEquals(5, (int)this.listaCompra.size());
		//Caso de acierto: Est� en la lista de la compra y es favorito
		assertTrue(this.listaCompra.eliminarFavorito("Manzanas"));
		assertEquals(7, (int)this.listaCompra.numProductos());
		assertEquals(3, (int)this.listaCompra.numFavoritos());
		assertEquals(5, (int)this.listaCompra.size());
		//Caso de acierto: No est� en la lista de la compra y es favorito
		assertTrue(this.listaCompra.eliminarFavorito("Cecina"));
		assertEquals(6, (int)this.listaCompra.numProductos());
		assertEquals(2, (int)this.listaCompra.numFavoritos());
		assertEquals(5, (int)this.listaCompra.size());
	}

	/**
	 * Test method for {@link modelo.ListaCompra#limpiarFavoritos()}.
	 */
	@Test
	public final void testLimpiarFavoritos() {
		this.listaCompra.limpiarFavoritos();
		assertEquals(0, (int)this.listaCompra.numFavoritos());
		assertEquals(0, (int)this.listaCompra.getFavoritos().size());
	}
	
	/**
	 * Test method for {@link modelo.ListaCompra#limpiarListaCompra()}.
	 */
	@Test
	public final void testlimpiarListaCompra() {
		this.listaCompra.limpiarListaCompra();
		assertEquals(4, (int)this.listaCompra.numProductos());
		assertEquals(4, (int)this.listaCompra.numFavoritos());
		assertEquals(0, (int)this.listaCompra.size());
		assertEquals(0, (int)this.listaCompra.getListaCompra().size());
		assertEquals(4, (int)this.listaCompra.getFavoritos().size());
		assertEquals(4, (int)this.listaCompra.getListaProductos().size());
	}
	
	
	/**
	 * Test method for {@link modelo.LineaProducto#setCantidad(Integer)}.
	 */
	@Test
	public final void testModificarCantidad() {
		LineaProducto linea = this.listaCompra.getLineaProducto("Lechugas");
		assertEquals(2, (int)linea.getCantidad());
		assertEquals(2, (int)this.listaCompra.getLineaProducto("Lechugas").getCantidad());
		//Caso de acierto: n� positivo
		listaCompra.modificarCantidad("Lechugas", 5);
		assertEquals(5, (int)linea.getCantidad());
		assertEquals(5, (int)this.listaCompra.getLineaProducto("Lechugas").getCantidad());
		//Caso de fallo: n� negativo
		assertFalse(listaCompra.modificarCantidad("Lechugas", -6));
		assertEquals(5, (int)this.listaCompra.getLineaProducto("Lechugas").getCantidad());
		//Caso de fallo: 0
		assertFalse(listaCompra.modificarCantidad("Lechugas", 0));
		assertEquals(5, (int)this.listaCompra.getLineaProducto("Lechugas").getCantidad());
	}
	
	/**
	 * Test method for {@link modelo.LineaProducto#marcarComprado()}.
	 */
	@Test
	public final void marcarComprado() {
		LineaProducto linea = this.listaCompra.getLineaProducto("Lechugas");
		assertFalse(linea.getEstaComprado());
		assertFalse(this.listaCompra.getLineaProducto("Lechugas").getEstaComprado());
		listaCompra.marcarComprado("Lechugas");
		assertTrue(linea.getEstaComprado());
		assertTrue(this.listaCompra.getLineaProducto("Lechugas").getEstaComprado());
	}
	
	/**
	 * Test method for {@link modelo.LineaProducto#desmarcarComprado()}.
	 */
	@Test
	public final void desMarcarComprado() {
		LineaProducto linea = this.listaCompra.getLineaProducto("Lechugas");
		assertFalse(linea.getEstaComprado());
		assertFalse(this.listaCompra.getLineaProducto("Lechugas").getEstaComprado());
		listaCompra.marcarComprado("Lechugas");
		assertTrue(linea.getEstaComprado());
		assertTrue(this.listaCompra.getLineaProducto("Lechugas").getEstaComprado());
		listaCompra.desmarcarComprado("Lechugas");
		assertFalse(linea.getEstaComprado());
		assertFalse(this.listaCompra.getLineaProducto("Lechugas").getEstaComprado());
	}

}
