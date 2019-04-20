/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.tul.fm.public_transportation_db_objects;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author FilipKrat
 */
public class Contact {
    @Id
    @Column(name="kontakt_hodnota")
    private String contact;
    @OneToOne
    @Column(name="typ")
    private ContactType type;
    
    @Column(name="cislo_rp")
    private Driver driver;

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
