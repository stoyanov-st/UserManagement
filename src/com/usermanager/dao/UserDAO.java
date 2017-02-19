package com.usermanager.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.usermanager.model.User;


public class UserDAO {
	private final String DRIVER = "com.mysql.jdbc.Driver";
	private final String HOST = "jdbc:mysql://localhost:3306/usermanagement";
	private final String USER = "root";
	private final String PASSWORD = "";
	private Connection connection;
	private PreparedStatement statement;
	private ResultSet resultSet;
	
	protected void dbConnect() throws SQLException {
		if (connection == null || connection.isClosed()) {
			 try{
					Class.forName(DRIVER); 	 	
				}
			    catch (ClassNotFoundException e){
			        System.out.println("Can't load class:"+e.getMessage());
			    }
			 
			 connection = DriverManager.getConnection(HOST, USER, PASSWORD);
		}
	}
	
	protected void dbDisconnect() throws SQLException {
		if (connection != null && !connection.isClosed()) {
			connection.close();
		}
	}
	
	public void createUser(User user) throws SQLException {
		dbConnect();
		
		String query = "INSERT INTO users (firstname, lastname, dateofbirth, phonenumber, email) VALUES (?, ?, ?, ?, ?)";
		statement = connection.prepareStatement(query);
		statement.setString(1, user.getFirstName());
		statement.setString(2, user.getLastName());
		statement.setDate(3, user.getDateOfBirth());
		statement.setString(4, user.getPhoneNumber());
		statement.setString(5, user.getEmail());
		statement.executeUpdate();
		statement.close();
		
		dbDisconnect();
	}
	
	public List<User> getUsers() throws SQLException{
		List<User> allUsersList = new ArrayList<>();
		
		dbConnect();
		
		statement = connection.prepareStatement("SELECT * FROM users");
		resultSet = statement.executeQuery();
		while (resultSet.next()) {
			User user = new User();
			user.setUserId(resultSet.getInt("id"));
			user.setFirstName(resultSet.getString("firstname"));
			user.setLastName(resultSet.getString("lastname"));
			user.setDateOfBirth(resultSet.getString("dateofbirth"));
			user.setPhoneNumber(resultSet.getString("phonenumber"));
			user.setEmail(resultSet.getString("email"));
			allUsersList.add(user);
		}
		
		dbDisconnect();
		
		return allUsersList;
	}
	
	public User getUserById(int userId) throws SQLException {
		User user = new User();
		
		dbConnect();
		
		String query = "SELECT * FROM `users` WHERE id = ?";
		
		statement = connection.prepareStatement(query);
		statement.setInt(1, userId);
		
		resultSet = statement.executeQuery();
				
		if (resultSet.next()) {
			user.setUserId(resultSet.getInt("id"));
			user.setFirstName(resultSet.getString("firstname"));
			user.setLastName(resultSet.getString("lastname"));
			user.setDateOfBirth(resultSet.getString("dateofbirth"));
			user.setPhoneNumber(resultSet.getString("phonenumber"));
			user.setEmail(resultSet.getString("email"));
		}
		
		dbDisconnect();
		
		return user;
	}
	
	public List<User> searchUser(String searchValue) throws SQLException {
		List<User> foundUsersList = new ArrayList<>();
		
		dbConnect();
			
		String query = "SELECT * FROM users WHERE firstname LIKE ? OR lastname LIKE ? OR email LIKE ?";
		statement = connection.prepareStatement(query);
		statement.setString(1, "%" + searchValue + "%");
		statement.setString(2, "%" + searchValue + "%");
		statement.setString(3, "%" + searchValue + "%");
		
		resultSet = statement.executeQuery();
		
		while (resultSet.next()) {
			User user = new User();
			user.setUserId(resultSet.getInt("id"));
			user.setFirstName(resultSet.getString("firstname"));
			user.setLastName(resultSet.getString("lastname"));
			user.setDateOfBirth(resultSet.getString("dateofbirth"));
			user.setPhoneNumber(resultSet.getString("phonenumber"));
			user.setEmail(resultSet.getString("email"));
			foundUsersList.add(user);
		}
		
		dbDisconnect();
		
		return foundUsersList;
	}
	
	public void editUser(User user) throws SQLException{
		dbConnect();
		
		String query = "UPDATE users SET firstname = ? , lastname = ? , dateofbirth = ? , phonenumber = ? , email = ? WHERE id = ?";
		
		statement = connection.prepareStatement(query);
		statement.setString(1, user.getFirstName());
		statement.setString(2, user.getLastName());
		statement.setDate(3, user.getDateOfBirth());
		statement.setString(4, user.getPhoneNumber());
		statement.setString(5, user.getEmail());
		statement.setInt(6, user.getUserId());
		
		statement.executeUpdate();
		statement.close();
		
		dbDisconnect();
	}
	
	public void deleteUser(int userId) throws SQLException {
		dbConnect();
		
		String query = "DELETE FROM users WHERE id = ?";
		
		statement = connection.prepareStatement(query);
		statement.setInt(1, userId);
		
		statement.executeUpdate();
		statement.close();
						
		dbDisconnect();
	}
	
}
