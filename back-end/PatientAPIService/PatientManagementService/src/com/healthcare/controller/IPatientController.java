package com.healthcare.controller;

import java.util.ArrayList;

import com.healthcare.model.Patient;

public interface IPatientController {

	public String registerPatient(Patient patient);

	public String getAllPatients();

	public String getPatientDetail(String pid);

	public String updatePatientDetails(Patient patient);

	public String deletePatient(String pno);

}