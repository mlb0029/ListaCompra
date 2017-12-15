package modelo.persistencia;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import modelo.datos.*;

import java.util.Formatter;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Forma de trabajar con los ficheros de datos de la aplicación.
 * <p>
 * Hereda de Persistencia y termina de implementar IPersistencia.
 * 
 * @author MIGUEL ANGEL LEON BARDAVIO
 * @see Persistencia
 * @see IPersistencia
 */
public class PersistenciaListaCompra extends Persistencia implements IPersistencia{


	/**
	 * Constructor de la clase.
	 */
	public PersistenciaListaCompra() {
		super();
	}
	
	/* (non-Javadoc)
	 * @see modelo.persistencia.IPersistencia#guardarContenido(modelo.datos.IListaCompra)
	 */
	@Override
	public void guardarContenido(ListaCompra listaCompra){
		String isFav = "";
		String cantidad ="";
		String estaComprado="";
		HashSet<String> products = new HashSet<String>(listaCompra.getListaCompra().keySet());
		products.addAll(listaCompra.getFavoritos());
		HashMap<String, LineaProducto> liCo = new HashMap<String, LineaProducto>(listaCompra.getListaCompra());
		HashSet<String> liFav = new HashSet<String>(listaCompra.getFavoritos());
		Formatter f;
		
		try {
			f = new Formatter(this.fileListaCompra);
			//f.format("%s\n", listaCompra.numProductos().toString());
			for (String producto : products) {
				if (liFav.contains(producto))
					isFav = "fav";
				else
					isFav = "noFav";
				if (liCo.containsKey(producto)) {
					cantidad = liCo.get(producto).getCantidad().toString();
					if (liCo.get(producto).getEstaComprado())
						estaComprado = "comprado";
					else
						estaComprado = "noComprado";
				}else {
					cantidad = "0";
					estaComprado = "noComprado";
				}
				f.format("%s;%s;%s;%s\n", producto, isFav, cantidad, estaComprado);
				
			}
			f.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/* (non-Javadoc)
	 * @see modelo.persistencia.IPersistencia#cargarCont()
	 */
	@Override
	public ListaCompra cargarCont() throws Exception {
		ListaCompra listaCompra = ListaCompra.getInstance();
		listaCompra.clearAll();
		File file;
		Scanner sc;
		String[] line;
		String nombreProducto;
		Integer cantidad;
		
		try {
			file = new File(this.fileListaCompra);
			sc = new Scanner(file);
			while (sc.hasNext()) {
				line = sc.next().split(";");
				nombreProducto = line[0];
				if (line[1].equals("fav"))
					listaCompra.anadirFavorito(nombreProducto);
				cantidad = Integer.parseInt(line[2]);
				if (cantidad > 0)
					listaCompra.anadirProducto(nombreProducto, cantidad);
				if (line[3].equals("comprado")) {
					listaCompra.setComprado(nombreProducto, true);
				}
			}
			sc.close();
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("No se encuentra el fichero");
		}catch (Exception e) {
			throw new Exception("Error al guardar datos");
		}
		return listaCompra;
	}
}
