package com.healthcare.service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.google.gson.*;

import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

import com.healthcare.model.Hospital;

@Path("/hospital")

public class HospitalService {

		
		Hospital hosObj = new Hospital();
		
		@GET
		@Path("/")
		@Produces(MediaType.TEXT_HTML)
		public String readHospital() {
			return hosObj.readHospital();
		}
		
		
		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertHospital(String hospitalData) {
			
			JsonObject hosObject = new JsonParser().parse(hospitalData).getAsJsonObject();
			
			String hid = hosObject.get("hid").getAsString();
			String hname = hosObject.get("hname").getAsString();
			String hlocation = hosObject.get("hlocation").getAsString();
			String htelno = hosObject.get("htelno").getAsString();
			String hhosch = hosObject.get("hhosch").getAsString();

			String output =  hosObj.insertHospital( hid, hname, hlocation, htelno, hhosch);

			return output;
		}
		
		
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateHospital(String hospitalData) {
			// Convert the input string to a JSON object
			JsonObject hosObject = new JsonParser().parse(hospitalData).getAsJsonObject();

			// Read the values from the JSON object
			String hno = hosObject.get("hno").getAsString();
			String hid = hosObject.get("hid").getAsString();
			String hname = hosObject.get("hname").getAsString();
			String hlocation = hosObject.get("hlocation").getAsString();
			String htelno = hosObject.get("htelno").getAsString();
			String hhosch = hosObject.get("hhosch").getAsString();

			String output =  hosObj.updateHospital(hno, hid, hname, hlocation, htelno, hhosch);

			return output;
		}
		
		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String deleteHospital(String hospitalData) 
		{ 
			JsonObject hosObject = new JsonParser().parse(hospitalData).getAsJsonObject();
			
			String hno = hosObject.get("hno").getAsString();

			String output = hosObj.deleteHospital(hno);

			return output;
		}

		
		
		
	}

