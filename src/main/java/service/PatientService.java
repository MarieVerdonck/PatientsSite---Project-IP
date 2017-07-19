package service;

import domain.db.PatientDB;
import domain.db.PatientSingletonDB;
import domain.model.Address;
import domain.model.Factory;
import domain.model.Patient;
import java.time.LocalDate;
import java.util.Collection;

/**
 * Facade class to access all functionalities of the Patient logic
 * @author Marie
 */
public class PatientService {
    
    PatientDB db;
    
    public PatientService(String typeDB) {
        if (typeDB.equals("Memory")) {
            db = PatientSingletonDB.getDB();
        } else {
            //TODO: Non-singleton DB
        }
    }
    
    public void create(String fname, String lname, int byear, int bmonth, int bday, 
                        String street, int nr, String addOn, String zipcode, String city, String state, String country,
                        int weight, int height) {
        Address address = Factory.createAddress(street, nr, addOn, zipcode, city, state, country);
        LocalDate bdate = LocalDate.parse(byear + "-" + bmonth + "-" + bday);
        db.add(fname, lname, bdate, address, weight, height);
    }
    
    public Collection<Patient> read() {
        return db.getAll();
    }
    
    public void update(long id, String fname, String lname, int byear, int bmonth, int bday, 
                        String street, int nr, String addOn, String zipcode, String city, String state, String country,
                        int weight, int height) {
        Address address = Factory.createAddress(street, nr, addOn, zipcode, city, state, country);
        LocalDate bdate = LocalDate.parse(byear + "-" + bmonth + "-" + bday);
        db.update(id, fname, lname, bdate, address, weight, height);
    }
    
    public void delete(long id) {
        db.remove(id);
    }
    
    public Patient find(long id) {
        return db.find(id);
    }
    
    public double averageWeight() {
        return db.getAverageWeight();
    }
    
    public double averageHeight() {
        return db.getAverageHeight();
    }
    
}
