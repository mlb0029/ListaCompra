package aplicacion;

import java.awt.List;
import java.util.ArrayList;
import java.util.Map;

import interfaces.InterfazConsola;
import persistencia.IPersistencia;
import persistencia.PersistenciaLista;
import modelo.LineaProducto;
import modelo.Producto;
@SuppressWarnings("unused")

/**
 * Clase Aplicación.
 * <p>
 * Clase donde tiene lugar la función main.
 * 
 * @author MIGUEL ANGEL LEON BARDAVIO
 *
 */
public class Aplicacion {

	/**
	 * Nombre del fichero dónde se almacenan los datos.
	 */
	public static final String fileListaCompra = "listaCompra.txt";

	/**
	 * Función main.
	 * 
	 * @param args Argumentos
	 */
	public static void main(String[] args) {
		InterfazConsola apliListaCompra = new InterfazConsola();
    	apliListaCompra.muestraMenu();
		
		
		 // Lista
		 IPersistencia<LineaProducto> persistenciaLista = new
		 PersistenciaLista(fileListaCompra);
//		 List<LineaLista> listaCompra = new ArrayList<LineaLista>();
		 Map<String, LineaProducto> listaCompraCarg = null;
//		
//		 listaCompra.add(new LineaLista(3,false,false,"lechuga"));
//		 listaCompra.add(new LineaLista(2,false,true,"tomates"));
		 try {
//		 System.out.println("guardar");
//		 persistenciaListaCompra.guardarContenido(listaCompra);
//		
//		 System.out.println("muestra");
//		 persistenciaListaCompra.muestraContenido();
//		 //
		 System.out.println("cargada");
		 listaCompraCarg = persistenciaLista.cargarCont();
		 System.out.println(listaCompraCarg.values());
		
		 // System.out.println(lista);
		 } catch (Exception e) {
		 // TODO Auto-generated catch block
		 e.printStackTrace();
		 }
	}

}
