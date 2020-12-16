package vue;

import java.util.ArrayList;
import java.util.List;

import com.sun.media.jfxmedia.logging.Logger;

import controleur.ControleurSalon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import modele.Message;

public class VueSalon extends Vue {

	
	protected static VueSalon instance = null; 
	public static VueSalon getInstance() 
	{
		if(null==instance)instance = new VueSalon();
		return VueSalon.instance;
	}
	protected ControleurSalon controleur = null;
	public ControleurSalon getControleur() {
		return this.controleur;
	}
	private VueSalon() 
	{
		super("salon.fxml"); 
		super.controleur = this.controleur = new ControleurSalon();
		Logger.logMsg(Logger.INFO, "new VueSalon()");
	}
		
	public void activerControles()
	{
		super.activerControles();
		Button actionNouveauMessage = (Button)lookup("#action-ajouter-message");
		
		actionNouveauMessage.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
			TextArea boiteNouveauMessage = (TextArea)lookup("#nouveau-message");
			controleur.notifierClicNouveauMessage(boiteNouveauMessage.getText());
			}});
		Button actionRetour = (Button)lookup("#menu-retour");
		
		actionRetour.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
			controleur.retourAuMenu();
			}});
	}
	
	public void intialiserSalon(modele.Salon salon)
	{
		/*TextArea affichageListeMessages = (TextArea)lookup("#nouveau-message");
		String messages;
		messages = "Distance entre la ferme et le champ : "+champ.getDistanceFerme()+"\n";
		messages += "Fertilité du sol : "+champ.getFertiliteSol()+"\n";
		messages += "Taille du champ : "+champ.getTaille()+" m²";
		affichageListeMessages.setText(messages);*/

		Label affichageTitre = (Label)lookup("#titre-page");
		affichageTitre.setText("Salon " + salon.getNom());
		
		
	}
	public void afficherMessages(List<Message> listeMessages)
	{
		
		Logger.logMsg(Logger.INFO, "VueChamp.afficherMessages()");
		/*ListView affichageListeSemences = (ListView)lookup("#liste-semences"); ancien code pour afficher dans un ListView
		ArrayList<String> listeNomSemences= new ArrayList<String>(); */
		VBox vueSemences = (VBox)lookup("#liste-messages");
		vueSemences.getChildren().clear();
		Button action;
		
		for (Message message: listeMessages) {
			
			action = new Button("profil");
			action.setId(message.getId()+"");
			action.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {
				Button source = (Button)e.getSource();
				Logger.logMsg(Logger.INFO, "Clic sur le message avec l'id : " + source.getId());
				controleur.notifierClicAfficherProfil(Integer.parseInt(source.getId()));
				}});
		
			Logger.logMsg(Logger.INFO, "Semence : "+ message.getTexteDuMessage());
			HBox vueSemence = new HBox();
			vueSemence.getStyleClass().add("semence");
			vueSemence.getChildren().add(new Label(message.getTexteDuMessage()));
			vueSemence.getChildren().add(action);
			vueSemences.getChildren().add(vueSemence);
			//listeNomSemences.add(semence.getTypeSemence());
		}
		
		//affichageListeSemences.setItems(FXCollections.observableArrayList(listeNomSemences));
		
		
	}
}
