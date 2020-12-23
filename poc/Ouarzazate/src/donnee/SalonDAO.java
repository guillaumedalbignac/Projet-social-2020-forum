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

  public List<Salon> listerSalons(){
    Connection connection = BaseDeDonnees.getInstance().getConnection();

    List<Salon> listeSalons =  new ArrayList<Salon>();
		Statement requeteListeSalons;
		try {
			requeteListeSalons = connection.createStatement();
			ResultSet curseurListeSalons = requeteListeSalons.executeQuery("SELECT * from salon");
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

		return listeSalons;
	}

	public Salon detaillerSalon(int numero)
	{
		Connection connection = BaseDeDonnees.getInstance().getConnection();

    Salon salon =  new Salon();
		PreparedStatement requeteSalon;
			try {
				requeteSalon = connection.prepareStatement("SELECT * from salon WHERE id = ?");
				requeteSalon.setInt(1, numero);

				ResultSet curseurSalon = requeteSalon.executeQuery();
				curseurSalon.next();
				int id = curseurSalon.getInt("id");
				String nom = curseurSalon.getString("nom");

				salon.setId(id);
				salon.setNom(nom);

			} catch (SQLException e) {
				e.printStackTrace();
			}

		return salon;
	}
}
