package donnee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
		try {
			PreparedStatement requeteAjouterMessage = connection.prepareStatement("Insert into messages(id,message,pseudo,heure,id_salon) VALUES(?,?,?,?,?)");
			requeteAjouterMessage.setInt(1, message.getId());
			requeteAjouterMessage.setString(2, message.getTexteDuMessage());
			requeteAjouterMessage.setString(3, message.getPseudo());
			requeteAjouterMessage.setString(2, message.getDateMessage());
			requeteAjouterMessage.setInt(5, message.getSalonId());
			requeteAjouterMessage.execute();
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
