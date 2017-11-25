package persistencia;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import modelo.ListaCompra;

public abstract class Persistencia<E,F> implements IPersistencia<E,F> {

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
	public abstract void guardarContenido(Collection<E> lista);

	public abstract ListaCompra cargarCont();

}
