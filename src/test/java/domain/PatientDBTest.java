/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.db.PatientSingletonDB;
import domain.model.Factory;
import domain.model.Patient;
import java.time.LocalDate;
import java.util.Collection;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import domain.db.PatientDB;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Marie
 */
public class PatientDBTest {
    
    private static PatientDB patientService;
    private static Patient testPatient;
    
    public PatientDBTest() throws ParseException {
        patientService = PatientSingletonDB.getDB();    
        testPatient = Factory.createPatient("testFname", "testName", new Date(2000,01,01), 
                    Factory.createAddress("testStreet", 1, "testZip", "testCity", "testCountry"), 
                    100, 200);
    }
    
    @Before
    public void setUp() {
        patientService.add(testPatient);
    }
    
    @After
    public void tearDown() {
        patientService.remove(testPatient.getId());
    }
    
    @Test 
    public void test_createPatientAndAddToDB() {
        int nrOfPatients = patientService.getAll().size();
        Patient testPatient2 = Factory.createPatient("testFname2", "testName2", new Date(2000,01,01), 
                    Factory.createAddress("testStreet", 1, "testZip", "testCity", "testCountry"), 
                    60, 160);
        patientService.add(testPatient2);
        assertTrue(patientService.find(testPatient2.getId()) != null );
        assertEquals(nrOfPatients+1, patientService.getAll().size());
    }
    
    @Test
    public void test_readContainsTestPatient() {
        Collection<Patient> patients = patientService.getAll();
        Patient foundPatient = null;
        for (Patient patient: patients) {
            if (patient.getId().equals(testPatient.getId())) {
                foundPatient = patient;
            }
        }
        assertTrue(foundPatient != null);
        assertEquals(foundPatient.getFirstName(), testPatient.getFirstName());
        assertEquals(foundPatient.getLastName(), testPatient.getLastName());
    }
    
    @Test
    public void test_updateTestPatient_changeNameAndWeight() {
        Patient patient = new Patient(testPatient.getId(), "newTestFirstName", "newTestLastName", testPatient.getBdate(), testPatient.getAddress(), 70, testPatient.getHeightInCm());
        patientService.update(patient);
        Patient updatedPatient = patientService.find(testPatient.getId());
        
        assertEquals(updatedPatient.getFirstName(), "newTestFirstName");
        assertEquals(updatedPatient.getLastName(), "newTestLastName");
        assertEquals(updatedPatient.getWeightInKg(), 70);
    }
    
    @Test
    public void test_deletePatient() throws ParseException {
        Patient testPatient2 = Factory.createPatient("testFname2", "testName2", new SimpleDateFormat("mm/dd/yyyy").parse("01/01/2000"), 
                    Factory.createAddress("testStreet", 1, "testZip", "testCity", "testCountry"), 
                    60, 160);
        patientService.add(testPatient2);
        assertTrue(patientService.find(testPatient2.getId()) != null );
        patientService.remove(testPatient2.getId());
        assertTrue(patientService.find(testPatient2.getId()) == null );
    }
    
    @Test (expected = DomainException.class)
    public void test_deleteNonExistentPatient() {
        Collection<Patient> patients = patientService.getAll();
        boolean newIdFound = false;
        long i = 0;
        while (!newIdFound) {
            if (patientService.find(i) == null) {
                newIdFound = true;
            }
            i++;
        }
        try {
            patientService.remove(i);
        } catch (DomainException e) {
            assertTrue(e.getMessage().contains("Patient with id " + i + " not found in DB"));
        } 
        patientService.remove(i);
    }
}
