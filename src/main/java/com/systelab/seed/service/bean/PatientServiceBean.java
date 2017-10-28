package com.systelab.seed.service.bean;

import com.systelab.seed.infrastructure.events.cdi.PatientCreated;
import com.systelab.seed.model.patient.Patient;
import com.systelab.seed.service.PatientService;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Stateless
public class PatientServiceBean implements PatientService
{

  @PersistenceContext(unitName = "SEED")
  private EntityManager em;

  
  @Inject @PatientCreated
  private Event<Patient> patientCreated;
  
  @Override
  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public void create(Patient patient)
  {
    em.persist(patient);
    // TODO: Be careful because CDI Events are synchronous. JMS could be considered.
    patientCreated.fire(patient);
  }

  @Override
  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public Patient update(Long id, Patient patient)
  {
    em.merge(patient);
    return patient;
  }

  @Override
  public List<Patient> getAllPatients()
  {
    List<Patient> patients = new ArrayList<Patient>();

    TypedQuery<Patient> query = em.createNamedQuery(Patient.FIND_ALL, Patient.class);
    List<Patient> results = query.getResultList();
    for (Patient s : results)
    {
    	patients.add(s);
    }
    return patients;
  }

  @Override
  public Patient getPatient(Long patientId)
  {
	Patient s = null;
    s = em.find(Patient.class, patientId);
    return s;
  }

}
