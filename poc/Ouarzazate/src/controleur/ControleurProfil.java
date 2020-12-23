package controleur;

import com.sun.media.jfxmedia.logging.Logger;


import vue.Navigateur;
import vue.VueSalons;

public class ControleurProfil extends Controleur{

	
	public ControleurProfil()
	{
		Logger.logMsg(Logger.INFO, "new ControleurProfil()");
	}
	
	public void retourAuMenu()
	{
		Navigateur.getInstance().afficherVue(VueSalons.getInstance());
	}
	
	
}
