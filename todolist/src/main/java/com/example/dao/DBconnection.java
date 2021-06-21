package com.example.dao;

import java.sql.*;

public class DBconnection {
private Connection connection;

DBconnection(){
	try {
		loadJdbcDriver();
		setupConnection();
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}
}


public void loadJdbcDriver() throws ClassNotFoundException {
    Class.forName("com.mysql.jdbc.Driver");
}
public void setupConnection() throws SQLException {
	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/todolist", "root", "root");
}


public Connection getConnection() {
	return connection;
}


public void setConnection(Connection connection) {
	this.connection = connection;
}
}
