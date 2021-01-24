package com.ead.ims.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;
//This class follows Singleton pattern to create single database connection
public class ConnectDatabase {

	 private static ConnectDatabase instance;
	    private static Connection connection;
	    private static final String URL = "jdbc:mysql://imsdb.cc5bkgl8jsbr.us-east-1.rds.amazonaws.com:3306/imsdb";
	    private static final String DATABASEDRIVER = "com.mysql.jdbc.Driver";
	    private static final String USERNAME = "admin";
	    private static final String PASSWORD = "admin1234";


	    private ConnectDatabase() throws SQLException {
	        try {
	            Class.forName(DATABASEDRIVER);
	            this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
	        } catch (ClassNotFoundException ex) {
	            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
	        }
	    }

	    //method to get database connection
	    public static Connection getConnection() {
	        return connection;
	    }

	    //static method to create instance of Singleton class
	    public static ConnectDatabase getInstance() throws SQLException {
	        if (instance == null) {
	            instance = new ConnectDatabase();
	        } else if (instance.getConnection().isClosed()) {
	            instance = new ConnectDatabase();
	        }
	        return instance;
	    }

	    //static method to close connection
	    public static void closeConnection() throws SQLException {
	        try {
	            System.out.println("----Connection closed with MYSQL database----");
	            connection.close();
	        }catch (Exception e)
	        {
	            System.out.println("Close connection failed  : " + e.getMessage());
	        }
	    }
	
}
