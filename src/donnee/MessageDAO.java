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
		Message semence = new Message();
				
		PreparedStatement requeteSemences;
		try {
			requeteSemences = connection.prepareStatement("SELECT * from semences WHERE id = ?");
			requeteSemences.setInt(1, id);
			ResultSet curseur = requeteSemences.executeQuery();
			curseur.next();
			String typeSemence = curseur.getString("typesemence");
			String datePlantation = curseur.getString("dateplantation");
			semence.setTexteDuMessage(typeSemence);
			semence.setDateMessage(datePlantation);
			semence.setId(id);
			
		} 
		catch (SQLException e) {
				e.printStackTrace();
		}
		
		return semence;
	}
	
	
	
	public List<Message> listerMessages(int numero)
	{
		Connection connection = BaseDeDonnees.getInstance().getConnection();
		
		List<Message> listeMessages =  new ArrayList<Message>();			
		PreparedStatement requeteMessages;
		try {
			requeteMessages = connection.prepareStatement("SELECT * from semences WHERE champ_id = ?");
			requeteMessages.setInt(1, numero);
			ResultSet curseurListeMessages = requeteMessages.executeQuery();
			
			while(curseurListeMessages.next())
			{
				int id = curseurListeMessages.getInt("id");
				String texteMessage = curseurListeMessages.getString("typesemence");
				String dateMessage = curseurListeMessages.getString("dateplantation");
				Message message = new Message();
				message.setId(id);
				message.setTexteDuMessage(texteMessage);
				message.setDateMessage(dateMessage);
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
			PreparedStatement requeteAjouterMessage = connection.prepareStatement("Insert into semences(typesemence,dateplantation) VALUES(?,?)");
			requeteAjouterMessage.setString(1, message.getTexteDuMessage());
			requeteAjouterMessage.setString(2, message.getDateMessage());
			requeteAjouterMessage.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void editerSemence(Message semence) 
	{
		Connection connection = BaseDeDonnees.getInstance().getConnection();
		try {
			PreparedStatement requeteModifierSemence = connection.prepareStatement("UPDATE semences SET typesemence = ?, dateplantation = ? WHERE id = ?");
			requeteModifierSemence.setString(1, semence.getTexteDuMessage());
			requeteModifierSemence.setString(2, semence.getDateMessage());
			requeteModifierSemence.setInt(3,semence.getId());
			requeteModifierSemence.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
