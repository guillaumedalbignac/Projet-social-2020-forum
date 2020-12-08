package donnee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import modele.Champ;


public class ChampDAO {


	public List<Champ> listerChamps()
	{
		Connection connection = BaseDeDonnees.getInstance().getConnection();
		
		List<Champ> listeChamps =  new ArrayList<Champ>();			
		Statement requeteListeChamps;
		try {
			requeteListeChamps = connection.createStatement();
			ResultSet curseurListeChamps = requeteListeChamps.executeQuery("SELECT * from champ");
			while(curseurListeChamps.next())
			{
				int id = curseurListeChamps.getInt("id");
				String nom = curseurListeChamps.getString("nom");
				String distanceFerme = curseurListeChamps.getString("distanceferme");
				Champ champs = new Champ();
				champs.setId(id);
				champs.setNom(nom);
				champs.setDistanceFerme(distanceFerme);
				listeChamps.add(champs);
			}
		} catch (SQLException e) {
				e.printStackTrace();
		}
		
		return listeChamps;
	}

	public Champ detaillerChamp(int numero)
	{
		Connection connection = BaseDeDonnees.getInstance().getConnection();
		
		Champ champ =  new Champ();			
		PreparedStatement requeteChamp;
			try {
				requeteChamp = connection.prepareStatement("SELECT * from champ WHERE id = ?");
				requeteChamp.setInt(1, numero);
				
				ResultSet curseurChamp = requeteChamp.executeQuery();
				curseurChamp.next();
				int id = curseurChamp.getInt("id");
				String nom = curseurChamp.getString("nom");
				String distanceFerme = curseurChamp.getString("distanceferme");
				int taille = curseurChamp.getInt("taille");
				String fertiliteSol = curseurChamp.getString("fertilitesol");
				
				champ.setId(id);
				champ.setNom(nom);
				champ.setDistanceFerme(distanceFerme);
				champ.setTaille(taille);
				champ.setFertiliteSol(fertiliteSol);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		return champ;
	}
	
}
