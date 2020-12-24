package controleur;

import com.sun.media.jfxmedia.logging.Logger;

import donnee.SalonDAO;
import donnee.MessageDAO;
import vue.Navigateur;
import vue.VueSalon;
import vue.VueSalons;

public class ControleurSalons extends Controleur{

	public ControleurSalons()
	{
		Logger.logMsg(Logger.INFO, "new ControleurCollections()");
	}
	
	// RECEPTION des EVENEMENTS
	public void actionOuvrirSalon(int numero)
	{
		SalonDAO salonDAO = new SalonDAO();
		VueSalon.getInstance().getControleur().actionOuvrirSalon(numero);
	}

}
