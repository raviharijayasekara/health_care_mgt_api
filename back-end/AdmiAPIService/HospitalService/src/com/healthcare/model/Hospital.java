package com.healthcare.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.healthcare.util.DbConnection;


public class Hospital {

	public String insertHospital(String hid, String hname, String hlocation, String htelno,String hhosch) {
		String output = "";
		try {
			DbConnection con = new DbConnection();
			Connection conn = con.connect();
			
			if (con == null) 
			{ return "Error while connecting to the database for inserting.";}
			
			// create a prepared statement
			String query = " insert into Hospital (`hno`,`hid`,`hname`,`hlocation`,`htelno`,`hhosch`)" + " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, hid);
			preparedStmt.setString(3, hname);
			preparedStmt.setString(4, hlocation);
			preparedStmt.setString(5, htelno);
			preparedStmt.setDouble(6, Double.parseDouble(hhosch));
            //execute the statement
			preparedStmt.execute();
			conn.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the hospital.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readHospital() {
		String output = "";
		try {
			DbConnection con = new DbConnection();
			Connection conn = con.connect();
			if (con == null) 
			{ return "Error while connecting to the database for reading."; }
            // Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Hospital Id</th><th>Hospital Name</th><th>Hospital Location</th><th>Hospital Telno</th><th>Hospital Charge</th></tr>";
			String query = "select * from Hospital";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
// iterate through the rows in the result set
			while (rs.next()) {
				String hno = Integer.toString(rs.getInt("hno"));
				String hid = rs.getString("hid");
				String hname = rs.getString("hname");
				String hlocation = rs.getString("hlocation");
				String htelno = rs.getString("htelno");
				String hhosch = Double.toString(rs.getDouble("hhosch"));
				
// Add into the html table
				output += "<tr><td>" + hid + "</td>";
				output += "<td>" + hname + "</td>";
				output += "<td>" + hlocation + "</td>";
				output += "<td>" + htelno + "</td>";
				output += "<td>" + hhosch + "</td>";
// buttons
				
			}
			conn.close();
// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the hospitals.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateHospital(String hno, String hid, String hname, String hlocation, String htelno,String hhosch) {
		String output = "";
		try {
			DbConnection con = new DbConnection();
			Connection conn = con.connect();
			if (con == null) 
			{ return "Error while connecting to the database for updating."; }
// create a prepared statement
			String query = "UPDATE Hospital SET hid=?,hname=?, hlocation=?,htelno=?,hhosch=? WHERE hno=?";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
// binding values
			preparedStmt.setString(1, hid);
			preparedStmt.setString(2, hname);
			preparedStmt.setString(3, hlocation);
			preparedStmt.setString(4, htelno);
			preparedStmt.setDouble(5, Double.parseDouble(hhosch));
			preparedStmt.setInt(6, Integer.parseInt(hno));
// execute the statement
			preparedStmt.execute();
			conn.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the hospital.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteHospital(String hno) {
		String output = "";
		try {
			DbConnection con = new DbConnection();
			Connection conn = con.connect();
			if (con == null) 
			{ return "Error while connecting to the database for deleting."; }
// create a prepared statement
			String query = "delete from Hospital where hno=?";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
// binding values
			preparedStmt.setInt(1, Integer.parseInt(hno));
// execute the statement
			preparedStmt.execute();
			conn.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the hospital.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}