package com.healthcare.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.healthcare.controller.PatientController;
import com.healthcare.model.Patient;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;



	@Path("/Patients")
public class PatientService {

		PatientController patientcontroller = new PatientController();
		Patient patient = new Patient();

		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String registerPatient(String patientData) {
			
				JsonObject pObj = new JsonParser().parse(patientData).getAsJsonObject();
				
				String pid = pObj.get("pid").getAsString();
				String fName = pObj.get("fName").getAsString();
				String lName = pObj.get("lName").getAsString();
				String nic = pObj.get("nic").getAsString();
				String dob = pObj.get("dob").getAsString();
				String phone = pObj.get("phone").getAsString();
				String email = pObj.get("email").getAsString();
				String gender = pObj.get("gender").getAsString();
				String bloodGroup = pObj.get("bloodGroup").getAsString();
				String allergies = pObj.get("allergies").getAsString();
				
				patient.setPid(pid);
				patient.setfName(fName);
				patient.setlName(lName);			
				patient.setNic(nic);
				patient.setDob(dob);
				patient.setPhone(phone);
				patient.setEmail(email);			
				patient.setGender(gender);
				patient.setBloodGroup(bloodGroup);
				patient.setAllergies(allergies);
		
			String output = patientcontroller.registerPatient(patient);

			return output;
		}

		@GET
		@Path("/")
		@Produces(MediaType.TEXT_HTML)
		public String getAllPatients() {

			return patientcontroller.getAllPatients();

		}

		@GET
		@Path("/{pid}")
		@Produces(MediaType.TEXT_HTML)
		public String getPatientDetail(@PathParam("pid") String id) {

			return patientcontroller.getPatientDetail(id);

		}

		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String deletePatient(String patientData) { 
		{ 
			JsonObject patientObject = new JsonParser().parse(patientData).getAsJsonObject();
			
			String pno = patientObject.get("pno").getAsString();

			String output = patientcontroller.deletePatient(pno);

			return output;
		}
		
		}
		
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updatePatientDetails(String patientData) {

			JsonObject pObj = new JsonParser().parse(patientData).getAsJsonObject();
			
			String pid = pObj.get("pid").getAsString();
			String fName = pObj.get("fName").getAsString();
			String lName = pObj.get("lName").getAsString();
			String nic = pObj.get("nic").getAsString();
			String dob = pObj.get("dob").getAsString();
			String phone = pObj.get("phone").getAsString();
			String email = pObj.get("email").getAsString();
			String gender = pObj.get("gender").getAsString();
			String bloodGroup = pObj.get("bloodGroup").getAsString();
			String allergies = pObj.get("allergies").getAsString();
			

			patient.setPid(pid);
			patient.setfName(fName);
			patient.setlName(lName);			
			patient.setNic(nic);
			patient.setDob(dob);
			patient.setPhone(phone);
			patient.setEmail(email);			
			patient.setGender(gender);
			patient.setBloodGroup(bloodGroup);
			patient.setAllergies(allergies);
			

			String output = patientcontroller.updatePatientDetails(patient);

			return output;

		}
	}
