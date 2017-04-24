package database.dao;


import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

import database.DatabaseManager;
import model.User;

public class UserDao {
	private static UserDao dao = new UserDao();
	
	private DatabaseManager databaseManager = DatabaseManager.getInstance();
	
	private UserDao(){
		
	}
	
	public static UserDao getInstance(){
		return dao;
	}
	
	public void insertUser(User user){
		try {
			PreparedStatement statement = databaseManager.getConnection().prepareStatement("INSERT INTO user (nom, prenom, dnaissance, email, mdp) values (?,?,?,?,?)");
			statement.setString(1, user.getNom());
			statement.setString(2, user.getPrenom());
			Date d = null;
			if(user.getDnaissance() != null){
				d = new Date(user.getDnaissance().getTime());
			}
			statement.setDate(3, d);
			statement.setString(4, user.getEmail());
			statement.setString(5, user.getMdp());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean checkEmail(User user){
		PreparedStatement statement;
		try {
			statement = databaseManager.getConnection().prepareStatement("Select 1 From user where email = ?");
			statement.setString(1, user.getEmail());
			ResultSet result = statement.executeQuery();
			return !result.first();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
}
