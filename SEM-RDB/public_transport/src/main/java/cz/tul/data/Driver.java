package cz.tul.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Ridic")
public class Driver {
    @Id
    @Column(name="cislo_rp")
    private String licenseNo;
    @Column(name="ridic_jmeno")
    private String firstName;
    @Column(name="ridic_prijmeni")
    private String surName;

    public Driver(){}

    public Driver(String licenseNo, String firstName, String surName) {
        this.licenseNo = licenseNo;
        this.firstName = firstName;
        this.surName = surName;
        
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurName() {
        return surName;
    }

    @Override
    public String toString() {
        return "Driver{" + "licenseNo=" + licenseNo + ", firstName=" + firstName + ", surName=" + surName + '}';
    }

    
   
}
