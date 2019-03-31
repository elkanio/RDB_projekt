/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.tul.fm.public_transportation_db_objects;

import java.util.ArrayList;

/**
 *
 * @author FilipKrat
 */
public class Driver {
    private String licenseNo;
    private String firstName;
    private String surName;


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

    
   
}
