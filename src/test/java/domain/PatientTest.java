package domain;

import domain.model.Factory;
import domain.model.Patient;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.time.LocalDate;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * JUnit tests for Patient Class
 * @author Marie
 */
public class PatientTest {
    
    private final String patient1_Name = "Lemmings";
    private final String patient1_Fname = "John";
    private final Date patient1_Bdate;
    private final String patient1_address_street = "Lemmingway";
    private final int patient1_address_housenumber = 2;
    private final String patient1_address_zipcode = "3000";
    private final String patient1_address_city = "Leuven";
    private final String patient1_address_country = "Belgium";
    private final int patient1_weight = 98;
    private final int patient1_height = 180;

    public PatientTest() throws ParseException {
        this.patient1_Bdate = new SimpleDateFormat("mm/dd/yyyy").parse("01/06/2002");
    }
    

    @Test
    public void createPatientWithCorrectValues() {
        Patient patient = Factory.createPatient(patient1_Fname, patient1_Name, patient1_Bdate, 
                        Factory.createAddress(patient1_address_street, patient1_address_housenumber, patient1_address_zipcode, patient1_address_city, patient1_address_country), 
                        patient1_weight, patient1_height);
        
        assertEquals(patient.getLastName(), patient1_Name);
        assertEquals(patient.getFirstName(), patient1_Fname);
        assertEquals(patient.getBdate(), patient1_Bdate);
        assertEquals(patient.getAddress().getStreet(), patient1_address_street);
        assertEquals(patient.getAddress().getHouseNumber(), patient1_address_housenumber);
        assertEquals(patient.getAddress().getZipCode(), patient1_address_zipcode);
        assertEquals(patient.getAddress().getCity(), patient1_address_city);
        assertEquals(patient.getAddress().getCountry(), patient1_address_country);
        assertEquals(patient.getWeightInKg(), patient1_weight);
        assertEquals(patient.getHeightInCm(), patient1_height);
    }
    
    @Test (expected = DomainException.class) 
    public void testName_Null() {
        Patient patientMock = Factory.createPatient();
        try {
            patientMock.setLastName(null);
        } catch (DomainException e) {
            assertTrue(e.getMessage().contains("Name can't be null."));
        }
        patientMock.setLastName(null);
    }
    
    @Test (expected = DomainException.class) 
    public void testName_Empty() {
        Patient patientMock = Factory.createPatient();
        try {
            patientMock.setLastName("   ");
        } catch (DomainException e) {
            assertTrue(e.getMessage().contains("Name can't be empty."));
        }
        patientMock.setLastName("");
    }
    
     public void testFirstName_Null() {
        Patient patientMock = Factory.createPatient();
        try {
            patientMock.setFirstName(null);
        } catch (DomainException e) {
            assertTrue(e.getMessage().contains("First Name can't be null."));
        }
        patientMock.setFirstName(null);
    }
    
    @Test (expected = DomainException.class) 
    public void testFirstName_Empty() {
        Patient patientMock = Factory.createPatient();
        try {
            patientMock.setFirstName("   ");
        } catch (DomainException e) {
            assertTrue(e.getMessage().contains("First Name can't be empty."));
        }
        patientMock.setFirstName("");
    }
    
    @Test (expected = DomainException.class) 
    public void testBdate_Null() {
        Patient patientMock = Factory.createPatient();
        try {
            patientMock.setBdate(null);
        } catch (DomainException e) {
            assertTrue(e.getMessage().contains("Birthdate can't be null."));
        }
        patientMock.setBdate(null);
    }
    
    @Test (expected = DomainException.class) 
    public void testAddress_Null() {
        Patient patientMock = Factory.createPatient();
        try {
            patientMock.setAddress(null);
        } catch (DomainException e) {
            assertTrue(e.getMessage().contains("Address can't be null."));
        }
        patientMock.setAddress(null);
    }
    
    @Test (expected = DomainException.class) 
    public void testHeight_Negative() {
        Patient patientMock = Factory.createPatient();
        try {
            patientMock.setHeightInCm(-120);
        } catch (DomainException e) {
            assertTrue(e.getMessage().contains("Height has to be positive"));
        }
        patientMock.setHeightInCm(-120);
    }
    
    @Test (expected = DomainException.class)
    public void testHeight_Zero() {
        Patient patientMock = Factory.createPatient();
        try {
            patientMock.setHeightInCm(0);
        } catch (DomainException e) {
            assertTrue(e.getMessage().contains("Height has to be positive"));
        }
        patientMock.setHeightInCm(0);
    }
    
    @Test (expected = DomainException.class) 
    public void testWeight_Negative() {
        Patient patientMock = Factory.createPatient();
        try {
            patientMock.setWeightInKg(-20);
        } catch (DomainException e) {
            assertTrue(e.getMessage().contains("Weight has to be positive"));
        }
        patientMock.setWeightInKg(-20);
    }
    
    @Test (expected = DomainException.class) 
    public void testWeight_Zero() {
        Patient patientMock = Factory.createPatient();
        try {
            patientMock.setWeightInKg(0);
        } catch (DomainException e) {
            assertTrue(e.getMessage().contains("Weight has to be positive"));
        }
        patientMock.setWeightInKg(0);
    }
    
}
