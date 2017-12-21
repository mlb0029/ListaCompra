package modelo.persistencia;

import java.io.FileNotFoundException;
import java.io.IOException;

import modelo.datos.*;
import util.ISujeto_Observado;

/**
 * Define la interfaz para definir el sistema de persistencia.
 * 
 * @author CLARA PALACIOS RODRIGO
 */
public interface IPersistencia extends ISujeto_Observado{

	/**
	 * Muestra en pantalla el contenido del fichero.
	 * 
	 * @throws FileNotFoundException No se encuentra el fichero.
	 * @throws IOException Error de entrada/salida del fichero.
	 */
	public abstract void muestraContenido() throws FileNotFoundException, IOException;

	/**
	 * Almacena los datos creados por la aplicación en un fichero.
	 * 
	 * @param listaCompra Datos creados por la aplicación.
	 * @throws Exception De apertura del fichero.
	 */
	public abstract void guardarContenido(ListaCompra listaCompra) throws Exception;

	/**
	 * Carga los datos de un fichero en la aplicación.
	 * 
	 * @return Almacen de datos de la aplicación.
	 * @throws Exception De apertura del fichero.
	 */
	public abstract ListaCompra cargarCont() throws Exception;

}
