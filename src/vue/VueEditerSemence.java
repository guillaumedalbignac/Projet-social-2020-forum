package vue;

import com.sun.media.jfxmedia.logging.Logger;

import controleur.ControleurChamp;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import modele.Semence;

public class VueEditerSemence extends Vue{

	protected ControleurChamp controleur;
	protected static VueEditerSemence instance;
	public static VueEditerSemence getInstance() {if(null == instance) instance = new VueEditerSemence(); return instance;};
	public VueEditerSemence() {
		super("editerSemence.fxml");
	}
	public void activerControles()
	{
		super.activerControles();
		
		Button actionEnregistrer = (Button)lookup("#action-enregistrer-semence");
		actionEnregistrer.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
			controleur.notifierClicEnregisterEditerSemence();
			}});
	}
	
	public Semence lireSemence() {
		Semence semence = new Semence();
		TextField caseTypeSemence = (TextField)lookup("#type-semence");
		TextField caseDateSemence = (TextField)lookup("#date-semence");
		semence.setTypeSemence(caseTypeSemence.getText());
		semence.setDatePlantation(caseDateSemence.getText());
		return semence;
		
	}
	
	public void afficherSemence(Semence semence) 
	{
		TextField caseTypeSemence = (TextField)lookup("#type-semence");
		TextField caseDateSemence = (TextField)lookup("#date-semence");
		caseTypeSemence.setText(semence.getTypeSemence());
		caseDateSemence.setText(semence.getDatePlantation());
		
		
	}
	
	
	
	
}

