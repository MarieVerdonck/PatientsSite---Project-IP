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
    
}
