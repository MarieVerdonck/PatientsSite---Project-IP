package domain.model;

import domain.DomainException;
import java.time.LocalDate;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
/**
 * Entity that represents a person
 * @author Marie
 */
@Entity
public class Patient implements Serializable {
    
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator = "patient_seq_gen")
    @SequenceGenerator(name="patient_seq_gen", sequenceName="PATIENT_SEQ")
    private Long id;
     
    @Column(name = "first_name", nullable = false)
    private String firstName;
     
    @Column(name = "last_name", nullable = false)
    private String lastName;
    
    private LocalDate bdate;
    private Address address;
    private int weightInKg;
    private int heightInCm;

    protected Patient(String firstName, String lastName, LocalDate bdate, Address address, int weightInKg, int heightInCm) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bdate = bdate;
        this.address = address;
        this.weightInKg = weightInKg;
        this.heightInCm = heightInCm;
    }
    
    protected Patient(Long id, String firstName, String lastName, LocalDate bdate, Address address, int weightInKg, int heightInCm) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bdate = bdate;
        this.address = address;
        this.weightInKg = weightInKg;
        this.heightInCm = heightInCm;
    }   
    
    protected Patient() {}
    
    public void setId(Long id) {
        DomainException.checkNotNull(id, "ID");
        this.id = id;
    }
    
    public Long getId() { return this.id; }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBdate() {
        return bdate;
    }

    public Address getAddress() {
        return address;
    }
    
    public void setLastName(String lastName) {
        DomainException.checkNotNull(lastName, "Name");
        DomainException.checkStringNotEmpty(lastName, "Name");
        this.lastName = lastName;
    }
    
    public void setFirstName(String firstName) {
        DomainException.checkNotNull(firstName, "First Name");
        DomainException.checkStringNotEmpty(firstName, "First Name");
        this.firstName = firstName;
    }
    
    public void setBdate(LocalDate bdate) {
        DomainException.checkNotNull(bdate, "Birthdate");
        DomainException.checkStringNotEmpty(bdate.toString(), "Birthdate");
        this.bdate = bdate;
    }
    
    public void setAddress(Address address) {
        DomainException.checkNotNull(address, "Address");
        DomainException.checkStringNotEmpty(address.toString(), "Address");
        this.address = address;
    }

    public int getWeightInKg() {
        return weightInKg;
    }

    public void setWeightInKg(int weightInKg) {
        DomainException.checkNotNull(weightInKg, "Weight");
        DomainException.checkStringNotEmpty(String.valueOf(weightInKg), "Weight");
        DomainException.checkIfNumberAndPositive(String.valueOf(weightInKg), "Weight");
        this.weightInKg = weightInKg;
    }

    public int getHeightInCm() {
        return heightInCm;
    }

    public void setHeightInCm(int heightInCm) {
        DomainException.checkNotNull(heightInCm, "Height");
        DomainException.checkStringNotEmpty(String.valueOf(heightInCm), "Height");
        DomainException.checkIfNumberAndPositive(String.valueOf(heightInCm), "Height");
        this.heightInCm = heightInCm;
    }
}
