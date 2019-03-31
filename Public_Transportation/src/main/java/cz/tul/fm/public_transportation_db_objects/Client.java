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
public class Client {
   private String mail;
   private String firstName;
   private String surName;

    public Client(String mail, String firstName, String surName) {
        this.mail = mail;
        this.firstName = firstName;
        this.surName = surName;
    }

    public String getMail() {
        return mail;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurName() {
        return surName;
    }
   
}
