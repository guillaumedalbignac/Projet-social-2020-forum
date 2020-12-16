package donnee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDeDonnees {
	private Connection connection = null;

	private BaseDeDonnees()
	{
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:20820/slouke", "postgres", "nostenfer7");
			//connection = DriverManager.getConnection("jdbc:postgresql://149.56.45.139:5432", "postgres", "password");
			//connection = DriverManager.getConnection("jdbc:postgresql://devoircapture.ddns.net", "postgres", "password");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// SINGLETON - DEBUT
	private static BaseDeDonnees instance = null;
	public static BaseDeDonnees getInstance()
	{
		if(null == instance) instance = new BaseDeDonnees();
		return instance;
	}
	// SINGLETON - FIN

	public Connection getConnection()
	{
		return this.connection;
	}

}
