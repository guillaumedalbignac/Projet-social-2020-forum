import redis.clients.jedis.Jedis;
import vue.Fenetre;

public class App {

	public static void main(String[] parametres) {
		Fenetre.launch(Fenetre.class, parametres);	
		
		Jedis cache = new Jedis("localhost");
		
		cache.set("mouton/1/nom","Dolly");
		String nomMouton1 = cache.get("mouton/1/nom");
		System.out.println("nom mouton 1 : " + nomMouton1);
		
		cache.close();
	}

}
