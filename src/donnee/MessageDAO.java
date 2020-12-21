package donnee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sun.media.jfxmedia.logging.Logger;

import modele.Message;

public class MessageDAO {

	public Message detaillerMessage(int id)
	{
		Connection connection = BaseDeDonnees.getInstance().getConnection();
		Message message = new Message();

		PreparedStatement requeteMessages;
		try {
			requeteMessages = connection.prepareStatement("SELECT * from messages WHERE id = ?");
			requeteMessages.setInt(1, id);
			ResultSet curseur = requeteMessages.executeQuery();
			curseur.next();
			String typeMessage = curseur.getString("typemessage");
			message.setTexteDuMessage(typeMessage);
			message.setId(id);

		}
		catch (SQLException e) {
				e.printStackTrace();
		}

		return message;
	}



	public List<Message> listerMessages(int numero)
	{
		Connection connection = BaseDeDonnees.getInstance().getConnection();
		
		List<Message> listeMessages =  new ArrayList<Message>();
		PreparedStatement requeteMessages;
		try {
			requeteMessages = connection.prepareStatement("SELECT * from messages WHERE id_salon = ?");
			requeteMessages.setInt(1, numero);
			ResultSet curseurListeMessages = requeteMessages.executeQuery();

			while(curseurListeMessages.next())
			{
				int id = curseurListeMessages.getInt("id");
				String texteMessage = curseurListeMessages.getString("message");
				String dateMessage = curseurListeMessages.getString("heure");
				String pseudo = curseurListeMessages.getString("pseudo");
				Message message = new Message();
				message.setId(id);
				message.setPseudo(pseudo);
				message.setTexteDuMessage(texteMessage);
				message.setDateMessage(dateMessage);
				message.setSalonId(numero);
				listeMessages.add(message);
			}
		} catch (SQLException e) {
				e.printStackTrace();
		}

		return listeMessages;
	}

	public void ajouterMessage(Message message)
	{
		Connection connection = BaseDeDonnees.getInstance().getConnection();
		Statement statement = null;
		try {

			/*String queryDel = "delete from messages where id>9";
			statement = connection.createStatement();
			statement.executeUpdate(queryDel);*/

			String query = "INSERT INTO messages(id,message,pseudo,id_salon) VALUES(?,?,?,?)";
			String queryTH = "SELECT heure from messages where id='3'";
			
			String query1 = "INSERT INTO messages(id,heure) VALUES(?,?)";
			
			Statement requeteID = connection.createStatement();
			ResultSet idd = requeteID.executeQuery(queryTH);
			idd.next();
			Logger.logMsg(Logger.INFO, "\nINFO : "+idd);
			Logger.logMsg(Logger.INFO, "\nINFO : "+idd.toString());
			
			PreparedStatement requeteAjouterMessage = connection.prepareStatement(query);
			PreparedStatement requeteAjouterMessage1 = connection.prepareStatement(query1);

			requeteAjouterMessage.setInt(1,message.getId());
			requeteAjouterMessage.setString(2, message.getTexteDuMessage());
			requeteAjouterMessage.setString(3, message.getPseudo());
			//requeteAjouterMessage.setString(4, message.getDateMessage());
			requeteAjouterMessage.setInt(4, message.getSalonId());

			requeteAjouterMessage1.setInt(1,message.getId()+1);
			requeteAjouterMessage1.setString(2, message.getDateMessage());
			
			requeteAjouterMessage.executeUpdate();
			requeteAjouterMessage1.executeUpdate();
			
			System.out.println(requeteAjouterMessage.executeUpdate());
            
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/*public void editerMessage(Message message)
	{
		Connection connection = BaseDeDonnees.getInstance().getConnection();
		try {
			PreparedStatement requeteModifierMessage = connection.prepareStatement("UPDATE messages SET message = ?, pseudo = ?, heure = ? WHERE id = ?");
			requeteModifierMessage.setString(1, message.getTexteDuMessage());
			requeteModifierMessage.setString(2, message.getDateMessage());
			requeteModifierMessage.setInt(3,message.getId());
			requeteModifierMessage.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}


	}*/

}
