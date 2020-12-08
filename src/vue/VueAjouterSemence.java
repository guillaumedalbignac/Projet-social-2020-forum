package vue;

import com.sun.media.jfxmedia.logging.Logger;

import controleur.ControleurSalon;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import modele.Message;

public class VueAjouterSemence extends Vue{

	protected ControleurSalon controleur;
	protected static VueAjouterSemence instance;
	public static VueAjouterSemence getInstance() {if(null == instance) instance = new VueAjouterSemence(); return instance;};
	public VueAjouterSemence() {
		super("ajouterSemence.fxml");
	}
	public void activerControles()
	{
		super.activerControles();
		
		Button actionEnregistrer = (Button)lookup("#action-enregistrer-semence");
		actionEnregistrer.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
			controleur.notifierClicEnregisterAjoutSemence();
			}});
	}
	
	public Message lireSemence() {
		Message semence = new Message();
		TextField typeSemence = (TextField)lookup("#type-semence");
		TextField dateSemence = (TextField)lookup("#date-semence");
		semence.setTexteDuMessage(typeSemence.getText());
		semence.setDateMessage(dateSemence.getText());
		return semence;
		
	}
	
	
	
	
}

