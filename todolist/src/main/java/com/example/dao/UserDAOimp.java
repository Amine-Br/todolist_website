package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.models.User;

public class UserDAOimp implements UserDAO{
	//a final variable can only be assigned once. And static fields of a class are initialized when the class is loaded
	private static final String SELECT_USER_BY_EMAIL="select id, email, name, password from users where email =?";
	private static final String SELECT_USER_BY_EMAIL_AND_PASSWORD="select id, email, name, password from users where email =? AND password=?";
	private static final String INSERT_USER="INSERT INTO users(email, name, password) VALUES(?, ?, ?)";
	private static final String UPDATE_USER_BY_ID="Update users set email = ?,name= ?,password= ? where id= ?";
	
	
	DBconnection db=new DBconnection();
	@Override
	public User selectuserbyemail(String email) {
		User user=null;
		try {
			Connection connection = db.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_EMAIL);
			preparedStatement.setString(1, email);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id=rs.getInt("id");
				String name = rs.getString("name");
				String password = rs.getString("password");
				user = new User();
                user.setId(id);
                user.setName(name);
                user.setEmail(email);
                user.setPassword(password);
			}
		} catch (SQLException e) {
        	e.printStackTrace();
		}
		return user;
	}

	@Override
	public boolean login(User user) {
		boolean result=false;
		try {			
			Connection connection = db.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_EMAIL_AND_PASSWORD);
			preparedStatement.setString(1, user.getEmail());
			preparedStatement.setString(2, user.getPassword());
			ResultSet rs = preparedStatement.executeQuery();
			//rs.next return true or false and we know rs has only one or zero results;
			result=rs.next();
		} catch (SQLException e) {
        	e.printStackTrace();
		}
		return result;
	}

	@Override
	public void adduser(User user) {
		try {			
			Connection connection = db.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER);
			preparedStatement.setString(1, user.getEmail());
			preparedStatement.setString(2, user.getName());
			preparedStatement.setString(3, user.getPassword());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
        	e.printStackTrace();
		}		
	}

	@Override
	public void updateuserbyid(User user, int id) {
		try {			
			Connection connection = db.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_BY_ID);
			preparedStatement.setString(1, user.getEmail());
			preparedStatement.setString(2, user.getName());
			preparedStatement.setString(3, user.getPassword());
			preparedStatement.setInt(4, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
        	e.printStackTrace();
		}			
	}

}
