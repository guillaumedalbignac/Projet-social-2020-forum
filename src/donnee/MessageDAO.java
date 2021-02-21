package donnee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.sun.media.jfxmedia.logging.Logger;

import modele.Message;
import redis.clients.jedis.Jedis;

public class MessageDAO {
	
	List<Message> listeMessages;
	
	public MessageDAO() {
		initialiserListeMessages();
	}
	
	private void initialiserListeMessages() {
		Connection connection = BaseDeDonnees.getInstance().getConnection();
		
		listeMessages =  new ArrayList<Message>();
		PreparedStatement requeteMessages;
		try {
			requeteMessages = connection.prepareStatement("SELECT * from messages");
			ResultSet curseurListeMessages = requeteMessages.executeQuery();

			while(curseurListeMessages.next())
			{				
				int id = curseurListeMessages.getInt("id");
				int salonId = curseurListeMessages.getInt("id_salon");
				String texteMessage = curseurListeMessages.getString("message");
				String dateMessage = curseurListeMessages.getString("heure");
				String pseudo = curseurListeMessages.getString("pseudo");
				Message message = new Message();
				message.setId(id);
				message.setPseudo(pseudo);
				message.setTexteDuMessage(texteMessage);
				message.setDateMessage(dateMessage);
				message.setSalonId(salonId);
				listeMessages.add(message);
			}
		} catch (SQLException e) {
				e.printStackTrace();
		}
	}

	public Message detaillerMessage(int id)
	{
		Message bonMessage = new Message();
		for (Message message: listeMessages) {
			if (message.getId() == id) {
				bonMessage = message;
			}
		}
		return bonMessage;
	}



	public List<Message> listerMessages(int numero)
	{
		List<Message> listeMessagesSalon = new ArrayList<>();
		
		for (Message message: listeMessages) {
			if (message.getSalonId() == numero) {
				listeMessagesSalon.add(message);
			}
		}
		
		return listeMessagesSalon;
	}

	public void ajouterMessage(Message message)
	{	
		//Utilisation de la cache
		Jedis cache = new Jedis("localhost");
		
		String idSalon = String.valueOf(message.getSalonId());
		String idMessage = String.valueOf(message.getId());
		String contenuMessage = message.getTexteDuMessage();
		
		
		cache.set(idSalon + "/" + idMessage + "/messages", contenuMessage);
		cache.set("timestamp", Calendar.getInstance().getTimeInMillis() + "");  
		
		//Sauvegarde du message dans la BDD
		Connection connection = BaseDeDonnees.getInstance().getConnection();
		PreparedStatement requeteMessages;
		
		try {
			requeteMessages = connection.prepareStatement("INSERT INTO messages(id,message,pseudo,id_salon) VALUES(?,?,?,?)");
			requeteMessages.setInt(1, message.getId());
			requeteMessages.setString(2, contenuMessage);
			requeteMessages.setString(3, "TestCacheRedis");
			requeteMessages.setInt(4, Integer.parseInt(idSalon));
			
			requeteMessages.executeUpdate();
			//System.out.println("Query sql: " + requeteMessages);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
}
