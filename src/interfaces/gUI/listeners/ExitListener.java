package interfaces.gUI.listeners;

import aplicacion.GraphicAplication;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Cierra la aplicación.
 * 
 * @author MIGUEL ANGEL LEON BARDAVIO
 * @see GraphicAplication
 */
public class ExitListener implements EventHandler<ActionEvent>{

	/* (non-Javadoc)
	 * @see javafx.event.EventHandler#handle(javafx.event.Event)
	 */
	@Override
	public void handle(ActionEvent arg0) {
		System.exit(0);
	}

}
