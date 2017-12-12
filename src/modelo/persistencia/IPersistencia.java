package modelo.persistencia;

import java.io.FileNotFoundException;
import java.io.IOException;

import modelo.datos.*;

/**
 * Define la interfaz para definir el sistema de persistencia.
 * 
 * @author CLARA PALACIOS RODRIGO
 */
public interface IPersistencia {

	/**
	 * Muestra en pantalla el contenido del fichero.
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public abstract void muestraContenido() throws FileNotFoundException, IOException;

	/**
	 * Almacena los datos creados por la aplicación en un fichero.
	 * 
	 * @param listaCompra Datos creados por la aplicación.
	 * @throws Exception
	 */
	public abstract void guardarContenido(IListaCompra listaCompra) throws Exception;

	/**
	 * Carga los datos de un fichero en la aplicación.
	 * 
	 * @return Almacen de datos de la aplicación.
	 * @throws Exception
	 */
	public abstract ListaCompra cargarCont() throws Exception;

}
