package donnee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modele.Semence;

public class SemenceDAO {
	
	public Semence detaillerSemence(int id)
	{
		Connection connection = BaseDeDonnees.getInstance().getConnection();
		Semence semence = new Semence();
				
		PreparedStatement requeteSemences;
		try {
			requeteSemences = connection.prepareStatement("SELECT * from semences WHERE id = ?");
			requeteSemences.setInt(1, id);
			ResultSet curseur = requeteSemences.executeQuery();
			curseur.next();
			String typeSemence = curseur.getString("typesemence");
			String datePlantation = curseur.getString("dateplantation");
			semence.setTypeSemence(typeSemence);
			semence.setDatePlantation(datePlantation);
			semence.setId(id);
			
		} 
		catch (SQLException e) {
				e.printStackTrace();
		}
		
		return semence;
	}
	
	
	
	public List<Semence> listerSemences(int numero)
	{
		Connection connection = BaseDeDonnees.getInstance().getConnection();
		
		List<Semence> listeSemences =  new ArrayList<Semence>();			
		PreparedStatement requeteSemences;
		try {
			requeteSemences = connection.prepareStatement("SELECT * from semences WHERE champ_id = ?");
			requeteSemences.setInt(1, numero);
			ResultSet curseurListeSemences = requeteSemences.executeQuery();
			
			while(curseurListeSemences.next())
			{
				int id = curseurListeSemences.getInt("id");
				String typeSemence = curseurListeSemences.getString("typesemence");
				String datePlantation = curseurListeSemences.getString("dateplantation");
				Semence semence = new Semence();
				semence.setId(id);
				semence.setTypeSemence(typeSemence);
				semence.setDatePlantation(datePlantation);
				listeSemences.add(semence);
			}
		} catch (SQLException e) {
				e.printStackTrace();
		}
		
		return listeSemences;
	}
	
	public void ajouterSemence(Semence semence)
	{
		Connection connection = BaseDeDonnees.getInstance().getConnection();
		try {
			PreparedStatement requeteAjouterSemence = connection.prepareStatement("Insert into semences(typesemence,dateplantation) VALUES(?,?)");
			requeteAjouterSemence.setString(1, semence.getTypeSemence());
			requeteAjouterSemence.setString(2, semence.getDatePlantation());
			requeteAjouterSemence.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void editerSemence(Semence semence) 
	{
		Connection connection = BaseDeDonnees.getInstance().getConnection();
		try {
			PreparedStatement requeteModifierSemence = connection.prepareStatement("UPDATE semences SET typesemence = ?, dateplantation = ? WHERE id = ?");
			requeteModifierSemence.setString(1, semence.getTypeSemence());
			requeteModifierSemence.setString(2, semence.getDatePlantation());
			requeteModifierSemence.setInt(3,semence.getId());
			requeteModifierSemence.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
