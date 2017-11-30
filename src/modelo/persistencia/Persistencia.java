package modelo.persistencia;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import modelo.datos.*;

public abstract class Persistencia implements IPersistencia {

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
	public abstract void guardarContenido(IListaCompra lista);

	@Override
	public abstract ListaCompra cargarCont();

}
