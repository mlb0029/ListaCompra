package interfaces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import modelo.datos.*;
import modelo.persistencia.*;

/**
 * Interfaz de usuario: modo texto.
 * 
 * @author CLARA PALACIOS RODRIGO
 *
 */
public class InterfazConsola {

	IListaCompra listaCompra;

	IPersistencia persistencia;

	public InterfazConsola() {
		listaCompra = ListaCompra.getInstance();
		persistencia = new PersistenciaListaCompra();
		try {
			listaCompra = persistencia.cargarCont();
		} catch (Exception e) {
			System.out.println("Error al cargar datos");
		}

	}

	public Integer pedirNumero(Scanner teclado) {
		Integer numero = 1;
		System.out.println("Cantidad:");
		numero = Integer.parseInt(teclado.next());
		return numero;
	}

	public void muestraListaCompra(HashMap<String, LineaProducto> liCo) {
		if (liCo.size() > 0) {
			System.out.println("Los productos de su lista de la compra son :\n");
			for (String prod : liCo.keySet()) {
				System.out.println(liCo.get(prod));
			}
		} else {
			System.out.println("Lista de la compra vacía");
		}
	}

	public void muestraListaFavoritos(List<String> liFav) {
		if (liFav.size() > 0) {
			System.out.println("Los productos de su lista de favoritos son :\n");
			for (String prod : liFav)
				System.out.println(prod);
		} else
			System.out.println("Lista de favoritos vacía");

	}

	public void muestraMenu() {
		System.out.println("--------------------------------------------------------------");
		muestraOpciones();
		System.out.println("--------------------------------------------------------------");
		System.out.println("Elige una opcion: ");

	}

	public void muestraEnunciado() {
		System.out.println("00-  Salir");// ++
		System.out.println("01-  Añadir producto");// ++
		System.out.println("02-  Eliminar producto");// ++
		System.out.println("03-  Modificar cantidad de producto");//
		System.out.println("04-  Marcar como producto favorito");// ++
		System.out.println("05-  Desmarcar como producto favorito");// ++
		System.out.println("06-  Marcar como producto comprado");// ++
		System.out.println("07-  Desmarcar como producto comprado");// ++
		System.out.println("08-  Mostrar lista de la compra");// ++
		System.out.println("09-  Mostrar productos favoritos");// ++
		System.out.println("10-  Borrar lista de la compra");
		System.out.println("11-  Borrar todos los favoritos");
		System.out.println("12-  Guardar datos");// ++
		System.out.println("13-  Cargar datos");// ++
	}

	public void muestraOpciones() {
		muestraEnunciado();
		eligeOpcion();
	}

	public void eligeOpcion() {

		Scanner teclado = new Scanner(System.in);
		Integer opcion = -1;

		try {
			while (opcion != 0) {
				try {
					opcion = teclado.nextInt();
				} catch (Exception e) {
					System.out.println("Introduce un numero");
					opcion = 0;
					muestraOpciones();

				}
				switch (opcion) {
				case 0:
					break;
				case 1:
					System.out.println("Elige nombre de producto:");
					String producto = teclado.next();
					Integer cantidad = pedirNumero(teclado);
					if (listaCompra.anadirProducto(producto, cantidad))
						System.out.println("Añadido correctamente!");
					else
						System.out.println("Sin cambios");
					// persistenciaLista.guardarContenido(listaCompra);
					break;
				case 2:
					System.out.println("Elige producto a eliminar");
					producto = teclado.next();
					if (listaCompra.eliminarProducto(producto))
						System.out.println("Eliminado correctamente!");
					else
						System.out.println("Sin cambios");
					break;
				case 3:
					System.out.println("Elige producto a modificar cantidad");
					producto = teclado.next();
					cantidad = pedirNumero(teclado);
					if (listaCompra.modificarCantidad(producto, cantidad))
						System.out.println("Cambiada la cantidad correctamente!");
					else
						System.out.println("Sin cambios");
					break;
				case 4:
					System.out.println("Elige producto de la lista de la compra o uno nuevo");
					producto = teclado.next();
					listaCompra.anadirFavorito(producto);
					System.out.println("Almacenado como favorito correctamente!");
					break;
				case 5:
					System.out.println("Elige producto a desmarcar como favorito");
					producto = teclado.next();
					if (listaCompra.eliminarFavorito(producto)) {
						System.out.println("Eliminado de favoritos correctamente!");
					} else
						System.out.println("Sin cambios");
					break;
				case 6:
					System.out.println("Elige producto a marcar como comprado");
					producto = teclado.next();
					if (listaCompra.setComprado(producto, true)) {
						System.out.println("Marcado como comprado correctamente!");
					} else
						System.out.println("Sin cambios");
					break;
				case 7:
					System.out.println("Elige producto a desmarcar como comprado");
					producto = teclado.next();
					if (listaCompra.setComprado(producto, false)) {
						System.out.println("Desmarcado como comprado correctamente!");
					} else
						System.out.println("Sin cambios");
					break;
				case 8:
					HashMap<String, LineaProducto> liCo = new HashMap<String, LineaProducto>(
							listaCompra.getListaCompra());
					muestraListaCompra(liCo);
					break;
				case 9:
					List<String> liFav = new ArrayList<String>(listaCompra.getFavoritos());
					muestraListaFavoritos(liFav);
					break;
				case 10:
					listaCompra.limpiarListaCompra();
					System.out.println("Lista de la compra eliminada completamente");
					break;

				case 11:
					listaCompra.limpiarFavoritos();
					System.out.println("Lista de favoritos eliminada completamente");
					break;
				case 12:
					System.out.println("Se estan guardando los datos...");
					try {
						persistencia.guardarContenido(listaCompra);
					} catch (Exception e) {
						System.out.println("Error inesperado");
					}
					System.out.println("Datos almacenados correctamente...");
					break;
				case 13:
					System.out.println("Se estan cargando los datos...");
					try {
						this.listaCompra = persistencia.cargarCont();
					} catch (Exception e) {
						System.out.println("Error inesperado");
					}
					System.out.println("Datos cargados!...");
					break;

				default:

					break;
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
