/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.db;

import domain.model.Address;
import domain.model.Patient;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Marie
 */
public class PatientJpaDB extends PatientDB {

    protected static EntityManagerFactory entityManagerFactory;
    protected static EntityManager entityManager;

    public static void init() {
        entityManagerFactory = Persistence.createEntityManagerFactory("IP-patients");
        entityManager = entityManagerFactory.createEntityManager();
    }
    
    public PatientJpaDB() {
        init();
    }

    @Override
    public Patient add(Patient patient) {
        if (this.find(patient.getId()) != null) {
            throw new DBException("There is already a patient with id: " + Long.toString(patient.getId()) + " in the JPA DB.");
        }
        entityManager.getTransaction().begin();
        entityManager.persist(patient);
        entityManager.getTransaction().commit();
        return patient;
    }

    @Override
    public Collection<Patient> getAll() {
        entityManager.getTransaction().begin();
        List patients = entityManager.createQuery("SELECT p FROM patient p", Patient.class).getResultList();
        entityManager.getTransaction().commit();
        return patients;
    }

    public Patient update(long id, String name, String fname, Date bdate, Address address, int weightInKg, int heightInCm) {
        Patient patientInDB = entityManager.find(Patient.class, id);

        entityManager.getTransaction().begin();
        patientInDB.setLastName(name);
        patientInDB.setFirstName(fname);
        patientInDB.setBdate(bdate);
        patientInDB.setAddress(address);
        patientInDB.setWeightInKg(weightInKg);
        patientInDB.setHeightInCm(heightInCm);
        entityManager.getTransaction().commit();

        return entityManager.find(Patient.class, id);
    }

    @Override
    public Patient update(Patient patient) {
        this.update(patient.getId(), patient.getLastName(), patient.getFirstName(),
                patient.getBdate(), patient.getAddress(), patient.getWeightInKg(), patient.getHeightInCm());
        return patient;
    }

    @Override
    public void remove(long id) {
        if (entityManager.find(Patient.class, id) == null) {
            throw new DBException("There is no patient in DB with id: " + id);
        }
        Patient patientInDB = entityManager.find(Patient.class, id);

        entityManager.getTransaction().begin();
        entityManager.remove(patientInDB);
        entityManager.getTransaction().commit();
    }

    @Override
    public Patient find(long id) {
        return entityManager.find(Patient.class, id);
    }

    @Override
    public void close() {
        entityManager.close();
    }

}
