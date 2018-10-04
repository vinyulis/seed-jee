package com.systelab.seed.service;

import com.systelab.seed.model.patient.Patient;
import com.systelab.seed.util.exceptions.PatientNotFoundException;
import com.systelab.seed.util.pagination.Page;
import com.systelab.seed.util.pagination.Pageable;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.ejb.Local;
import java.util.UUID;

@Local
public interface PatientService {

    Page<Patient> getAllPatients(Pageable pageable);

    XSSFWorkbook getPatientsWorkbook();

    Patient getPatient(UUID patientId);

    void create(Patient patient);

    Patient update(UUID id, Patient patient);

    void delete(UUID id) throws PatientNotFoundException;
}