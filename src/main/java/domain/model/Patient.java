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
        checkStringNotEmptyOrNull(lastName, "Name");
        this.lastName = lastName;
    }
    
    public void setFirstName(String firstName) {
        checkStringNotEmptyOrNull(firstName, "First Name");
        this.firstName = firstName;
    }
    
    public void setBdate(LocalDate bdate) {
        checkStringNotEmptyOrNull(bdate.toString(), "Birthdate");
        this.bdate = bdate;
    }
    
    public void setAddress(Address address) {
        checkStringNotEmptyOrNull(address.toString(), "Address");
        this.address = address;
    }

    public int getWeightInKg() {
        return weightInKg;
    }

    public void setWeightInKg(int weightInKg) {
        this.checkStringNotEmptyOrNull(String.valueOf(weightInKg), "Weight");
        this.checkIfNumberAndPositive(String.valueOf(weightInKg), "Weight");
        this.weightInKg = weightInKg;
    }

    public int getHeightInCm() {
        return heightInCm;
    }

    public void setHeightInCm(int heightInCm) {
        this.checkStringNotEmptyOrNull(String.valueOf(heightInCm), "Height");
        this.checkIfNumberAndPositive(String.valueOf(heightInCm), "Height");
        this.heightInCm = heightInCm;
    }
    
    private void checkIfNumberAndPositive(String value, String key) {
	if (!(Integer.valueOf(value) instanceof Integer)) {
            throw new DomainException(key + " should be a number.");
	} else {
            if(Integer.valueOf(value) <= 0) {
		throw new DomainException(key + " has to be positive.");
            }
	}
    }
    
    private void checkStringNotEmptyOrNull(String value, String key) {
	if (value==null) {
            throw new DomainException(key + " can't be null.");
	}
		if (value.trim().isEmpty()) {
			throw new DomainException(key + " can't be empty.");
		}
	}
}
