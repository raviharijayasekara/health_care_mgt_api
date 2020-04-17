package com.healthcare.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;

import com.healthcare.util.DBConnection;

public class Appointment {
	
	public String insertAppointment(String aID, String pName, String dName, String hName, String roomNO, String appNO, String aDate) {
		String output = "";
		try {
			DBConnection con = new DBConnection();
			Connection conn = con.connect();
			
			if (con == null) 
			{ return "Error while connecting to the database for inserting.";}
			
			// create a prepared statement
			String query = " insert into appointment (`ano`,`aID`,`pName`,`dName`,`hName`,`roomNO`, `appNO`,`aDate`)" + " values (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, aID);
			preparedStmt.setString(3, pName);
			preparedStmt.setString(4, dName);
			preparedStmt.setString(5, hName);
			preparedStmt.setString(6, roomNO);
			preparedStmt.setString(7, appNO);
			String localDate = LocalDate.now().toString();
			preparedStmt.setObject(8 , aDate);
			
			
            //execute the statement
			preparedStmt.execute();
			conn.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the Appointment.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readAppointment() {
		String output = "";
		try {
			DBConnection con = new DBConnection();
			Connection conn = con.connect();
			if (con == null) 
			{ return "Error while connecting to the database for reading."; }
			
            // Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Appointment Id</th><th>Patient Name</th><th>Doctor Name</th><th>Hospital Name</th><th>Room No</th><th>Appointment Number</th><th>Date</th></tr>";
			String query = "select * from appointment";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next()) {
				String ano = Integer.toString(rs.getInt("ano"));
				String aID = rs.getString("aID");
				String pName = rs.getString("pName");
				String dName = rs.getString("dName");
				String hName = rs.getString("hName");
				String roomNO = rs.getString("roomNO");
				String appNO = rs.getString("appNO");
				String aDate = rs.getString("aDate");
				
				
				// Add into the html table
				output += "<tr><td>" + aID + "</td>";
				output += "<td>" + pName + "</td>";
				output += "<td>" + dName + "</td>";
				output += "<td>" + hName + "</td>";
				output += "<td>" + roomNO + "</td>";
				output += "<td>" + appNO + "</td>";
				output += "<td>" + aDate + "</td>";
				
				// buttons
			}
			conn.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the appointment.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateAppointment(String ano, String aID, String pName, String dName, String hName, String roomNO, String appNO, String aDate) {
		String output = "";
		try {
			DBConnection con = new DBConnection();
			Connection conn = con.connect();
			if (con == null) 
			{ return "Error while connecting to the database for updating."; }
			
			// create a prepared statement
			String query = "UPDATE appointment SET aID=?,pName=?, dName=?,hName=?,roomNO=?,appNO=?,aDate=? WHERE ano=?";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			
			// binding values
			preparedStmt.setString(1, aID);
			preparedStmt.setString(2, pName);
			preparedStmt.setString(3, dName);
			preparedStmt.setString(4, hName);
			preparedStmt.setString(5, roomNO);
			preparedStmt.setString(6, appNO);
			String localDate = LocalDate.now().toString();
			preparedStmt.setObject(7 , aDate);
			preparedStmt.setInt(8, Integer.parseInt(ano));
			
			// execute the statement
			preparedStmt.execute();
			conn.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the appointment.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteAppointment(String ano) {
		String output = "";
		try {
			DBConnection con = new DBConnection();
			Connection conn = con.connect();
			if (con == null) 
			{ return "Error while connecting to the database for deleting."; }
			
			// create a prepared statement
			String query = "delete from appointment where ano=?";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(ano));
			
			// execute the statement
			preparedStmt.execute();
			conn.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the Appointment.";
			System.err.println(e.getMessage());
		}
		return output;
	}



}
