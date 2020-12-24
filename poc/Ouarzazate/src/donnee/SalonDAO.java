package donnee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.sun.media.jfxmedia.logging.Logger;

import redis.clients.jedis.Jedis;

import modele.Salon;

public class SalonDAO {

	/*public static void main(String [] args) {
		
		Jedis cache = new Jedis("localhost", 6380);
		
		cache.set("cours/2/difficulte", 9 + "");
		cache.set("cours/2/nom", "Intelligence articielle");
		cache.set("cours/2/ponderation", "2-3-2");
		
		String nomCours = cache.get("cours/2/nom");
		String difficulteCours = cache.get("cours/2/difficulte");
		int difficulte = Integer.parseInt(difficulteCours);
		
		System.out.println("Le cours recupere est " + nomCours);
		System.out.println("La difficulte est " + difficulte);	
		
		Salon salon1 = new Salon();
		salon1.setNom("Monde virtuel");
		salon1.setId(1);
		Salon salon2 = new Salon();
		salon2.setNom(salon.getNom());
		salon2.setId(2);
		List<Salon> listeSalon = new ArrayList<Salon>();
		listeSalon.add(salon1);
		listeSalon.add(salon2);
		
		for(int position = 0; position <listeSalon.size(); position++)
		{
			Salon salon = listeSalon.get(position);			
			cache.set("cours/" + salon.getId() + "/nom", salon.getNom());
		}
		
		int numero = 0;
		do
		{
			numero++;
			String nom = cache.get("cours/" + numero+ "/nom");
			if(null == nom) break;
			System.out.println(nom);
		}
		while(true);
	}*/

  public List<Salon> listerSalons(){
	  boolean init = false;
		Jedis cache = new Jedis("localhost");
	  if(init==false) {
			List<Salon> listeSalons = new ArrayList<Salon>();

				Statement requeteListeSalons;
				try {
					  Connection connection = BaseDeDonnees.getInstance().getConnection();
					requeteListeSalons = connection.createStatement();
					ResultSet curseurListeSalons = requeteListeSalons.executeQuery("SELECT * from salon");
					while(curseurListeSalons.next())
					{
						int id = curseurListeSalons.getInt("id");
						String nom = curseurListeSalons.getString("nom");
/*
						Logger.logMsg(Logger.INFO, "Numxkjbgx : " + nom + "__"+id);
						Logger.logMsg(Logger.INFO, "Numxkjbgx : " + curseurListeSalons.getString("nom"));
						Logger.logMsg(Logger.INFO, "Numxkjbgx : " + curseurListeSalons.getInt("id"));
*/
						Salon salon = new Salon();
						salon.setNom(nom);
						salon.setId(id);
						listeSalons.add(salon);
						//Logger.logMsg(Logger.INFO, salon.getNom()+salon.getId()+"Useun : " + listeSalons);
						
						
					}
				} catch (SQLException e) {
						e.printStackTrace();
				}
				for(int position = 0; position <listeSalons.size(); position++)
				{
					Salon salonR = listeSalons.get(position);			
					cache.set("salon/" + salonR.getId() + "/nom", salonR.getNom());
					
				}
				init = true;
	  }		
		List<Salon> listeSalon = new ArrayList<Salon>();
		int numero = 0;
		do
		{
			Salon salon = new Salon();
			numero++;
			String nomR = cache.get("salon/" + numero+ "/nom");
			if(null == nomR) break;
			salon.setNom(nomR);
			salon.setId(numero);
			listeSalon.add(salon);
			System.out.println(nomR);
		}
		while(true);
		return listeSalon;
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
