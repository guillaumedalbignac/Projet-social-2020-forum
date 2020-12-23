package controleur;

import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date ;

import com.sun.media.jfxmedia.logging.Logger;

import donnee.SalonDAO;
import donnee.BaseDeDonnees;
import donnee.MessageDAO;
import modele.Salon;
import modele.Message;
import vue.Navigateur;
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

		String Pseudo = "Esteban";
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	    Date date = new Date();
	    String dateMessage = dateFormat.format(date);
	    System.out.println(dateFormat.format(date));
	    
	    int maxId = 0;
	  
		Connection connection = BaseDeDonnees.getInstance().getConnection();
		try {
		String queryMax = "SELECT MAX(id) as max_id from messages";
		Statement requeteID = connection.createStatement();
		ResultSet idd = requeteID.executeQuery(queryMax);
		idd.next();
		maxId = idd.getInt("max_id");
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		Message message = new Message();
		message.setId(maxId+1);
		message.setSalonId(salon.getId());
		message.setTexteDuMessage(texteMessage);
		message.setDateMessage(dateMessage);
		message.setPseudo(Pseudo);
		MessageDAO messageDAO = new MessageDAO();
		messageDAO.ajouterMessage(message);
		this.messages.add(message);
		VueSalon.getInstance().afficherMessages(messages); // TODO : optimiser
		Navigateur.getInstance().afficherVue(VueSalon.getInstance());
		
		Logger.logMsg(Logger.INFO, "\nINFO : "+message.getPseudo() +" ("+ message.getId() +") : " +message.getTexteDuMessage() + " " + message.getDateMessage()+"-"+message.getSalonId());
	}
	
	protected Message message;
	
	public void notifierClicAfficherProfil(int id) // TODO: à faire ainsi que le DAO pour le profil
	{
		MessageDAO messagesDAO = new MessageDAO();
		this.message = messagesDAO.detaillerMessage(id);
		Logger.logMsg(Logger.INFO, "ControleurSalon.notifierClicEditionMessage("+id+")");
		//VueProfil.getInstance().afficherSemence(message);
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
