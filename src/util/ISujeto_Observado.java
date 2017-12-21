package util;

/**
 * Interfaz que define el rol de Sujeto del patrón de comportamiento "Observador".
 * 
 * @author MIGUEL ANGEL LEON BARDAVIO
 *
 */
public interface ISujeto_Observado {

	/**
	 * Añade un observador.
	 * 
	 * @param listener Observador a añadir.
	 */
	public void addListener(IListaListeners listener);
	
	/**
	 * Elimina un observador.
	 * 
	 * @param listener Observador a eliminar.
	 */
	public void removeListener(IListaListeners listener);
	
	/**
	 * Notifica a los observadores.
	 */
	public void notifyListeners();
}
