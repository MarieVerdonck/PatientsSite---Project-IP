/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.model.Address;
import domain.model.Factory;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * JUnit tests for Address Class
 * @author Marie
 */
public class AddressTest {

    private final String street = "Lemmingway";
    private final int housenumber = 2;
    private final String addOn = "0B";
    private final String zipcode = "3000";
    private final String city = "Leuven";
    private final String state = "Vlaams-Brabant";
    private final String country = "Belgium";

    @Test
    public void createAddressWithAllCorrectValues() {
        Address address = Factory.createAddress(street, housenumber, addOn, zipcode, city, state, country);
    
        assertEquals(address.getStreet(), street);
        assertEquals(address.getHouseNumber(), housenumber);
        assertEquals(address.getAddOn(), addOn);
        assertEquals(address.getZipCode(), zipcode);
        assertEquals(address.getCity(), city);
        assertEquals(address.getState(), state);
        assertEquals(address.getCountry(), country);
        
        String addressString = address.toString();
        
        assertTrue(addressString.contains(street));
        assertTrue(addressString.contains(Integer.toString(housenumber)));
        assertTrue(addressString.contains(addOn));
        assertTrue(addressString.contains(zipcode));
        assertTrue(addressString.contains(city));
        assertTrue(addressString.contains(country));
        assertTrue(addressString.contains(state));
    }
    
    @Test
    public void createAddressWithAllCorrectValues_NoAddOn_NoState() {
        Address address = Factory.createAddress(street, housenumber, zipcode, city, country);
    
        assertEquals(address.getStreet(), street);
        assertEquals(address.getHouseNumber(), housenumber);
        assertEquals(address.getAddOn(), null);
        assertEquals(address.getZipCode(), zipcode);
        assertEquals(address.getCity(), city);
        assertEquals(address.getState(), null);
        assertEquals(address.getCountry(), country);
        
        String addressString = address.toString();
        
        assertTrue(addressString.contains(street));
        assertTrue(addressString.contains(Integer.toString(housenumber)));
        assertFalse(addressString.contains(addOn));
        assertTrue(addressString.contains(zipcode));
        assertTrue(addressString.contains(city));
        assertTrue(addressString.contains(country));
        assertFalse(addressString.contains(state));
    }
    
    @Test
    public void createAddressWithAllCorrectValues_NoAddOn() {
        Address address = Factory.createAddress(street, housenumber, null, zipcode, city, state, country);
    
        assertEquals(address.getStreet(), street);
        assertEquals(address.getHouseNumber(), housenumber);
        assertEquals(address.getAddOn(), null);
        assertEquals(address.getZipCode(), zipcode);
        assertEquals(address.getCity(), city);
        assertEquals(address.getState(), state);
        assertEquals(address.getCountry(), country);
        
        String addressString = address.toString();
        
        assertTrue(addressString.contains(street));
        assertTrue(addressString.contains(Integer.toString(housenumber)));
        assertFalse(addressString.contains(addOn));
        assertTrue(addressString.contains(zipcode));
        assertTrue(addressString.contains(city));
        assertTrue(addressString.contains(country));
        assertTrue(addressString.contains(state));
    }
    
    @Test
    public void createAddressWithAllCorrectValues_NoState() {
        Address address = Factory.createAddress(street, housenumber, addOn, zipcode, city, null, country);
    
        assertEquals(address.getStreet(), street);
        assertEquals(address.getHouseNumber(), housenumber);
        assertEquals(address.getAddOn(), addOn);
        assertEquals(address.getZipCode(), zipcode);
        assertEquals(address.getCity(), city);
        assertEquals(address.getState(), null);
        assertEquals(address.getCountry(), country);
        
        String addressString = address.toString();
        
        assertTrue(addressString.contains(street));
        assertTrue(addressString.contains(Integer.toString(housenumber)));
        assertTrue(addressString.contains(addOn));
        assertTrue(addressString.contains(zipcode));
        assertTrue(addressString.contains(city));
        assertTrue(addressString.contains(country));
        assertFalse(addressString.contains(state));
    }
    
    @Test (expected = DomainException.class) 
    public void testStreet_Null() {
        Address addressMock = Factory.createAddress();
        try {
            addressMock.setStreet(null);
        } catch (DomainException e) {
            assertTrue(e.getMessage().contains("Street can't be null."));
        }
        addressMock.setStreet(null);
    }
    
    @Test (expected = DomainException.class) 
    public void testStreet_Empty() {
        Address addressMock = Factory.createAddress();
        try {
            addressMock.setStreet("  ");
        } catch (DomainException e) {
            assertTrue(e.getMessage().contains("Street can't be empty."));
        }
        addressMock.setStreet("");
    }
    
    @Test (expected = DomainException.class) 
    public void testHouseNumber_Negative() {
        Address addressMock = Factory.createAddress();
        try {
            addressMock.setHouseNumber(-5);
        } catch (DomainException e) {
            assertTrue(e.getMessage().contains("Housenumber has to be positive."));
        }
        addressMock.setHouseNumber(-5);
    }
    
    @Test (expected = DomainException.class) 
    public void testHouseNumber_Zero() {
        Address addressMock = Factory.createAddress();
        try {
            addressMock.setHouseNumber(0);
        } catch (DomainException e) {
            assertTrue(e.getMessage().contains("Housenumber has to be positive."));
        }
        addressMock.setHouseNumber(0);
    }
    
    @Test (expected = DomainException.class) 
    public void testZipCode_Null() {
        Address addressMock = Factory.createAddress();
        try {
            addressMock.setZipCode(null);
        } catch (DomainException e) {
            assertTrue(e.getMessage().contains("Zipcode can't be null."));
        }
        addressMock.setZipCode(null);
    }
    
    @Test (expected = DomainException.class) 
    public void testZipCode_Empty() {
        Address addressMock = Factory.createAddress();
        try {
            addressMock.setZipCode("  ");
        } catch (DomainException e) {
            assertTrue(e.getMessage().contains("Zipcode can't be empty."));
        }
        addressMock.setZipCode("");
    }
    
    @Test (expected = DomainException.class) 
    public void testCity_Null() {
        Address addressMock = Factory.createAddress();
        try {
            addressMock.setCity(null);
        } catch (DomainException e) {
            assertTrue(e.getMessage().contains("City can't be null."));
        }
        addressMock.setCity(null);
    }
    
    @Test (expected = DomainException.class) 
    public void testCity_Empty() {
        Address addressMock = Factory.createAddress();
        try {
            addressMock.setCity("  ");
        } catch (DomainException e) {
            assertTrue(e.getMessage().contains("City can't be empty."));
        }
        addressMock.setCity("");
    }
    
    @Test (expected = DomainException.class) 
    public void testCountry_Null() {
        Address addressMock = Factory.createAddress();
        try {
            addressMock.setCountry(null);
        } catch (DomainException e) {
            assertTrue(e.getMessage().contains("Country can't be null."));
        }
        addressMock.setCountry(null);
    }
    
    @Test (expected = DomainException.class) 
    public void testCountry_Empty() {
        Address addressMock = Factory.createAddress();
        try {
            addressMock.setCountry("  ");
        } catch (DomainException e) {
            assertTrue(e.getMessage().contains("Country can't be empty."));
        }
        addressMock.setCountry("");
    }
    
}
