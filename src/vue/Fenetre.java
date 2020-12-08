package vue;

import controleur.Controleur;
import javafx.stage.Stage;
//import javafx.stage.StageStyle;

public class Fenetre extends Navigateur {

	@Override
	public void start(Stage stade) throws Exception {
		//stade.initStyle(StageStyle.UNDECORATED);
		stade.setScene(Controleur.selectionnerVuePrincipale()); // une vue est appliquée à la fenêtre
		stade.show();
		this.stade = stade;
	}

}
