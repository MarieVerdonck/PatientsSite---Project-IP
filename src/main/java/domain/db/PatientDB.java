package domain.db;

import domain.model.Address;
import domain.model.Patient;
import java.time.LocalDate;
import java.util.Collection;

/**
 *
 * @author Marie
 */
public abstract class PatientDB {
   
    abstract public Patient add(String fname, String lname, LocalDate bdate, Address address, int weightInKg, int heightInCm);
    abstract public Patient add(Patient patient);
    abstract public Collection<Patient> getAll();
    abstract public Patient update(long id, String name, String fname, LocalDate bdate, Address address, int weightInKg, int heightInCm);
    abstract public void remove(long id);
    abstract public Patient find(long id);

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
}
