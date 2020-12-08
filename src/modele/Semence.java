package modele;

public class Semence {
	
	protected int id;
	protected String typeSemence;
	protected String datePlantation;
	protected int champ_id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTypeSemence() {
		return typeSemence;
	}
	public void setTypeSemence(String typeSemence) {
		this.typeSemence = typeSemence;
	}
	
	
	public String getDatePlantation() {
		return datePlantation;
	}
	public void setDatePlantation(String datePlantation) {
		this.datePlantation = datePlantation;
	}
	
	public int getChampId() {
		return champ_id;
	}
	public void setChampId(int champId) {
		this.champ_id = champId;
	}

}
