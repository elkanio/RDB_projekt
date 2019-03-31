/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.tul.fm.public_transportation_db_objects;

/**
 *
 * @author FilipKrat
 */
public class Contact {
    private String contact;
    private String type;
    private Driver driver;

    public Contact(String contact, String type, Driver driver) {
        this.contact = contact;
        this.type = type;
        this.driver = driver;
    }

    public String getContact() {
        return contact;
    }

    public String getType() {
        return type;
    }

    public Driver getDriver() {
        return driver;
    }
    
}
