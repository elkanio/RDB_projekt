/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.tul.data;

import javax.persistence.*;


/**
 *
 * @author FilipKrat
 */
@Entity
@Table(name = "kontakt")
public class Contact {
    @Id
    @Column(name="kontakt_hodnota")
    private String contact;
    @ManyToOne
    @JoinColumn(name="typ")
    private ContactType type;

    @ManyToOne
    @JoinColumn(name="cislo_rp")
    private Driver driver;

    public Contact(){}

    public Contact(String contact, ContactType type, Driver driver) {
        this.contact = contact;
        this.type = type;
        this.driver = driver;
    }

    public String getContact() {
        return contact;
    }

    public ContactType getType() {
        return type;
    }

    public Driver getDriver() {
        return driver;
    }
    
}
