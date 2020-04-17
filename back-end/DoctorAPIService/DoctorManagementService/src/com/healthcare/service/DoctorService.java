package com.healthcare.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.healthcare.model.Doctor;


@Path("/doctor")
public class DoctorService {

	Doctor doctorObj = new Doctor();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readDoctor() {
		return doctorObj.readDoctor();
	}
		
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertDoctor(@FormParam("docID") String docID, 
			@FormParam("dName") String dName,
			@FormParam("address") String address,
			@FormParam("contactNumber") String contactNumber, 
			@FormParam("speciality") String speciality,
			@FormParam("description") String description,
			@FormParam("docCharges") String docCharges,
			@FormParam("visitingHospitals") String visitingHospitals) {
		String output = doctorObj.insertDoctor(docID, dName, address, contactNumber, speciality, description, docCharges, visitingHospitals);
		return output;
	}
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateDoctor(String doctorData) {
		// Convert the input string to a JSON object
		JsonObject doctorObject = new JsonParser().parse(doctorData).getAsJsonObject();

		// Read the values from the JSON object
		String docNo = doctorObject.get("docNo").getAsString();
		String docID = doctorObject.get("docID").getAsString();
		String dName = doctorObject.get("dName").getAsString();
		String address = doctorObject.get("address").getAsString();
		String contactNumber = doctorObject.get("contactNumber").getAsString();
		String speciality = doctorObject.get("speciality").getAsString();
		String description = doctorObject.get("description").getAsString();
		String docCharges = doctorObject.get("docCharges").getAsString();
		String visitingHospitals = doctorObject.get("visitingHospitals").getAsString();

		String output =  doctorObj.updateDoctor(docNo, docID, dName, address, contactNumber, speciality, description, docCharges, visitingHospitals);

		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteDoctor(String doctorData) 
	{ // Convert the input string to XML document 
		Document doc = Jsoup.parse(doctorData, "",Parser.xmlParser()); 
		//Read the value from the element <docNo> 
		String docNo =doc.select("docNo").text();

		String output = doctorObj.deleteDoctor(docNo);

		return output;
	}	
}
