package domain.db;

import domain.model.Address;
import domain.model.Patient;

import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Marie
 */
public abstract class PatientDB {
   
    //abstract public Patient add(String fname, String lname, Date bdate, Address address, int weightInKg, int heightInCm);
    abstract public Patient add(Patient patient);
    abstract public Collection<Patient> getAll();
    //abstract public Patient update(long id, String name, String fname, Date bdate, Address address, int weightInKg, int heightInCm);
    abstract public Patient update(Patient patient);
    abstract public void remove(long id);
    abstract public Patient find(long id);
    abstract public void close();

    public double getAverageWeight() {
        int totalWeight = 0;
        for (Patient patient: getAll()) {
            totalWeight += patient.getWeightInKg();
        }
        return totalWeight/getAll().size();
    }

    public double getAverageHeight() {
        int totalHeight = 0;
        for (Patient patient: getAll()) {
            totalHeight += patient.getHeightInCm();
        }
        return totalHeight/getAll().size();
    }
    
    public double getAverageAge() {
        int totalAge = 0;
        for (Patient patient: getAll()) {
            totalAge += patient.getAge();
        }
        return totalAge/getAll().size();
    }
}
