package com.systelab.seed.service;

import com.systelab.seed.model.patient.Patient;
import com.systelab.seed.util.exceptions.PatientNotFoundException;
import com.systelab.seed.util.pagination.Page;
import com.systelab.seed.util.pagination.Pageable;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.ejb.Local;

@Local
public interface PatientService {

    Page<Patient> getAllPatients(Pageable pageable);

    XSSFWorkbook getPatientsWorkbook();

    Patient getPatient(Long patientId);

    void create(Patient patient);

    Patient update(Long id, Patient patient);

    void delete(Long id) throws PatientNotFoundException;
}