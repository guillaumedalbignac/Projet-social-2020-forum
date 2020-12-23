package controleur;

import com.sun.media.jfxmedia.logging.Logger;

import donnee.SalonDAO;
import donnee.MessageDAO;
import vue.Navigateur;
import vue.VueSalon;

public class ControleurSalons extends Controleur{

	public ControleurSalons()
	{
		Logger.logMsg(Logger.INFO, "new ControleurCollections()");
	}
	
	// RECEPTION des EVENEMENTS
	public void actionOuvrirChamp(int numero)
	{
		SalonDAO champDAO = new SalonDAO();
		VueSalon.getInstance().getControleur().actionOuvrirSalon(numero);
	}

}
