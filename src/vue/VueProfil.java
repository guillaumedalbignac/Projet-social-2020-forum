package vue;

import com.sun.media.jfxmedia.logging.Logger;

import controleur.ControleurProfil;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import modele.Utilisateur;
import donnee.UtilisateurDAO;

public class VueProfil extends Vue{
	protected UtilisateurDAO utilisateurDAO;
	protected Utilisateur utilisateur;
	protected static VueProfil instance;
	public static VueProfil getInstance() {if(null == instance) instance = new VueProfil("simon.delarue2@gmail.com"); return instance;};
	protected ControleurProfil controleur = null;
	public ControleurProfil getControleur() {
		return this.controleur;
	}
	
	protected TextField caseEmail;
	protected TextField casePseudo;
	protected TextField caseBio;
	protected TextField caseAge;
	
	public VueProfil(String email) {
		super("profil.fxml");
		Logger.logMsg(Logger.INFO, "new VueProfil()");
		super.controleur = this.controleur = new ControleurProfil();
		utilisateurDAO = new UtilisateurDAO();
		utilisateur = utilisateurDAO.detaillerUtilisateur(email);
		caseEmail = (TextField)lookup("#email");
		casePseudo = (TextField)lookup("#pseudo");
		caseBio = (TextField)lookup("#biographie");
		caseAge = (TextField)lookup("#age");
		afficherProfil(utilisateur);
	}
	
	public void afficherProfil(Utilisateur utilisateur) 
	{
		caseEmail.setText(utilisateur.getEmail());
		casePseudo.setText(utilisateur.getPseudo());
		caseBio.setText(utilisateur.getBio());
		caseAge.setText(utilisateur.getAge()+"");
	}
	
	public void activerControles()
	{
		super.activerControles();
	
		Button actionRetour = (Button)lookup("#action-retour");
		actionRetour.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				controleur.retourAuMenu();
			}});
		Button actionEnregistrer = (Button)lookup("#action-enregistrer");
		actionEnregistrer.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				utilisateur.setPseudo(casePseudo.getText());
				utilisateur.setBio(caseBio.getText());
				utilisateur.setAge(Integer.parseInt(caseAge.getText()));
				utilisateurDAO.editerUtilisateur(utilisateur);
			}});
	}
	
}

