package persistencia;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface IPersistencia<E> {

	public abstract void muestraContenido() throws FileNotFoundException, IOException;

	public abstract void guardarContenido(List<E> lista);

	public abstract Map<String, E> cargarCont();

}
