package com.healthcare.service;

import javax.ws.rs.Path;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.healthcare.model.Appointment;

@Path("/appointment")
public class AppointmentService {

	Appointment appObj = new Appointment();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readAppointment() {
		return appObj.readAppointment();
	}

	

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertAppointment(String appointmentData) {
		
		// Convert the input string to a JSON object
		JsonObject appObject = new JsonParser().parse(appointmentData).getAsJsonObject();

		// Read the values from the JSON object
		String aID = appObject.get("aID").getAsString();
		String pName = appObject.get("pName").getAsString();
		String dName = appObject.get("dName").getAsString();
		String hName = appObject.get("hName").getAsString();
		String roomNO = appObject.get("roomNO").getAsString();
		String appNO = appObject.get("appNO").getAsString();
		String aDate = appObject.get("aDate").getAsString();
		
		String output =  appObj.insertAppointment(aID, pName, dName, hName, roomNO, appNO, aDate);

		return output;
		
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateAppointment(String appointmentData) {
		// Convert the input string to a JSON object
		JsonObject appObject = new JsonParser().parse(appointmentData).getAsJsonObject();

		// Read the values from the JSON object
		String ano = appObject.get("ano").getAsString();
		String aID = appObject.get("aID").getAsString();
		String pName = appObject.get("pName").getAsString();
		String dName = appObject.get("dName").getAsString();
		String hName = appObject.get("hName").getAsString();
		String roomNO = appObject.get("roomNO").getAsString();
		String appNO = appObject.get("appNO").getAsString();
		String aDate = appObject.get("aDate").getAsString();
		
		String output =  appObj.updateAppointment(ano, aID, pName, dName, hName, roomNO, appNO, aDate);

		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteAppointment(String AppointmentData) 
	{ 
		JsonObject appObject = new JsonParser().parse(AppointmentData).getAsJsonObject();
		//Read the value from the element <ano> 
		String ano = appObject.get("ano").getAsString();
		String output = appObj.deleteAppointment(ano);

		return output;
	}
	
}
