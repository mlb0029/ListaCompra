package interfaces.gUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Cierra la aplicación.
 * 
 * @author Miguel Ángel León
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
