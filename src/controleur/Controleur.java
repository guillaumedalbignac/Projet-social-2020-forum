package controleur;

import donnee.SalonDAO;
import vue.Vue;
import vue.VueAjouterSemence;
import vue.VueSalons;

//import vue.Navigateur;
//import vue.*;

public class Controleur {

	public static Vue selectionnerVuePrincipale()
	{
		//return VueAjouterSemence.getInstance();
		SalonDAO salonDAO = new SalonDAO();
		VueSalons.getInstance().afficherSalons(salonDAO.listerSalons());
		return VueSalons.getInstance();
	}
		
}
