package vue;

import com.sun.media.jfxmedia.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;

// Classe qui regroupe toutes les vues et permet de changer de page
public abstract class Navigateur extends Application{ // Application de javafx est en réalité une fenêtre
	
	protected Stage stade;
		
	private static Navigateur instance = null;
	public static Navigateur getInstance() {return instance;}	
	protected Navigateur()
	{
		instance = this;
		Logger.setLevel(Logger.INFO);
		VueChamps.getInstance().activerControles();
		VueChamp.getInstance().activerControles();
		VueAjouterSemence.getInstance().activerControles();
		VueAjouterSemence.getInstance().controleur = VueChamp.getInstance().controleur;
		VueEditerSemence.getInstance().activerControles();
		VueEditerSemence.getInstance().controleur = VueChamp.getInstance().controleur;
	}
	
	public void afficherVue(Vue vue)
	{
		stade.setScene(vue);
		stade.show();				
	}
}
