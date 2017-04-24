package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Pattern Singleton

public class DatabaseManager {
	
	private static DatabaseManager manager = new DatabaseManager();
	
	private Connection connection;
	
	private DatabaseManager(){
		try {
		    Class.forName( "com.mysql.jdbc.Driver" );
		} catch ( ClassNotFoundException e ) {

		}
		String url = "jdbc:mysql://localhost:3306/myjeedb";

		String utilisateur = "appjee";

		String motDePasse = "appjee";
		
		try {
			connection = DriverManager.getConnection( url, utilisateur, motDePasse );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void close(){
		if(connection != null){
			try {
				connection.close();
	        } catch ( Exception e ) {
	            e.printStackTrace();
	        }
		}
	}
	
	public Connection getConnection(){
		return connection;
	}
	
	public static DatabaseManager getInstance(){
		return manager;
	}
}
