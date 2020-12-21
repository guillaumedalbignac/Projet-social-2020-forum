package donnee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class BaseDeDonnees {
	Connection connection = null;

	private BaseDeDonnees()
	{
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:20820/slouke","postgres","nostenfer7");
			String url1 = "jdbc:postgresql://149.56.45.139:5432/slouke";
			
			//connection = DriverManager.getConnection(url1, "esteban", "motdepasse");
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
