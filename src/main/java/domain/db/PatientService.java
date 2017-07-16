package domain.db;

import domain.model.Address;
import domain.model.Patient;
import java.time.LocalDate;
import java.util.Collection;

/**
 *
 * @author Marie
 */
public interface PatientService {
   
    public Patient create(String name, String fname, LocalDate bdate, Address address, int weightInKg, int heightInCm);
    public Collection<Patient> read();
    public Patient update(long id, String name, String fname, LocalDate bdate, Address address, int weightInKg, int heightInCm);
    public void delete(long id);
    
}
