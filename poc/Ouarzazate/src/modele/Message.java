package modele;

public class Message {

	protected int id;
	protected String texteDuMessage;
	protected String dateMessage;
	protected String pseudo;
	protected int salon_id;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getTexteDuMessage() {
		return texteDuMessage;
	}
	public void setTexteDuMessage(String texteDuMessage) {
		this.texteDuMessage = texteDuMessage;
	}

	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getDateMessage() {
		return dateMessage;
	}
	public void setDateMessage(String dateMessage) {
		this.dateMessage = dateMessage;
	}
	
	public int getSalonId() {
		return salon_id;
	}
	public void setSalonId(int salonId) {
		this.salon_id = salonId;
	}

}
