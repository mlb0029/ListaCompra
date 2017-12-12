package modelo.persistencia;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Introduce un atributo para las subclases que contiene el nombre del fichero e implementa
 * el procedimiento muestraContenido().
 * 
 * @author CLARA PALACIOS RODRIGO
 * @see IPersistencia#muestraContenido()
 */
public abstract class Persistencia implements IPersistencia {

	/**
	 * Nombre del fichero donde se almacenaran los datos.
	 */
	protected final String fileListaCompra = "listaCompra.txt";
	
	/**
	 * Constructor no hace nada.
	 */
	Persistencia() {
	}

	/* (non-Javadoc)
	 * @see modelo.persistencia.IPersistencia#muestraContenido()
	 */
	@Override
	public void muestraContenido() throws FileNotFoundException, IOException {
		String cadena;
		FileReader f = new FileReader(fileListaCompra);
		BufferedReader b = new BufferedReader(f);
		while ((cadena = b.readLine()) != null) {
			System.out.println(cadena);
		}
		b.close();
	}
}
