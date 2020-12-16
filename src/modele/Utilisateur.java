package modele;

public class Utilisateur {
	
	protected String email;
	protected String pseudo;
	protected String bio;
	protected int age;

	public Utilisateur(String email, String pseudo, String bio, int age) {
		this.email = email;
		this.pseudo = pseudo;
		this.bio = bio;
		this.age = age;
	}

	public Utilisateur() {
		
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPseudo() {
		return this.pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getBio() {
		return this.bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
