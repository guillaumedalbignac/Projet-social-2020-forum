package vue;
import java.util.List;

import com.sun.media.jfxmedia.logging.Logger;

import controleur.ControleurSalons;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import modele.Salon;

public class VueSalons extends Vue {

	protected ControleurSalons controleur;
	protected static VueSalons instance = null; 
	public static VueSalons getInstance() {if(null==instance)instance = new VueSalons();return VueSalons.instance;}; 
	
	private VueSalons() 
	{
		super("salons.fxml"); 
		super.controleur = this.controleur = new ControleurSalons();
		Logger.logMsg(Logger.INFO, "new VueSalons()");
	}
		
	public void activerControles()
	{
		super.activerControles();
	}
	
	public void afficherSalons(List<Salon> champs)
	{
		int position = 1;
		for(Salon champ : champs)
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
