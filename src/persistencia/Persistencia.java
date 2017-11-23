package persistencia;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public abstract class Persistencia<E> implements IPersistencia<E> {

	public String file = null;

	Persistencia(String fileListaCompra) {
		this.file = fileListaCompra;
	}

	@Override
	public void muestraContenido() throws FileNotFoundException, IOException {
		String cadena;
		FileReader f = new FileReader(file);
		BufferedReader b = new BufferedReader(f);
		while ((cadena = b.readLine()) != null) {
			System.out.println(cadena);
		}
		b.close();
	}

	@Override
	public abstract void guardarContenido(List<E> lista);

	public abstract Map<String, E> cargarCont();

}
