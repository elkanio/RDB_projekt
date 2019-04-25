/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.tul.fm.public_transportation_db_objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "klient")
public class Client {
   @Id
   @Column(name="email")
   private String mail;

   @Column(name="jmeno")
   private String firstName;

   @Column(name="prijmeni")
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
