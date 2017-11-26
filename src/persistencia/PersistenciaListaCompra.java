/**
 * 
 */
package persistencia;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Formatter;

import modelo.ListaCompra;

/**
 * @author MIGUEL ANGEL LEON BARDAVIO
 *
 */
public class PersistenciaListaCompra implements IPersistencia {

	String fileName;
	
	public PersistenciaListaCompra(String fileListaCompra) {
		fileName = fileListaCompra;
	}

	@Override
	public void muestraContenido() throws FileNotFoundException {
	  File x = new File("C:\\sololearn\\test.txt");
	  Scanner sc = new Scanner(x);
	  while(sc.hasNext())
		  System.out.println(sc.next());
	  sc.close();
		
	}

	public void guardarContenido(ListaCompra listaCompra) throws Exception{
		String isFav = "";
		String cantidad ="";
		String estaComprado="";
		Formatter f;
		
		try {
			f = new Formatter(this.fileName);
			//f.format("%s\n", listaCompra.numProductos().toString());
			for (String producto : listaCompra.getListaProductos().keySet()) {
				if (listaCompra.getProducto(producto).isFavorito())
					isFav = "fav";
				else
					isFav = "noFav";
				if (listaCompra.getListaCompra().containsKey(producto)) {
					cantidad = listaCompra.getLineaProducto(producto).getCantidad().toString();
					if (listaCompra.getLineaProducto(producto).getEstaComprado())
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

	@Override
	public ListaCompra cargarCont() throws Exception {
		ListaCompra.delInstance();
		ListaCompra listaCompra = ListaCompra.getInstance();
		File file;
		Scanner sc;
		String[] line;
		String nombreProducto;
		Integer cantidad;
		
		try {
			file = new File(this.fileName);
			sc = new Scanner(file);
			while (sc.hasNext()) {
				line = sc.next().split(";");
				nombreProducto = line[0];
				if (line[1].equals("fav"))
					listaCompra.añadirFavorito(nombreProducto);
				cantidad = Integer.parseInt(line[2]);
				if (cantidad > 0)
					listaCompra.añadirProducto(nombreProducto, cantidad);
				if (line[3].equals("comprado")) {
					listaCompra.getLineaProducto(nombreProducto).marcarComprado();
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
