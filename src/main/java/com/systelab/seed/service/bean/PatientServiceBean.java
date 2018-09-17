package com.systelab.seed.service.bean;

import com.systelab.seed.infrastructure.events.cdi.PatientCreated;
import com.systelab.seed.model.patient.Patient;
import com.systelab.seed.service.PatientService;
import com.systelab.seed.util.exceptions.PatientNotFoundException;
import com.systelab.seed.util.pagination.Page;
import com.systelab.seed.util.pagination.Pageable;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class PatientServiceBean implements PatientService {

    @PersistenceContext(unitName = "SEED")
    private EntityManager em;


    @Inject
    @PatientCreated
    private Event<Patient> patientCreated;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void create(Patient patient) {
        em.persist(patient);
        // Be careful because CDI Events are synchronous.
        // In JEE 8 fireAsync was introduced. Use it as soon as you upgrade.
        patientCreated.fire(patient);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Patient update(Long id, Patient patient) {
        em.merge(patient);
        return patient;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(Long id) throws PatientNotFoundException {
        Patient p = em.find(Patient.class, id);
        if (p != null) {
            em.remove(p);
        } else {
            throw new PatientNotFoundException();
        }
    }

    @Override
    public Page<Patient> getAllPatients(Pageable pageable) {

        TypedQuery<Long> queryTotal = em.createNamedQuery(Patient.ALL_COUNT, Long.class);
        TypedQuery<Patient> query = em.createNamedQuery(Patient.FIND_ALL, Patient.class);
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<Patient> patients = query.getResultList();
        long totalElements = (long) queryTotal.getSingleResult();

        return new Page<Patient>(patients, totalElements);
    }


    @Override
    public XSSFWorkbook getPatientsWorkbook() {
        final XSSFWorkbook wb = new XSSFWorkbook();

        Sheet sheet = wb.createSheet("Patients");
        Page<Patient> patients = getAllPatients(new Pageable());

        int rowNum = 0;

        for (int i = 0; i < patients.getContent().size(); i++) {
            Row row = sheet.createRow(rowNum++);
            int colNum = 0;
            Cell cell1 = row.createCell(colNum++);
            cell1.setCellValue(patients.getContent().get(i).getName());
            Cell cell2 = row.createCell(colNum++);
            cell2.setCellValue(patients.getContent().get(i).getSurname());
            Cell cell3 = row.createCell(colNum++);
            cell3.setCellValue(patients.getContent().get(i).getEmail());
        }
        return wb;
    }

    @Override
    public Patient getPatient(Long patientId) {
        return em.find(Patient.class, patientId);
    }

}
