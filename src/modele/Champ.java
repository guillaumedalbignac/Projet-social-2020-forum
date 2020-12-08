package modele;

public class Champ {
	
	protected int id;
	protected String nom;
	protected String distanceFerme;
	protected int taille;
	protected String fertiliteSol;
	
	public String getDistanceFerme() {
		return distanceFerme;
	}
	public void setDistanceFerme(String distanceFerme) {
		this.distanceFerme = distanceFerme;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getTaille() {
		return taille;
	}
	public void setTaille(int taille) {
		this.taille = taille;
	}
	
	public String getFertiliteSol() {
		return fertiliteSol;
	}
	public void setFertiliteSol(String fertiliteSol) {
		this.fertiliteSol = fertiliteSol;
	}

}
