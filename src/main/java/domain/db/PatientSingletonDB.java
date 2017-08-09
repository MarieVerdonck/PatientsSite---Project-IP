package domain.db;

import domain.DomainException;
import domain.model.Address;
import domain.model.Patient;
import domain.model.Factory;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Singleton DB
 *
 * @author Marie
 */
public class PatientSingletonDB extends PatientDB {

    private static volatile PatientSingletonDB instance = null;
    private static ArrayList<Patient> patients = new ArrayList<Patient>();

    private PatientSingletonDB() {
    }

    public static PatientSingletonDB getDB() throws ParseException {
        if (instance == null) {
            synchronized (PatientSingletonDB.class) {
                if (instance == null) {
                    instance = new PatientSingletonDB();
                    setStarterValues();
                }
            }
        }
        return instance;
    }

    private static void setStarterValues() throws ParseException {
        //Some starter values 
        Patient patient1 = Factory.createPatient("John", "Lemmings", new SimpleDateFormat("mm/dd/yyyy").parse("12/03/1965"), Factory.createAddress("Lemmingway", 2, "3000", "Leuven", "Belgium"), 98, 180);
        Patient patient2 = Factory.createPatient("Alise", "Trenton", new SimpleDateFormat("mm/dd/yyyy").parse("06/21/1987"), Factory.createAddress("Treestreet", 65, "3000", "Leuven", "Belgium"), 59, 162);
        patients.add(patient1);
        patients.add(patient2);
    }

    public Patient add(String fname, String lname, Date bdate, Address address, int weightInKg, int heightInCm) {
        Patient patient = Factory.createPatient(fname, lname, bdate, address, weightInKg, heightInCm);
        this.add(patient);
        return patient;
    }

    @Override
    public Patient add(Patient patient) {
        DBException.checkIfAlreadyInDB(patient, this);
        patients.add(patient);
        return patient;
    }

    @Override
    public Collection<Patient> getAll() {
        return patients;
    }

    public Patient update(long id, String fname, String lname, Date bdate, Address address, int weightInKg, int heightInCm) {
        if (this.find(id) != null && this.find(id) instanceof Patient) {
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
    public Patient update(Patient patient) {
        return this.update(patient.getId(), patient.getFirstName(), patient.getLastName(),
                patient.getBdate(), patient.getAddress(), patient.getWeightInKg(), patient.getHeightInCm());
    }

    @Override
    public void remove(long id) {
        if (this.find(id) != null && this.find(id) instanceof Patient) {
            this.deletePatient(this.find(id));
        } else {
            throw new DomainException("Patient with id " + id + " not found in DB");
        }
    }

    @Override
    public Patient find(long id) {
        for (Patient patient : patients) {
            if (patient.getId() == id) {
                return patient;
            }
        }
        return null;
    }

    private void deletePatient(Patient patient) {
        int location = -1;
        for (int i = 0; i < patients.size(); i++) {
            if (patients.get(i).equals(patient)) {
                location = i;
            }
        }
        if (location >= 0) {
            patients.remove(location);
        }
    }

    @Override
    public void close() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
