package com.systelab.seed.service;

import com.systelab.seed.model.patient.Patient;
import java.util.List;

import javax.ejb.Local;

@Local
public interface PatientService
{

  List<Patient> getAllPatients();

  Patient getPatient(Long patientId);

  void create(Patient patient);

  Patient update(Long id, Patient patient);
}