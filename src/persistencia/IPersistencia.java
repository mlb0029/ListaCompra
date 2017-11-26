package persistencia;
import modelo.ListaCompra;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IPersistencia {

	public abstract void muestraContenido() throws FileNotFoundException, IOException;

	public abstract void guardarContenido(ListaCompra listaCompra) throws Exception;

	public abstract ListaCompra cargarCont() throws Exception;

}
