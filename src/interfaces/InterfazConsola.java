package interfaces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import modelo.LineaProducto;
import persistencia.IPersistencia;
import persistencia.PersistenciaLista;
import modelo.ListaCompra;

public class InterfazConsola {
	public static final String fileListaCompra = "listaCompra.txt";
	public ListaCompra listaCompra = ListaCompra.getInstance();
	public LineaProducto productos = null;

	public void muestraMenu() {
		System.out.println("--------------------------------------------------------------");
		System.out.println("Elige una opcion:\n");
		muestraOpciones();
		System.out.println("--------------------------------------------------------------");

	}

	
	public void muestraEnunciado() {
		System.out.println("1-  Añadir producto");// ++
		System.out.println("2-  Eliminar producto");// ++
		System.out.println("3-  Modificar cantidad de producto");//
		System.out.println("4-  Marcar como producto favorito");// ++
		System.out.println("5-  Desmarcar como producto favorito");// ++
		System.out.println("6-  Marcar como producto comprado");// ++
		System.out.println("7-  Mostrar productos favoritos");// ++
		System.out.println("8-  Mostrar lista de la compra");// ++
		System.out.println("9-  Borrar todos los favoritos");
		System.out.println("10- Guardar datos");// ++
		System.out.println("11- Cargar datos");// ++
		System.out.println("0- Salir\n");


	}
	public void muestraOpciones() {
		muestraEnunciado();
		eligeOpcion();

	}

	public void eligeOpcion() {
		IPersistencia<LineaProducto, String> persistenciaLista = new PersistenciaLista(fileListaCompra);
		HashMap<String, String> favoritos = ListaCompra.getFavoritos();
		listaCompra = persistenciaLista.cargarCont();
		favoritos = ListaCompra.getFavoritos();
		
		Scanner teclado = new Scanner(System.in);

		int numero = -1;
		try {
			while (numero != 0) {
				numero = teclado.nextInt();
				String producto;

				switch (numero) {
				case 1:
					System.out.println("Nombre del nuevo producto:");
					producto = teclado.next();
					System.out.println("Cantidad:");
					String cantidad = teclado.next();
					Integer val = Integer.parseInt(cantidad);
					listaCompra.añadirProducto(producto, val);
					// persistenciaLista.guardarContenido(listaCompra);
					break;
				case 2:
					System.out.println("Elige producto");
					producto = teclado.next();
					listaCompra.eliminarProducto(producto);
					break;

				case 3:
					System.out.println("Elige producto");
					producto = teclado.next();
					System.out.println("Elige Cantidad");
					String cantidad1 = teclado.next();
					Integer val1 = Integer.parseInt(cantidad1);
					listaCompra.modificarCantidad(producto,Integer.parseInt(cantidad1));
					break;

				case 4:
					System.out.println("Elige producto");
					producto = teclado.next();
					listaCompra.añadirFavorito(producto);
					favoritos = ListaCompra.getFavoritos();
					break;

				case 5:
					System.out.println("Elige producto");
					producto = teclado.next();
					listaCompra.eliminarFavorito(producto);
					favoritos = new HashMap<String, String>();
					System.out.println(favoritos.toString());
					break;

				case 6:
					System.out.println("Elige producto");
					producto = teclado.next();
					listaCompra.marcarComprado(producto);
					break;

				case 7:
					System.out.println(favoritos.toString());
					break;
				case 8:
					System.out.println("Los productos de su lista de la compra son :\n");
					HashMap<String, LineaProducto> listaCompra2 = listaCompra.getListaCompra();
					for (LineaProducto linea : listaCompra2.values()) {
						System.out.println(linea);
					}
					break;

				case 9:
					listaCompra.limpiarFavoritos();
					favoritos = new HashMap<String, String>();
					System.out.println(favoritos.toString());
					break;

				case 10:
					System.out.println("Se estan guardando los datos" + favoritos);

					persistenciaLista.guardarContenido(listaCompra.getListaCompra().values());
					System.out.println(listaCompra);
					break;

				case 11:
					System.out.println("Cargando datos" + favoritos);
					listaCompra = persistenciaLista.cargarCont();
					favoritos = ListaCompra.getFavoritos();
					System.out.println(listaCompra);
					break;

				case 0:
					return;
				default:

					muestraMenu();
				}
				muestraEnunciado();
			}

		} catch (Exception ex) {
			System.out.println("No lee el numero");
			ex.printStackTrace();
		} finally {
			teclado.close();
		}

	}
}
