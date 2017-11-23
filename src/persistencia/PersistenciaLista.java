package persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.LineaProducto;
import modelo.ListaCompra;

public class PersistenciaLista extends Persistencia<LineaProducto> {

	public PersistenciaLista(String fileListaCompra) {
		super(fileListaCompra);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void guardarContenido(List<LineaProducto> lista ) {
		// Auto-generated
		// method
		// stub
		PrintWriter pw = null;
		FileOutputStream fo = null;
		File file = null;
		try {
			file = new File(this.file);
			pw = new PrintWriter(new FileOutputStream(file), true);
			fo = new FileOutputStream(file);
			for (LineaProducto p : lista) {
				pw.write(p.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			pw.flush();
			pw.close();
			try {
				fo.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};

	@Override
	public Map<String, LineaProducto> cargarCont() {
		String csvFile = this.file;
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ";";
		Boolean res=false;
		Map<String, LineaProducto> dictProd = new HashMap<String, LineaProducto>();
		try {

			br = new BufferedReader(new FileReader(csvFile));

			while ((line = br.readLine()) != null) {

				// use point comma as separator
				String[] product = line.split(cvsSplitBy);
				ListaCompra listaCompra = ListaCompra.getInstance();
				//ListaCompra listaCompra = new ListaCompra();
				ListaCompra listaFavoritos = ListaCompra.getInstance();
				if (product.length==3){
				listaCompra.añadirProducto(product[0], Integer.parseInt(product[1]));
				}else{
					listaFavoritos.añadirFavorito(product[0]);
					
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return dictProd;
	}

}
