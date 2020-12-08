package vue;

import com.sun.media.jfxmedia.logging.Logger;

import controleur.ControleurChamp;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import modele.Semence;

public class VueAjouterSemence extends Vue{

	protected ControleurChamp controleur;
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
	
	public Semence lireSemence() {
		Semence semence = new Semence();
		TextField typeSemence = (TextField)lookup("#type-semence");
		TextField dateSemence = (TextField)lookup("#date-semence");
		semence.setTypeSemence(typeSemence.getText());
		semence.setDatePlantation(dateSemence.getText());
		return semence;
		
	}
	
	
	
	
}

