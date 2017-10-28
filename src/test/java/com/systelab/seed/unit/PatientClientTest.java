package com.systelab.seed.unit;

import com.systelab.seed.client.PatientClient;
import com.systelab.seed.client.RequestException;
import com.systelab.seed.model.patient.Address;
import com.systelab.seed.model.patient.Patient;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.TestCaseId;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.allure.model.DescriptionType;
import ru.yandex.qatools.allure.model.SeverityLevel;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Title("Patients Test Suite")
public class PatientClientTest extends BaseClientTest
{
  private static final Logger logger = Logger.getLogger(PatientClientTest.class.getName());

  static PatientClient clientForPatient;

  @BeforeClass
  public static void init() throws RequestException
  {
    clientForPatient = new PatientClient();
    login(clientForPatient);
  }
  
  
  @Step("Delete previous.")
  public void deleteprevious()
  {
	  
  }
  
  @TestCaseId("SEED-SCC-1")
  @Description(value = "Test that is possible to create a Patient.\n\nPrerequisites:\n\n" + "- Prerequisite 1\n" + "- Prerequisite 2\n" + "- Prerequisite 3\n", type = DescriptionType.MARKDOWN)
  @Features("Patient")
  @Severity(SeverityLevel.BLOCKER)
  @Test
  public void testCreatePatient() throws RequestException
  {
	deleteprevious();
	Patient patient = new Patient();
	patient.setName("Ralph");
	patient.setSurname("Burrows");
	patient.setEmail("rburrows@gmail.com");
	

    Address address = new Address();
    address.setStreet("E-Street, 90");
    address.setCity("Barcelona");
    address.setZip("08021");
    patient.setAddress(address);
    Patient patient2 = clientForPatient.create(patient);
    Assert.assertNotNull(patient2);
  }

  @TestCaseId("SEED-SCC-2")
  @Description("Test that it is not possible to create a Invalid Patient and that we have an exception.")
  @Features("Patient")
  @Severity(SeverityLevel.BLOCKER)
  @Test
  public void testCreateInvalidPatient()
  {
	deleteprevious();
	Patient patient = new Patient();
	patient.setName("William");
	patient.setSurname("Burrows");

	patient.setEmail("rburrows");

    Address address = new Address();
    address.setStreet("E-Street, 90");
    address.setCity("Barcelona");
    address.setZip("08021");
    patient.setAddress(address);
    Exception caughtException = null;
    try
    {
      Patient patient2 = clientForPatient.create(patient);
    }
    catch (Exception ex)
    {
      caughtException = ex;
    }

    Assert.assertEquals("Invalid error code exception", 400, ((RequestException) caughtException).getErrorCode());

  }

  @TestCaseId("SEED-SCC-3")
  @Description("Test that we can get a List of Patients.")
  @Features("Patient")
  @Severity(SeverityLevel.BLOCKER)
  @Test
  public void testGetPatientList() throws RequestException
  {
    List<Patient> patients = clientForPatient.get();
    for (int i = 0; i < patients.size(); i++)
    {
      logger.info(patients.get(i).getName());
    }
    Assert.assertNotNull(patients);
  }

  @TestCaseId("SEED-SCC-4")
  @Description("Test that we can get a Patient.")
  @Features("Patient")
  @Severity(SeverityLevel.BLOCKER)
  @Test
  public void testGetPatient() throws RequestException
  {

    List<Patient> patients = clientForPatient.get();
    for (int i = 0; i < patients.size(); i++)
    {
    	Patient patient = clientForPatient.get(patients.get(i).getId());
      Assert.assertNotNull(patient);
    }
  }




}