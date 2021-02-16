package donnee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modele.Salon;
import modele.Utilisateur;

public class UtilisateurDAO {
	
	List<Utilisateur> listeUtilisateurs;
	
	public UtilisateurDAO() {
		initialiserListeUtilisateurs();
	}
	
	private void initialiserListeUtilisateurs() {
		Connection connection = BaseDeDonnees.getInstance().getConnection();
		
		listeUtilisateurs =  new ArrayList<Utilisateur>();			
		PreparedStatement requeteUtilisateurs;
		try {
			requeteUtilisateurs = connection.prepareStatement("SELECT * FROM utilisateur");
			ResultSet curseurListeUtilisateurs = requeteUtilisateurs.executeQuery();
			System.out.println("Query : " + curseurListeUtilisateurs);
			
			while(curseurListeUtilisateurs.next())
			{
				//String pseudo = curseurListeUtilisateurs.getString("pseudo");
				String pseudo = "test";
				int age = curseurListeUtilisateurs.getInt("age");							
				String bio = curseurListeUtilisateurs.getString("bio");
				String email = curseurListeUtilisateurs.getString("email");	

				listeUtilisateurs.add(new Utilisateur(email, pseudo, bio, age));
			}
		} catch (SQLException e) {
				e.printStackTrace();
		}
	}
	
	public Utilisateur detaillerUtilisateur(String email)
	{
		Utilisateur bonUtilisateur = new Utilisateur();
		for (Utilisateur utilisateur: listeUtilisateurs) {
			if (utilisateur.getEmail().equals(email)) {
				bonUtilisateur = utilisateur;
			}
		}
		return bonUtilisateur;
	}
	
	
	
	public List<Utilisateur> listerUtilisateurs()
	{
		Connection connection = BaseDeDonnees.getInstance().getConnection();
		
		List<Utilisateur> listeUtilisateurs =  new ArrayList<Utilisateur>();			
		PreparedStatement requeteUtilisateurs;
		try {
			requeteUtilisateurs = connection.prepareStatement("SELECT * FROM utilisateur");
			ResultSet curseurListeUtilisateurs = requeteUtilisateurs.executeQuery();
			
			while(curseurListeUtilisateurs.next())
			{
				String email = curseurListeUtilisateurs.getString("email");
				String pseudo = curseurListeUtilisateurs.getString("pseudo");
				String bio = curseurListeUtilisateurs.getString("bio");
				int age = curseurListeUtilisateurs.getInt("age");

				listeUtilisateurs.add(new Utilisateur(email, pseudo, bio, age));
			}
		} catch (SQLException e) {
				e.printStackTrace();
		}
		
		return listeUtilisateurs;
	}
	
	public void ajouterUtilisateur(Utilisateur utilisateur)
	{
		Connection connection = BaseDeDonnees.getInstance().getConnection();
		try {
			PreparedStatement requeteAjouterUtilisateur = connection.prepareStatement("insert into utilisateur(email,pseudo,bio,age) VALUES(?,?,?,?)");
			requeteAjouterUtilisateur.setString(1, utilisateur.getEmail());
			requeteAjouterUtilisateur.setString(2, utilisateur.getPseudo());
			requeteAjouterUtilisateur.setString(3, utilisateur.getBio());
			requeteAjouterUtilisateur.setInt(4, utilisateur.getAge());
			requeteAjouterUtilisateur.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void editerUtilisateur(Utilisateur utilisateur) 
	{
		Connection connection = BaseDeDonnees.getInstance().getConnection();
		try {
			PreparedStatement requeteModifierUtilisateur = connection.prepareStatement("UPDATE utilisateur SET pseudo = ?, bio = ?, age = ? WHERE email = ?");
			requeteModifierUtilisateur.setString(4, utilisateur.getEmail());
			requeteModifierUtilisateur.setString(1, utilisateur.getPseudo());
			requeteModifierUtilisateur.setString(2, utilisateur.getBio());
			requeteModifierUtilisateur.setInt(3, utilisateur.getAge());
			requeteModifierUtilisateur.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
