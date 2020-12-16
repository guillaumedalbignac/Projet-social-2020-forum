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
	
	public Utilisateur detaillerUtilisateur(String email)
	{
		Connection connection = BaseDeDonnees.getInstance().getConnection();
		Utilisateur utilisateur = new Utilisateur();	
		PreparedStatement requeteUtilisateur;
		try {
			requeteUtilisateur = connection.prepareStatement("SELECT * FROM utilisateur WHERE email = ?");
			requeteUtilisateur.setString(1, email);
			ResultSet curseur = requeteUtilisateur.executeQuery();
			curseur.next();
			String pseudo = curseur.getString("pseudo");
			String bio = curseur.getString("bio");
			int age = curseur.getInt("age");
			utilisateur.setAge(age);
			utilisateur.setBio(bio);
			utilisateur.setEmail(email);
			utilisateur.setPseudo(pseudo);
			
		} 
		catch (SQLException e) {
				e.printStackTrace();
		}
		
		return utilisateur;
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
