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
	
	protected List<Salon> listeSalons;
	
	public SalonDAO() {
		initialiserListeSalons();
	}
	
	private void initialiserListeSalons() {
		Connection connection = BaseDeDonnees.getInstance().getConnection();

	    listeSalons =  new ArrayList<Salon>();
			Statement requeteListeSalons;
			try {
				requeteListeSalons = connection.createStatement();
				ResultSet curseurListeSalons = requeteListeSalons.executeQuery("SELECT id,nom from salon");
				while(curseurListeSalons.next())
				{
					int id = curseurListeSalons.getInt("id");
					String nom = curseurListeSalons.getString("nom");
					Salon salon = new Salon();
					salon.setId(id);
					salon.setNom(nom);
					listeSalons.add(salon);
				}
			} catch (SQLException e) {
					e.printStackTrace();
			}
	}

  public List<Salon> listerSalons(){
		return listeSalons;
	}

	public Salon detaillerSalon(int numero)
	{
		Salon bonSalon = new Salon();
		for (Salon salon: listeSalons) {
			if (salon.getId() == numero) {
				bonSalon = salon;
			}
		}
		return bonSalon;
	}
}
