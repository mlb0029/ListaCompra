package modelo.persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import modelo.datos.ListaCompra;

/**
 * Forma de almacenar los datos en el fichero.
 * <p>
 * Completa la implementación de la interfaz que implementa la clase Persistencia.
 * 
 * @author CLARA PALACIOS RODRIGO
 * @see Persistencia
 * @see IPersistencia
 */
public class PersistenciaLista extends Persistencia implements IPersistencia{

	/**
	 * Constructor de la clase.
	 * 
	 */
	public PersistenciaLista() {
		super();
	}

	/* (non-Javadoc)
	 * @see modelo.persistencia.IPersistencia#guardarContenido(modelo.datos.IListaCompra)
	 */
	@Override
	public void guardarContenido(ListaCompra lista) {
	
		PrintWriter pw = null;
		FileOutputStream fo = null;
		File file = null;
		try {
			file = new File(this.fileListaCompra);
			pw = new PrintWriter(new FileOutputStream(file), true);
			fo = new FileOutputStream(file);
			for (String p : lista.getListaCompra().keySet()) {
				pw.write(lista.getListaCompra().get(p).toString());
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			pw.flush();
			pw.close();
			try {
				fo.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	};

	/* (non-Javadoc)
	 * @see modelo.persistencia.IPersistencia#cargarCont()
	 */
	@Override
	public ListaCompra cargarCont() {
		String csvFile = this.fileListaCompra;
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		ListaCompra listaCompra = ListaCompra.getInstance();
		

		try {

			br = new BufferedReader(new FileReader(csvFile));

			while ((line = br.readLine()) != null) {

				// use point comma as separator
				String[] product = line.split(cvsSplitBy);
				String nomProd=product[0].split(":")[1];
				Boolean favorito= Boolean.parseBoolean(product[1].split(":")[1]);
				Integer cantidad= Integer.parseInt(product[2].split(":")[1]);
				Boolean comprado= Boolean.parseBoolean(product[3].split(":")[1]);

				listaCompra.anadirProducto(nomProd,cantidad);
				if (favorito){
					listaCompra.anadirFavorito(nomProd);
				}
				if(comprado){
					listaCompra.setComprado(nomProd, true);
				}
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return listaCompra;
	}
}
