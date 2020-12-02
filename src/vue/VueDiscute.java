package vue;
import com.sun.media.jfxmedia.logging.Logger;

import controleur.ControleurDiscute;

public class VueDiscute extends Vue {

	protected ControleurDiscute controleur;
	protected static VueDiscute instance = null; 
	public static VueDiscute getInstance() {if(null==instance)instance = new VueDiscute();return VueDiscute.instance;}; 
	
	private VueDiscute() 
	{
		super("discute.fxml"); 
		super.controleur = this.controleur = new ControleurDiscute();
		Logger.logMsg(Logger.INFO, "new VueDiscute()");
	}
		
	public void activerControles()
	{
		super.activerControles();
		
		/*
		Button actionCalculatrice = (Button) lookup("#action-calculatrice");
		actionCalculatrice.setOnAction(new EventHandler<ActionEvent>() 
		{
            @Override public void handle(ActionEvent e) 
            {
            	Logger.logMsg(Logger.INFO, "Bouton Calculatrice activé");
            	controleur.notifierEvenement(ActionNavigation.CALCULATRICE);
            }
        });
		*/

	}
}
