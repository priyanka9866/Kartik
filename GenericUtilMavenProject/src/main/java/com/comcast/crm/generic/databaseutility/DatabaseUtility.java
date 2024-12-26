package com.comcast.crm.generic.databaseutility;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public class DatabaseUtility {

	Connection con;
	//To connect with 
	public void getDBConnection(String url, String username, String password) {
		try {
			Driver driver = new Driver();
		    DriverManager.registerDriver(driver);
			con = DriverManager.getConnection(url, username, password);
		} 
		catch (Exception e) {
		}	
	}
	
    public void closeDBConnection() {
		try {
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	 public ResultSet executeSelectQuery(String query)
	 {
		 ResultSet result = null ;
		 try 
		 { 
			 Statement statement = con.createStatement(); 
			 statement.exe(query); 
		 }
		 catch(Exception e) { 
			 }
		 return result;
		 }
	 
    
    public int executeNonSelectQuery(String query) {
    	int result = 0 ; 
    	try {
			Statement stat = con.createStatement();
			stat.executeUpdate(query);
		} catch (Exception e) {
			// TODO: handle exception
		}
    	return result;
	}
    
    
}
