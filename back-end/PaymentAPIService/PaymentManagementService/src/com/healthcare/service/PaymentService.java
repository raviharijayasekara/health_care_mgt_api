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
import com.healthcare.model.Payment;

@Path("/payment")
public class PaymentService {
	
	Payment payObj = new Payment();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPayment() {
		return payObj.readPayment();
	}
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPayment(String paymentData) {
		// Convert the input string to a JSON object
		JsonObject payObject = new JsonParser().parse(paymentData).getAsJsonObject();

		// Read the values from the JSON object
		
		String pID = payObject.get("pID").getAsString();
		String pName = payObject.get("pName").getAsString();
		String dName = payObject.get("dName").getAsString();
		String hName = payObject.get("hName").getAsString();
		String pDate = payObject.get("pDate").getAsString();
		String docCharge = payObject.get("docCharge").getAsString();
		String hosCharge = payObject.get("hosCharge").getAsString();
		String total = payObject.get("total").getAsString();
		
		String output =  payObj.insertPayment(pID, pName, dName, hName, pDate, docCharge, hosCharge, total);

		return output;
	}
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePayment(String paymentData) {
		// Convert the input string to a JSON object
		JsonObject payObject = new JsonParser().parse(paymentData).getAsJsonObject();

		// Read the values from the JSON object
		String pno = payObject.get("pno").getAsString();
		String pID = payObject.get("pID").getAsString();
		String pName = payObject.get("pName").getAsString();
		String dName = payObject.get("dName").getAsString();
		String hName = payObject.get("hName").getAsString();
		String pDate = payObject.get("pDate").getAsString();
		String docCharge = payObject.get("docCharge").getAsString();
		String hosCharge = payObject.get("hosCharge").getAsString();
		String total = payObject.get("total").getAsString();
		
		String output =  payObj.updatePayment(pno, pID, pName, dName, hName, pDate, docCharge, hosCharge, total);

		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePayment(String paymentData) 
	{ 
		JsonObject payObject = new JsonParser().parse(paymentData).getAsJsonObject();
		//Read the value from the element <pno> 
		String pno = payObject.get("pno").getAsString();
		String output = payObj.deletePayment(pno);
		
		return output;
	}
}

	
