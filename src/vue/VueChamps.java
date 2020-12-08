package vue;
import java.util.List;

import com.sun.media.jfxmedia.logging.Logger;

import controleur.ControleurChamps;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class VueChamps extends Vue {

	protected ControleurChamps controleur;
	protected static VueChamps instance = null; 
	public static VueChamps getInstance() {if(null==instance)instance = new VueChamps();return VueChamps.instance;}; 
	
	private VueChamps() 
	{
		super("champs.fxml"); 
		super.controleur = this.controleur = new ControleurChamps();
		Logger.logMsg(Logger.INFO, "new VueChamps()");
	}
		
	public void activerControles()
	{
		super.activerControles();
	}
	
	public void afficherChamps(List<modele.Champ> champs)
	{
		int position = 1;
		for(modele.Champ champ : champs)
		{
			Button libelle = (Button)lookup("#categorie-" + position);
			System.out.println(champ.getNom());
			libelle.setText(champ.getNom());
			libelle.setId(champ.getId()+""); // l'id est changé mais on n'a plus besoin de recuperer l'objet
			
			libelle.setOnAction(new EventHandler<ActionEvent>() 
			{
	            @Override public void handle(ActionEvent e) 
	            {
	            	Logger.logMsg(Logger.INFO, "Bouton Champ active");
	            	Button bouton = (Button)e.getSource();
	            	controleur.actionOuvrirChamp(Integer.parseInt(bouton.getId()));
	            }
	        });

			position++;
			if(position > 8) break;
		}
		
		
	}
	
	
}
