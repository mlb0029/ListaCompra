package persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import modelo.LineaProducto;
import modelo.ListaCompra;

public class PersistenciaLista extends Persistencia<LineaProducto,String> {

	public PersistenciaLista(String fileListaCompra) {
		super(fileListaCompra);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void guardarContenido(Collection<LineaProducto> lista) {
	
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
	public ListaCompra cargarCont() {
		String csvFile = this.file;
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		ListaCompra listaCompra = ListaCompra.getInstance();
		

		try {

			br = new BufferedReader(new FileReader(csvFile));

			while ((line = br.readLine()) != null) {

				// use point comma as separator
				String[] product = line.split(cvsSplitBy);
				String nomProd=product[0].split(":")[1];
				Boolean favorito= Boolean.parseBoolean(product[1].split(":")[1]);
				Integer cantidad= Integer.parseInt(product[2].split(":")[1]);
				Boolean comprado= Boolean.parseBoolean(product[3].split(":")[1]);

				listaCompra.a�adirProducto(nomProd,cantidad);
				if (favorito){
					listaCompra.a�adirFavorito(nomProd);
				}
				if(comprado){
					listaCompra.marcarComprado(nomProd);
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
		
		return listaCompra;
	}



}
