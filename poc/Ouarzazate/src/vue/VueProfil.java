package vue;

import com.sun.media.jfxmedia.logging.Logger;

import controleur.ControleurSalon;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import modele.Message;

public class VueProfil extends Vue{
	protected int id = 0;
	protected ControleurSalon controleur;
	protected static VueProfil instance;
	public static VueProfil getInstance() {if(null == instance) instance = new VueProfil(); return instance;};
	public VueProfil() {
		super("profil.fxml");
	}
	
	public Message lireMessage() {
		Message semence = new Message();
		TextField caseTypeSemence = (TextField)lookup("#pseudo");
		TextField caseDateSemence = (TextField)lookup("#profil-description");
		semence.setTexteDuMessage(caseTypeSemence.getText());
		semence.setDateMessage(caseDateSemence.getText());
		return semence;
		
	}
	
	public void afficherSemence(Message semence) 
	{
		this.id = semence.getSalonId();
		TextField caseTypeSemence = (TextField)lookup("#pseudo");
		TextField caseDateSemence = (TextField)lookup("#profil-description");
		caseTypeSemence.setText(semence.getTexteDuMessage());
		caseDateSemence.setText(semence.getDateMessage());
		
		
	}
	public void activerControles()
	{
		super.activerControles();
		
		Button actionEnregistrer = (Button)lookup("#action-retour");
		actionEnregistrer.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
			controleur.notifierClicRetour();
			}});
	}
	
	
	
	
}

