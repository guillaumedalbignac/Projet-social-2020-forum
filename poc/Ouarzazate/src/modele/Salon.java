package modele;

public class Salon {

	protected int id;
	protected String nom;
/*
	public Salon(String xml, int mode)
	{
		
	}
	public String exporterXML()
	{
		return "";
	}
	public String exporterJSON()
	{
		return "";
	}
	public String serialiser()
	{
		return "";
	}*/
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


}
