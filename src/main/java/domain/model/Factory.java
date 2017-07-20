package domain.model;

import java.util.Date;

/**
 * Factory for patients and addresses
 * @author Marie
 */
public class Factory {
    
    private static long idCounter = 1;
    
    private Factory() {}
    
    public static Patient createPatient(String firstName, String lastName, Date bdate, Address address, int weightInKg, int heightInCm) {
        return new Patient(idCounter++, firstName, lastName, bdate, address, weightInKg, heightInCm);
    }
    
    public static Patient createPatient() {
        Patient patient = new Patient();
        patient.setId(idCounter++);
        return patient;
    }
    
    public static Address createAddress(String street, int houseNumber, String addOn, String zipCode, String city, String state, String country) {
        return new Address(street, houseNumber, addOn, zipCode, city, state, country);
    }
    
    public static Address createAddress(String street, int houseNumber, String zipCode, String city, String country) {
        return new Address(street, houseNumber, zipCode, city, country);
    }
    
    public static Address createAddress() {
        return new Address();
    }
    
}
