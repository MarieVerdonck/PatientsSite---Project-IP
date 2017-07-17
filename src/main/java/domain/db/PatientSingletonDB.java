package domain.db;

import domain.DomainException;
import domain.model.Address;
import domain.model.Patient;
import domain.model.Factory;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Singleton DB
 * @author Marie
 */
public class PatientSingletonDB implements PatientService {
    
    private static volatile PatientSingletonDB instance = null;
    private static ArrayList<Patient> patients =  new ArrayList<Patient>();
    //Some starter values 
    private static Patient patient1 = Factory.createPatient("John", "Lemmings", LocalDate.parse("1956-12-03"), Factory.createAddress("Lemmingway", 2, "3000", "Leuven", "Belgium"), 98, 180);
    private static Patient patient2 = Factory.createPatient("Alise", "Trenton", LocalDate.parse("1985-12-03"), Factory.createAddress("Treestreet", 65, "3000", "Leuven", "Belgium"), 59, 162);
    
    private PatientSingletonDB() {}
    
    public static PatientSingletonDB getDB() {
        if (instance == null) {
			synchronized(PatientSingletonDB.class) {
				if (instance == null) {
					instance = new PatientSingletonDB();
					setStarterValues();
				}
			}
		}
		return instance;
    }
    
    private static void setStarterValues() {
        patients.add(patient1);
        patients.add(patient2);
    }

    @Override
    public Patient create(String fname, String lname, LocalDate bdate, Address address, int weightInKg, int heightInCm) {
        Patient patient = Factory.createPatient(fname, lname, bdate, address, weightInKg, heightInCm);
        this.addPatient(patient);
        return patient;
    }
    
    @Override
    public Patient create(Patient patient) {
        this.addPatient(patient);
        return patient;
    }
    
    private void addPatient(Patient patient) {
         if (this.find(patient.getId()) == null) {
            patients.add(patient);
        } else {
            throw new DomainException("There is already a patient with id " + patient.getId() + " in DB.");
        }
    }

    @Override
    public Collection<Patient> read() {
        return patients;
    }

    @Override
    public Patient update(long id, String fname, String lname, LocalDate bdate, Address address, int weightInKg, int heightInCm) {
        if (this.find(id)!=null && this.find(id) instanceof Patient) {
            Patient patient = this.find(id);
            patient.setFirstName(fname);
            patient.setLastName(lname);
            patient.setBdate(bdate);
            patient.setAddress(address);
            patient.setWeightInKg(weightInKg);
            patient.setHeightInCm(heightInCm);
            return patient;
        } else {
            throw new DomainException("Patient with id " + id + " not found in DB");
        }
    }

    @Override
    public void delete(long id) {
        if (this.find(id)!=null && this.find(id) instanceof Patient) {
            this.deletePatient(this.find(id));
        } else {
            throw new DomainException("Patient with id " + id + " not found in DB");
        }
    }
    
    @Override
    public Patient find(long id) {
        for (Patient patient: patients) {
            if (patient.getId()==id) {
                return patient;
            }
        }
        return null;
    }
    
    private void deletePatient(Patient patient) {
        int location = -1;
        for(int i=0; i<patients.size(); i++) {
            if (patients.get(i).equals(patient)) {
                location = i;
            }
        }
        if (location >= 0) {
            patients.remove(location);
        }
    }
}
