/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.db.PatientService;
import domain.db.PatientSingletonDB;
import domain.model.Factory;
import domain.model.Patient;
import java.time.LocalDate;
import java.util.Collection;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Marie
 */
public class ServiceTest {
    
    private static PatientService patientService;
    private static Patient testPatient;
    
    public ServiceTest() {
        patientService = PatientSingletonDB.getDB();    
        testPatient = Factory.createPatient("testFname", "testName", LocalDate.parse("2000-01-01"), 
                    Factory.createAddress("testStreet", 1, "testZip", "testCity", "testCountry"), 
                    100, 200);
    }
    
    @Before
    public void setUp() {
        patientService.create(testPatient);
    }
    
    @After
    public void tearDown() {
        patientService.delete(testPatient.getId());
    }
    
    @Test 
    public void test_createPatientAndAddToDB() {
        int nrOfPatients = patientService.read().size();
        Patient testPatient2 = Factory.createPatient("testFname2", "testName2", LocalDate.parse("2000-01-01"), 
                    Factory.createAddress("testStreet", 1, "testZip", "testCity", "testCountry"), 
                    60, 160);
        patientService.create(testPatient2);
        assertTrue(patientService.find(testPatient2.getId()) != null );
        assertEquals(nrOfPatients+1, patientService.read().size());
    }
    
    @Test
    public void test_readContainsTestPatient() {
        Collection<Patient> patients = patientService.read();
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
        patientService.update(testPatient.getId(), "newTestFirstName", "newTestLastName", testPatient.getBdate(), testPatient.getAddress(), 70, testPatient.getHeightInCm());
        Patient updatedPatient = patientService.find(testPatient.getId());
        
        assertEquals(updatedPatient.getFirstName(), "newTestFirstName");
        assertEquals(updatedPatient.getLastName(), "newTestLastName");
        assertEquals(updatedPatient.getWeightInKg(), 70);
    }
    
    @Test
    public void test_deletePatient() {
        Patient testPatient2 = Factory.createPatient("testFname2", "testName2", LocalDate.parse("2000-01-01"), 
                    Factory.createAddress("testStreet", 1, "testZip", "testCity", "testCountry"), 
                    60, 160);
        patientService.create(testPatient2);
        assertTrue(patientService.find(testPatient2.getId()) != null );
        patientService.delete(testPatient2.getId());
        assertTrue(patientService.find(testPatient2.getId()) == null );
    }
    
    @Test (expected = DomainException.class)
    public void test_deleteNonExistentPatient() {
        Collection<Patient> patients = patientService.read();
        boolean newIdFound = false;
        long i = 0;
        while (!newIdFound) {
            if (patientService.find(i) == null) {
                newIdFound = true;
            }
            i++;
        }
        try {
            patientService.delete(i);
        } catch (DomainException e) {
            assertTrue(e.getMessage().contains("Patient with id " + i + " not found in DB"));
        } 
        patientService.delete(i);
    }
}
