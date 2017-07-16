package domain.model;

import java.time.LocalDate;

/**
 * Factory for patients and addresses
 * @author Marie
 */
public class Factory {
    
    private static long idCounter = 0;
    
    private Factory() {}
    
    public static Patient createPatient(String firstName, String lastName, LocalDate bdate, Address address, int weightInKg, int heightInCm) {
        return new Patient(idCounter++, firstName, lastName, bdate, address, weightInKg, heightInCm);
    }
    
    public static Patient createPatient() {
        return new Patient();
    }
    
    public static Address createAddress(String street, int houseNumber, String addOn, String zipCode, String city, String state, String country) {
        return new Address(street, houseNumber, addOn, zipCode, city, state, country);
    }
    
    public static Address createAddress(String street, int houseNumber, String zipCode, String city, String country) {
        return new Address(street, houseNumber, zipCode, city, country);
    }
    
}
