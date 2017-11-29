package modelo.persistencia;
import java.io.FileNotFoundException;
import java.io.IOException;

import modelo.datos.ListaCompra;

public interface IPersistencia {

	public abstract void muestraContenido() throws FileNotFoundException, IOException;

	public abstract void guardarContenido(ListaCompra listaCompra) throws Exception;

	public abstract ListaCompra cargarCont() throws Exception;

}
