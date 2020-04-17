package com.healthcare.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	public Connection connect()
	{
		Connection conn = null;

	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");
	 conn= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcare_system","root", "");
	 //For testing
	 System.out.print("Successfully connected");
	 }
	 catch(Exception e)
	 {
	 e.printStackTrace();
	 }

	 return conn; 
	 
	}


}
