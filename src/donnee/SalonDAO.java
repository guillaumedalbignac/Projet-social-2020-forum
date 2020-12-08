package donnee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import modele.Salon;


public class SalonDAO {


	public List<Salon> listerSalons()
	{
		Connection connection = BaseDeDonnees.getInstance().getConnection();
		
		List<Salon> listeSalons =  new ArrayList<Salon>();			
		Statement requeteListeSalons;
		try {
			requeteListeSalons = connection.createStatement();
			ResultSet curseurListeSalons = requeteListeSalons.executeQuery("SELECT * from champ");
			while(curseurListeSalons.next())
			{
				int id = curseurListeSalons.getInt("id");
				String nom = curseurListeSalons.getString("nom");
				String distanceFerme = curseurListeSalons.getString("distanceferme");
				Salon salon = new Salon();
				salon.setId(id);
				salon.setNom(nom);
				salon.setDistanceFerme(distanceFerme);
				listeSalons.add(salon);
			}
		} catch (SQLException e) {
				e.printStackTrace();
		}
		
		return listeSalons;
	}

	public Salon detaillerChamp(int numero)
	{
		Connection connection = BaseDeDonnees.getInstance().getConnection();
		
		Salon champ =  new Salon();			
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
