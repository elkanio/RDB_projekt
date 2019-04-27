/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.tul.data;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 *
 * @author FilipKrat
 */
public class ContactType {

    @Id
    @Column(name="typ")
    private String type;

    public ContactType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
    
}
