package controleur;

import java.util.List;

import com.sun.media.jfxmedia.logging.Logger;

import donnee.SalonDAO;
import donnee.MessageDAO;
import modele.Salon;
import modele.Message;
import vue.Navigateur;
import vue.VueAjouterSemence;
import vue.VueSalon;
import vue.VueSalons;
import vue.VueProfil;

public class ControleurSalon extends Controleur{

	protected SalonDAO salonDAO = new SalonDAO();
	protected MessageDAO messageDAO = new MessageDAO();
	protected Salon salon = null;
	protected List<Message> messages = null;
	
	public ControleurSalon()
	{
		Logger.logMsg(Logger.INFO, "new ControleurChamp()");
	}
	
	public void actionOuvrirSalon(int numero)
	{
		this.salon = salonDAO.detaillerSalon(numero);
		VueSalon.getInstance().intialiserSalon(this.salon);
		this.messages = messageDAO.listerMessages(numero);
		VueSalon.getInstance().afficherMessages(this.messages);
		Navigateur.getInstance().afficherVue(VueSalon.getInstance());
	}
	
	public void notifierClicNouveauMessage(String texteMessage) // ICI INSERER DANS LA BASE
	{
		//Navigateur.getInstance().afficherVue(VueAjouterSemence.getInstance());
		Logger.logMsg(Logger.INFO, "Nouveau message :"+texteMessage+"");
	}
	
	
	public void notifierClicEnregisterAjoutSemence() {
		
		Message message = VueAjouterSemence.getInstance().lireSemence();
		message.setSalonId(salon.getId());
		MessageDAO messageDAO = new MessageDAO();
		messageDAO.ajouterMessage(message);
		this.messages.add(message);
		VueSalon.getInstance().afficherMessages(messages); // TODO : optimiser
		Navigateur.getInstance().afficherVue(VueSalon.getInstance());
	}
	
	protected Message message;
	
	public void notifierClicAfficherProfil(int id) // TODO: à faire ainsi que le DAO pour le profil
	{
		MessageDAO messagesDAO = new MessageDAO();
		this.message = messagesDAO.detaillerMessage(id);
		Logger.logMsg(Logger.INFO, "ControleurChamp.notifierClicEditionMessage("+id+")");
		VueProfil.getInstance().afficherSemence(message);
		Navigateur.getInstance().afficherVue(VueProfil.getInstance());
		
	}
	
	public void notifierClicRetour() 
	{
		/*Message semence = VueProfil.getInstance().lireMessage();
		semence.setSalonId(salon.getId());
		semence.setId(this.message.getId());
		
		semenceDAO.editerSemence(semence);*/
		MessageDAO messageDAO = new MessageDAO();
		this.messages = messageDAO.listerMessages(salon.getId()); // TODO: à opti
		VueSalon.getInstance().afficherMessages(messages);
		Navigateur.getInstance().afficherVue(VueSalon.getInstance());
		
		
	}
	
	public void retourAuMenu()
	{
		Navigateur.getInstance().afficherVue(VueSalons.getInstance());
	}
	
	
}
