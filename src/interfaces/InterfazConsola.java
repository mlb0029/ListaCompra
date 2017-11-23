package interfaces;


import java.util.ArrayList;
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
	public ListaCompra listaCompra =ListaCompra.getInstance();
	public ListaCompra listaFavoritos =ListaCompra.getInstance();
	public LineaProducto productos =null;	

	public void muestraMenu() {
		System.out.println("--------------------------------------------------------------");
		System.out.println("Elige una opcion:\n");
		muestraOpciones();
		System.out.println("--------------------------------------------------------------");

	}

	public void muestraOpciones() {
		System.out.println("1-  Añadir producto");
		System.out.println("2-  Eliminar producto");
		System.out.println("3-  Modificar cantidad de producto");
		System.out.println("4-  Marcar como producto favorito");
		System.out.println("5-  Desmarcar como producto favorito");
		System.out.println("6-  Marcar como producto comprado");
		System.out.println("7-  Mostrar productos favoritos");
		System.out.println("8-  Eliminar favoritos");
		System.out.println("9-  Guardar datos");
		System.out.println("0- Salir\n");
		eligeOpcion();

	}

	public void eligeOpcion() {
		IPersistencia<LineaProducto> persistenciaLista = new PersistenciaLista(fileListaCompra);
		Map<String, LineaProducto> listaCompraCarg = null;
		listaCompraCarg = persistenciaLista.cargarCont();
		
		HashSet<String>  favoritos = listaFavoritos.getFavoritos();
		Scanner teclado = new Scanner(System.in);
		 String cantidad =  teclado.next();
		 Integer val= Integer.parseInt(cantidad);
		int numero = -1;
		try {
			while (numero!=10){
			numero = teclado.nextInt();
			String producto;
				
			switch (numero) {
			case 1:
				System.out.println("Nombre del nuevo producto:");
				producto = teclado.next();
				listaCompra.añadirProducto(producto,val);
				//persistenciaLista.guardarContenido(listaCompra);
				break;
			case 2:
				System.out.println("Elige producto");
				producto = teclado.next();
				listaCompra.eliminarProducto(producto);
				break;

			case 3:
				System.out.println("Elige producto");
				producto = teclado.next();
				productos.setCantidad(val);
				break;
				

			case 5:
				System.out.println("Elige producto");
				producto = teclado.next();
				listaFavoritos.añadirFavorito( producto);
				break;

			case 6:
				System.out.println("Elige producto");
				producto = teclado.next();
				listaFavoritos.eliminarFavorito(producto);
				break;

			case 7:
				System.out.println("Elige producto");
		
				producto = teclado.next();
				 productos.marcarComprado();
				break;

			case 8:
				favoritos = listaFavoritos.getFavoritos();
				System.out.println(favoritos.toString());
				break;

			case 9:
				List<LineaProducto> contGuardar = new ArrayList<LineaProducto>();
				persistenciaLista.guardarContenido(contGuardar);
				System.out.println(contGuardar);
				break;

			case 10:
				return;
			default:
				muestraMenu();
			}
			}
		} catch (Exception ex) {
			System.out.println("No lee el numero");
			ex.printStackTrace();
		} finally {
			teclado.close();
		}
		
	}
}
