package persistencia;
import java.util.*;

import modelo.ListaCompra;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IPersistencia<E, F> {

	public abstract void muestraContenido() throws FileNotFoundException, IOException;

	public abstract void guardarContenido(Collection<E> lista);

	public abstract ListaCompra cargarCont();

}
