package com.healthcare.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.xml.bind.annotation.XmlRootElement;

import com.healthcare.util.DbConnection;


public class Doctor {
	
	// public String docID;
	// public String dName;
	// public String address;
	// public String contactNumber;
	// public String speciality;
	// public String description;
	// public String docCharges;
	// public String visitingHospitals;

	// insert

	public String insertDoctor(String docID, String dName, String address, String contactNumber, String speciality,
			String description, String docCharges, String visitingHospitals) {
		String output = "";
		try {
			DbConnection con = new DbConnection();
			Connection conn = con.connect();

			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}

			// create a prepared statement
			String query = " insert into doctor (`docNo`,`docID`,`dName`,`address`,`contactNumber`,`speciality`,`description`,`docCharges`,`visitingHospitals`)"
					+ " values (?, ?, ?, ?, ?, ?,?,?,?)";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, docID);
			preparedStmt.setString(3, dName);
			preparedStmt.setString(4, address);
			preparedStmt.setString(5, contactNumber);
			preparedStmt.setString(6, speciality);
			preparedStmt.setString(7, description);
			preparedStmt.setDouble(8, Double.parseDouble(docCharges));
			preparedStmt.setString(9, visitingHospitals);

			// execute the statement
			preparedStmt.execute();
			conn.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the doctor.";
			System.err.println(e.getMessage());
		}
		return output;
	}

//view

	public String readDoctor() {
		
		System.out.println("HELLO WORLD");
		String output = "";
		try {
			DbConnection con = new DbConnection();
			Connection conn = con.connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// Prepare the html table to be displayed

			output = "<table border=\"1\"><tr><th>Doctor Id</th><th>Doctor Name</th><th>Address</th><th>ContactNo</th><th>Speciality</th><th>Description</th><th>Doctor Charger</th><th>Visiting Hospital</th></tr>";
			String query = "select * from doctor";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {
				String docNo = Integer.toString(rs.getInt("docNo"));
				String docID = rs.getString("docID");
				String dName = rs.getString("dName");
				String address = rs.getString("address");
				String contactNumber = rs.getString("contactNumber");
				String speciality = rs.getString("speciality");
				String description = rs.getString("description");
				String docCharges = Double.toString(rs.getDouble("docCharges"));
				String visitingHospitals = rs.getString("visitingHospitals");

				// Add into the html table
				output += "<tr><td>" + docID + "</td>";
				output += "<td>" + dName + "</td>";
				output += "<td>" + address + "</td>";
				output += "<td>" + contactNumber + "</td>";
				output += "<td>" + speciality + "</td>";
				output += "<td>" + description + "</td>";
				output += "<td>" + docCharges + "</td>";
				output += "<td>" + visitingHospitals + "</td>";
				// buttons

			}
			conn.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the doctors.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	// update

	public String updateDoctor(String docNo, String docID, String dName, String address, String contactNumber,
			String speciality, String description, String docCharges, String visitingHospitals) {
		String output = "";
		try {
			DbConnection con = new DbConnection();
			Connection conn = con.connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE doctor SET docID=?,dName=?, address=?,contactNumber=?,speciality=?, description=?,docCharges=?,visitingHospitals=? WHERE docNo=?";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, docID);
			preparedStmt.setString(2, dName);
			preparedStmt.setString(3, address);
			preparedStmt.setString(4, contactNumber);
			preparedStmt.setString(5, speciality);
			preparedStmt.setString(6, description);
			preparedStmt.setDouble(7, Double.parseDouble(docCharges));
			preparedStmt.setString(8, visitingHospitals);
			preparedStmt.setInt(9, Integer.parseInt(docNo));

			// execute the statement
			preparedStmt.execute();
			conn.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the doctor.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	// delete

	public String deleteDoctor(String docNo) {
		String output = "";
		try {
			DbConnection con = new DbConnection();
			Connection conn = con.connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from doctor where docNo=?";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(docNo));
			// execute the statement
			preparedStmt.execute();
			conn.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the doctor.";
			System.err.println(e.getMessage());
		}
		return output;
	}

}
