package domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import domain.DomainException;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.DATE;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * Entity that represents a person
 * @author Marie
 */
@Entity(name="patient")
@Table(name="patient")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Patient implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator = "patient_seq_gen")
    @SequenceGenerator(name="patient_seq_gen", sequenceName="PATIENT_SEQ")
    private Long id;
     
    @Column(name = "first_name", nullable = false)
    @NotNull(message = "Please enter your first name.")
    @Size(min=1, max=15, message = "Your first name must be between 1 and 15 characters.")
    private String firstName;
     
    @Column(name = "last_name", nullable = false)
    @NotNull(message = "Please enter your last name.")
    @Size(min=1, max=30, message = "Your last name must be between 1 and 30 characters.")
    private String lastName;
    
    /* handles data-binding (parsing) and display if spring form tld or spring:eval */
    @Column(name = "birth_date", nullable = false)
    @NotNull(message = "Please enter birthdate.")
    @Past(message = "Birthdate must be in the past.")
    @DateTimeFormat(pattern = "mm/dd/yyyy")
    @Temporal(DATE)
    private Date bdate;
    
    @Column(name = "address", nullable = false)
    @NotNull(message = "Please enter address.")
    private Address address;
    
    @Column(name = "weightInKg", nullable = false)
    @NotNull(message = "Please enter weight.")
    @Min(1)
    private int weightInKg;
    
    @Column(name = "heightInCm", nullable = false)
    @NotNull
    @Min(1)
    private int heightInCm;

    protected Patient(String firstName, String lastName, Date bdate, Address address, int weightInKg, int heightInCm) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bdate = bdate;
        this.address = address;
        this.weightInKg = weightInKg;
        this.heightInCm = heightInCm;
    }
    
    public Patient(Long id, String firstName, String lastName, Date bdate, Address address, int weightInKg, int heightInCm) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bdate = bdate;
        this.address = address;
        this.weightInKg = weightInKg;
        this.heightInCm = heightInCm;
    }   
    
    public Patient() {}
    
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

    public Date getBdate() {
        return bdate;
    }
    
    public int getAge() {
        LocalDate birthDate = new java.sql.Date(bdate.getTime()).toLocalDate();
        LocalDate currentDate = LocalDate.now();
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }

    public Address getAddress() {
        if (this.address != null && this.address instanceof Address) {
            return this.address;
        }
        return null;
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
    
    public void setBdate(Date bdate) {
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
    
    @Override
    public String toString() {
        return "Name: " + firstName + " " + lastName + "\nAge: " + this.getAge() + "\nAddress: " + address + "\nWeight: " + weightInKg + "\nHeight: " + heightInCm;
    }
}
